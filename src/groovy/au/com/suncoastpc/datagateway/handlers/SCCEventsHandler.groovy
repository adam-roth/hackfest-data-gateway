package au.com.suncoastpc.datagateway.handlers

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.JSONValue

import au.com.suncoastpc.datagateway.Handler
import au.com.suncoastpc.datagateway.HandlerRegistry

class SCCEventsHandler implements Handler {
	static final def theInstance = new SCCEventsHandler()
	
	//XXX:  cat=10 for music
	static final def serviceRoot = "http://api.sunshinecoast.qld.gov.au/SCCEvents/Events"
	
	static def cachedResults = null
	static def cachedResultsDay = -1
	
	static {
		HandlerRegistry.registerHandler("Event/Music/Live", theInstance)				//Live music shows
		HandlerRegistry.registerHandler("Events/General", theInstance)
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
			
			if (! paramMap.cat || context.equals("Event/Music/Live")) {
				paramMap.cat = 10  		//XXX:  Music and Entertainment category
			}
			
			//put the input params back (only necessary if we've added new params, above)
			def newParams = ""
			paramMap.each { entry ->
				if (! newParams.equals("")) {
					newParams += "&"
				}
				newParams += "${ entry.key }=${ entry.value }"
			}
			params = newParams
			
			def requestUrl = "${ serviceRoot }?${ params }"
			println "${ requestUrl }"
			
			try {
				//send request
				def response = requestUrl.toURL().text.replace("\n", " ")
				
				//println "\nRequest:  ${ requestUrl } -> \n${ response }"
				
				//handle response
				def responseObj = JSONValue.parse(response)
				def events = responseObj.data
				
				//we want to transform the result a bit, to make it more convenient to work with on the client-side
				events.each { obj ->
					/*
					 * {"event_id":19193,
					 * "event_desc":"",
					 * "event_image":"http://api.sunshinecoast.qld.gov.au/Events/EventImage/eventimage_1_5101__1568008425",
					 * "event_cost":null,
					 * "event_contact":"Ph: 0413 227 013",
					 * "event_featuredEvent":false,
					 * "event_featuredNews":false}
					 */
					
					
					if (obj.event_startdate && obj.event_lat && obj.event_lng && obj.event_venue && obj.event_title 
						&& (context.equals() || ! obj.event_title.contains("Christmas Boat Parade"))) {
						def newObj = new JSONObject()
						newObj.putAll(obj)
						
						newObj.latitude = obj.event_lat
						newObj.longitude = obj.event_lng
						newObj.locationName = obj.event_venue
						newObj.artistName = obj.event_title.replace(" 'Death To Misery' Tour 2017", "");
						
						newObj.address = obj.event_street + ", " +  obj.event_suburb
						newObj.is_free = obj.event_freeEvent ? 1 : 0
						
						
						//FIXME:  alternately; create a duplicate event for each distinct artist? (or do this on the client-side)
						//FIXME:  also include URL for event details page, if present
						def otherArtists = new JSONArray()
						newObj.otherArtists = otherArtists
						
						def categories = new JSONArray()
						newObj.categories = categories
						
						if (obj.event_url) {
							newObj.website = obj.event_url
							newObj.detailsLink = newObj.website
						}
						
						if (obj.event_image) {
							newObj.imageLink = "http://api.sunshinecoast.qld.gov.au/SCCEvents" + obj.event_image
						}
						
						//"2016-11-15 19:30:00"
						newObj.startTime = Date.parse("yyyy-MM-dd HH:mm:ss", obj.event_startdate.replace("T", " ")).getTime()
						newObj.startDate = Date.parse("yyyy-MM-dd", obj.event_startdate.split(/T/)[0]).getTime()
						newObj.description = obj.event_desc.replaceAll(/\<.*?\>/, "")
						
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
