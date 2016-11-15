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
	
	static {
		HandlerRegistry.registerHandler("Event/Music/Live", theInstance)				//Live music shows
		//HandlerRegistry.registerHandler("Event/Music/Live/Venues", theInstance)		//Live music venues  [not actually required, as location details will be embedded within the API results]
	
		serviceEndpoints.put("Event/Music/Live", "events.json")
		serviceEndpoints.put("Event/Music/Live/Venues", "locations.json")
	}
	
	public JSONArray handleRequest(String context, String params) {
		def result = new JSONArray()
		
		//manipulate the input params a bit
		def paramMap = params ? params.split('&').collectEntries { param ->
			param.split('=').collect { it }
		} : [:]
		
		//paramMap.f = "pjson"				//only JSON is supported
		//if (! paramMap.outSR) {
		//	paramMap.outSR = "4326"			//set the output type to lat/long if none has been specified
		//}
		//if (! paramMap.outFields) {
		//	paramMap.outFields = "*"		//return all fields by default
		//}
		//if (! paramMap.where) {
		//	paramMap.where = "1=1"			//return all items by default
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
			
			//we want to transform the result a bit, to make it more convenient to work with on the client-side
			responseObj.events.each { obj ->
				if (obj.point && obj.point.lat && obj.location && obj.artists && obj.artists.artists) {
					def newObj = new JSONObject()
					newObj.putAll(obj)
					
					newObj.latitude = obj.point.lat
					newObj.longitude = obj.point.lng
					newObj.locationName = obj.location.name
					newObj.ageType = obj.restrictions
					newObj.artistName = obj.artists.artists[0].name
					
					//FIXME:  alternately; create a duplicate event for each distinct artist? (or do this on the client-side)
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
		}
		catch (e) {
			e.printStackTrace()
		}
		
		return result
	};

	def saneString(str) {
		return str.toLowerCase().replaceAll(/[^a-z]/, "")
	}
}
