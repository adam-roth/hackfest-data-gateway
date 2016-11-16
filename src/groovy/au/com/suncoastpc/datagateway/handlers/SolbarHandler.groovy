package au.com.suncoastpc.datagateway.handlers

import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import au.com.suncoastpc.datagateway.Handler;
import au.com.suncoastpc.datagateway.HandlerRegistry;

//FIXME:  repackage as actual handler, register against correct context
class SolbarHandler implements Handler {
	static final def theInstance = new SolbarHandler()
	
	static final def baseUrl = "http://solbar.com.au/gigs/"
	
	static def cachedResults = null
	static def cachedResultsDay = -1
	
	static {
		HandlerRegistry.registerHandler("Event/Music/Live", theInstance)				//Live music shows
	}
	
	public JSONArray handleRequest(String context, String params) {
		Calendar cal = Calendar.getInstance()
		def currentDay = cal.get(Calendar.DAY_OF_YEAR)
		
		synchronized(theInstance) {
			if (cachedResults && cachedResultsDay == currentDay) {
				return cachedResults
			}
			
			def currentYear = cal.get(Calendar.YEAR)
			def entries = new JSONArray();
			
			println baseUrl
			
			def allGigs = baseUrl.toURL().text
			allGigs.findAll(/(?ms)\<article.*?\>(.*?)\<\/article\>/) { fullMatch, article ->
				//grab the artist
				def artist = article.split(/h3\>/)[1].split(/\>/)[1].split(/\&bull\;/)[0].trim()
				artist = artist.split(" ").collect { it.toLowerCase().capitalize() }.join(" ")
				artist = StringEscapeUtils.unescapeHtml(artist)
				
				if (! artist.contains("Open Mic") && ! artist.startsWith("<article")) {
					//found a valid entry; now we need to gather additional details:
					//	detailsLink
					//  ticket cost?
					
					def entry = new JSONObject()
					entry.locationName = "Solbar"
					entry.address = "19 Ocean Street, Maroochydore, Sunshine Coast"
					//XXX:  Eventfinda Yacht Club lat/long is {"lat":-26.6854,"lng":153.1301}
					//XXX:  note that Eventfinda will include link to correct oztix page for the event
					entry.latitude = -26.6522
					entry.longitude = 153.0903
					entry.artistName = artist
					entry.ageType = article.toLowerCase().contains("all ages") ? "All Ages" : "18+"
					entry.is_free = article.toLowerCase().contains("free entry") ? 1 : 0
					if (entry.is_free == 0) {
						//FIXME:  could dive deeper to find the appropriate oztix page
						entry.ticketLink = "http://solbar.oztix.com.au/"
					}
					entry.detailsLink = article.split(/h3\>/)[1].split(/\"/)[1]
					
					//date and time ("Thursday 17th November")
					def dayAndMonth = article.split(/h3\>/)[1].split(/\>/)[1].split(/\&bull\;/)[1].split(/\</)[0].trim().replace("st", "").replace("nd", "").replace("rd", "").replace("th", "")
					dayAndMonth = dayAndMonth.split(/ /)[1] + " " + dayAndMonth.split(/ /)[2]
					
					def dayAndMonthAndYear = dayAndMonth + " " + currentYear
					entry.startDate = Date.parse("dd MMM yyyy", dayAndMonthAndYear).getTime();
					if (entry.startDate < new Date().getTime() - 1000 * 60 * 60 * 24) {
						dayAndMonthAndYear = dayAndMonth + " " + (currentYear + 1)
						entry.startDate = Date.parse("dd MMM yyyy", dayAndMonthAndYear).getTime();
					}
					
					//time may be unavailable
					entry.startTime = entry.startDate
					if (article.contains("|")) {
						def time = article.split(/\<p\>/)[1].split(/\|/)[0].trim();
						if (time.matches(/[0-9]\:?[0-9]{0,2}(am|pm)/)) {
							def startTime = dayAndMonthAndYear + " " + time
							if (time.contains(":")) {
								entry.startTime = Date.parse("dd MMM yyyy hh:mma", startTime).getTime();
							}
							else {
								entry.startTime = Date.parse("dd MMM yyyy hha", startTime).getTime();
							}
						}
					}
					
					//additional artists
					def otherArtists = new JSONArray()
					if (article.toLowerCase().contains("with special guests:") || article.toLowerCase().contains("with special guest:")) {
						def otherList = article.toLowerCase().split(/with special guests?\:/)[1].split(/tickets\:/)[0].trim()
						otherList = StringEscapeUtils.unescapeHtml(otherList)
						
						otherList.split(/\+/).each { name ->
							name = name.trim()
							name = name.split(/ /).collect { it.toLowerCase().capitalize() }.join(" ")
							//name = StringEscapeUtils.unescapeHtml(name)
							
							otherArtists.add(name)
						}
					}
					entry.otherArtists = otherArtists
					
					//category information is unavailable
					entry.categories = new JSONArray()
					
					//collation identifier
					entry.__collationIdentifier = "${ saneString(entry.artistName) }_${ saneString(entry.locationName) }_${ entry.startDate }".toString()
					
					entries.add(entry)
					//println entry
				}
			}
			
			cachedResults = entries
			cachedResultsDay = cal.get(Calendar.DAY_OF_YEAR)
		}
		
		return cachedResults
	}
	
	static def main(args) {
		def entries = theInstance.handleRequest("Event/Music/Live", null)
		println entries
	}
	
	static def saneString(str) {
		return str.toLowerCase().replaceAll(/[^a-z]/, "")
	}
}
