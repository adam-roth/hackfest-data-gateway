package au.com.suncoastpc.datagateway.handlers

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.JSONValue

import au.com.suncoastpc.datagateway.Handler
import au.com.suncoastpc.datagateway.HandlerRegistry

class EventfindaHandler implements Handler {
	static final def theInstance = new EventfindaHandler()
	
	static final def serviceUser = "coastlive"
	static final def servicePass = "xjrkzqzzd4pw"
	static final def serviceHost = "api.eventfinda.com.au"
	static final def serviceRoot = "http://${ serviceHost }/v2"
	static final def serviceEndpoints = [:]
	
	static def cachedResults = null
	static def cachedResultsDay = -1
	
	static {
		HandlerRegistry.registerHandler("Event/Music/Live", theInstance)				//Live music shows
		//HandlerRegistry.registerHandler("Event/Music/Live/Venues", theInstance)		//Live music venues  [not actually required, as location details will be embedded within the API results]
	
		serviceEndpoints.put("Event/Music/Live", "events.json")
		//serviceEndpoints.put("Event/Music/Live/Venues", "locations.json")
	}
	
	public JSONArray handleRequest(String context, String params) {
		Calendar cal = Calendar.getInstance()
		def currentDay = cal.get(Calendar.DAY_OF_YEAR)
		
		synchronized(theInstance) {
			if (cachedResults && cachedResultsDay == currentDay) {
				return cachedResults
			}
		
			def result = new JSONArray()
			
			//manipulate the input params a bit
			def paramMap = params ? params.split('&').collectEntries { param ->
				param.split('=').collect { it }
			} : [:]
			
			if (! paramMap.location) {
				paramMap.location = 84  		//XXX:  sunshine coast; can also use '&point=-26.6522586,153.0906231&radius=1'
			}
			if (! paramMap.category) {
				paramMap.category = 6			//XXX:  concerts/live music
			}
			//if (! paramMap.fields) {
			//	paramMap.fields = "event:(id,point,location,restrictions,url,name,description,address,is_free,category,artists,sessions,web_sites),session:(timezone,datetime_start),artist:(name),web_site:(url),location:(name, description)"
			//}
			
			//put the input params back (only necessary if we've added new params, above)
			def newParams = ""
			paramMap.each { entry ->
				if (! newParams.equals("")) {
					newParams += "&"
				}
				newParams += "${ entry.key }=${ entry.value }"
			}
			params = newParams
			
			def requestUrl = "${ serviceRoot }/${ serviceEndpoints.get(context) }?${ params }"
			println "${ requestUrl }"
			
			try {
				//set up basic auth
				def authString = "${ serviceUser }:${ servicePass }".getBytes().encodeBase64().toString()
				def conn = requestUrl.toURL().openConnection()
				conn.setRequestProperty("Authorization", "Basic ${authString}")
				
				//send request
				def response = conn.getInputStream().text
				
				//println "\nRequest:  ${ requestUrl } -> \n${ response }"
				
				//handle response
				def responseObj = JSONValue.parse(response)
				def events = responseObj.events
				
				while (events.size() < responseObj.get("@attributes").count) {
					def newUrl = "${ requestUrl }&offset=${ events.size() }"
					println "${ newUrl }"
					
					conn = newUrl.toURL().openConnection()
					conn.setRequestProperty("Authorization", "Basic ${authString}")
					
					response = conn.getInputStream().text
					responseObj = JSONValue.parse(response)
					
					events.addAll(responseObj.events)
				}
				
				//we want to transform the result a bit, to make it more convenient to work with on the client-side
				events.each { obj ->
					if (obj.point && obj.point.lat && obj.location && obj.artists && obj.artists.artists) {
						def newObj = new JSONObject()
						newObj.putAll(obj)
						
						newObj.latitude = obj.point.lat
						newObj.longitude = obj.point.lng
						newObj.locationName = obj.location.name
						newObj.ageType = obj.restrictions
						newObj.artistName = obj.artists.artists[0].name
						
						//FIXME:  alternately; create a duplicate event for each distinct artist? (or do this on the client-side)
						//FIXME:  also include URL for event details page, if present
						def otherArtists = new JSONArray()
						def check = newObj.artists.artists - newObj.artists.artists[0]
						check.each { artist ->
							otherArtists.add(artist.name)
						}
						newObj.otherArtists = otherArtists
						
						def categories = new JSONArray()
						if (obj.category) {
							obj.category.name.split(/\, */).each { cat ->
								categories.add(cat)
							}
						}
						newObj.categories = categories
						
						if (obj.web_sites) {
							newObj.website = obj.web_sites.web_sites[0].url
						}
						
						//"2016-11-15 19:30:00"
						newObj.startTime = Date.parse("yyyy-MM-dd HH:mm:ss", obj.datetime_start).getTime()
						newObj.startDate = Date.parse("yyyy-MM-dd", obj.datetime_start.split(/ /)[0]).getTime()
						
						
						//XXX:  note that we also have images available for display if we want to use them
						
						//set collation key to <sanitized artist name>_<sanitized location name>_startDate
						newObj.__collationIdentifier = "${ saneString(newObj.artistName) }_${ saneString(newObj.locationName) }_${ newObj.startDate }".toString()
						
						
						//FIXME:  need to figure out how to restrict search to Sunshine Coast events (probably param available in Eventfinda API; can apply client-side); also should limit categories, and request just the fields we need
						result.add(newObj)
					}
				}
				
				cachedResults = result
				cachedResultsDay = cal.get(Calendar.DAY_OF_YEAR)
			}
			catch (e) {
				e.printStackTrace()
			}
		}
		
		return cachedResults
	};

	def saneString(str) {
		return str.toLowerCase().replaceAll(/[^a-z]/, "")
	}
}
