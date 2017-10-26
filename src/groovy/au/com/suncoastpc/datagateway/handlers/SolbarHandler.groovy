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
	static final def separatorRegex = /(\&bull\;|•| +[^a-zA-Z0-9!@\#\$\%\^\&\*\(\)\-_\=\+\{\[\]\}\,\.\;\:\'\"\<\>\\\/\?\`\~]+ +)/
	
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
			//allGigs.findAll(/(?ms)\<article.*?\>(.*?)\<\/article\>/) { fullMatch, article ->
			allGigs.findAll(/(?ms)\<div.*?class\=\"sow-image-grid-image\"\>(.*?)\<\/div\>/) { fullMatch, article ->
				//find the link
				def articleParts = article.split(/href\=\"/)
				def link = articleParts.length > 1 ? article.split(/href\=\"/)[1].split(/\"/)[0] : nil
				article = link.toURL().text.replaceAll(/\<\/?del\>/, "")
				
				println link
				
				//sleep 2500
				
				//grab the artist
				def artist = "open mic"
				
				if (link) {
					try {
						def initialParts = article.split(/(?msi)\<h1.*?\>/)
						artist = (initialParts.length < 2 ? initialParts[0] : initialParts[1]).split(/\>/)[0].split(separatorRegex)[0].trim()
						artist = artist.split(" ").collect { it.toLowerCase().capitalize() }.join(" ")
						artist = StringEscapeUtils.unescapeHtml(artist)
					}
					catch (ex) {
						//failed to parse properly; just log and move on to the next one
						//ex.printStackTrace()
						println "Failed to parse artist; url=${ link }, error=${ ex }"
					}
				}
				else {
					println "No link found in article; article=${ article }"
				}
				
				if (link && ! artist.toLowerCase().contains("open mic") /*&& ! artist.startsWith("<article")*/ && ! artist.toLowerCase().startsWith("coming up")) {
					//found a valid entry; now we need to gather additional details:
					//	detailsLink
					//  ticket cost?
					
					//println "----------------------------- \n${ article }"
					try {
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
							entry.ticketLink = "https://solbar.oztix.com.au/"
							try {
								entry.ticketLink = "https://solbar.oztix.com.au/Default.aspx" + article.split(/solbar\.oztix\.com\.au\/Default\.aspx/)[1].split(/\"/)[0]
							}
							catch (ignored) { }
						}
						entry.detailsLink = link //article.split(/h3\>/)[1].split(/\"/)[1]
						
						//date and time ("Thursday 17th November")
						//FIXME:  we should just propagate this in text format to the client (but should probably do that translation at a level above individual crawlers)
						def dayAndMonth = article.split(/(?msi)\<h1.*?\>/)[1].split(/\>/)[0].split(separatorRegex)[1].split(/\</)[0].trim().replace("st", "").replace("nd", "").replace("rd", "").replace("th", "")
						dayAndMonth = dayAndMonth.split(/ /)[1] + " " + dayAndMonth.split(/ /)[2]
						
						//cleanup invalid data
						def dayAndMonthAndYear = (dayAndMonth + " " + currentYear).replace("Augu ", "August ").replace("Nvember", "November")
						if (dayAndMonthAndYear.endsWith(" 6")) {
							dayAndMonthAndYear = dayAndMonthAndYear.substring(0, dayAndMonthAndYear.length() - 2)
						}
						
						entry.startDate = Date.parse("dd MMM yyyy", dayAndMonthAndYear).getTime();
						if (entry.startDate < new Date().getTime() - 1000 * 60 * 60 * 24) {
							dayAndMonthAndYear = dayAndMonth + " " + (currentYear + 1)
							entry.startDate = Date.parse("dd MMM yyyy", dayAndMonthAndYear).getTime();
						}
						
						//time may be unavailable
						entry.startTime = entry.startDate
						//<h2>8pm
						article.findAll(/(?msi)\<h2.*?\>([0-9]\:?[0-9]{0,2}[amp]{0,2}).*?[\|\<]/) { match, time ->
							def startTime = dayAndMonthAndYear + " " + time
							if (time.contains(":")) {
								entry.startTime = Date.parse("dd MMM yyyy hh:mma", startTime).getTime();
							}
							else {
								entry.startTime = Date.parse("dd MMM yyyy hha", startTime).getTime();
							}
						}
						/*if (article.contains("|")) {
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
						}*/
						
						//additional artists
						def otherArtists = new JSONArray()
						article.findAll(/(?msi)\<h2.*?\>With Special Guests?\s*?\|\s*?([A-Za-z0-9]+.*?)\<\/h2/) { match, others ->
							def otherList = StringEscapeUtils.unescapeHtml(others)
							
							otherList.split(/\+/).each { name ->
								name = name.trim()
								name = name.split(/ /).collect { it.toLowerCase().capitalize() }.join(" ")
								//name = StringEscapeUtils.unescapeHtml(name)
								
								if (! otherArtists.contains(name)) {
									otherArtists.add(name)
								}
							}
						}
						
						/*if (article.toLowerCase().contains("with special guests:") || article.toLowerCase().contains("with special guest:")) {
							def otherList = article.toLowerCase().split(/with special guests?\:/)[1].split(/tickets\:/)[0].trim()
							otherList = StringEscapeUtils.unescapeHtml(otherList)
							
							otherList.split(/\+/).each { name ->
								name = name.trim()
								name = name.split(/ /).collect { it.toLowerCase().capitalize() }.join(" ")
								//name = StringEscapeUtils.unescapeHtml(name)
								
								otherArtists.add(name)
							}
						}*/
						entry.otherArtists = otherArtists
						
						//event description
						article.find(/(?msi)\<div.*?class\=.*?so\-panel.*?data\-index\=.*?1.*?\>.*?<div.*?class\=.*?textwidget.*?\>(.*?)\<\/div\>/) { match, content ->
							entry.description = StringEscapeUtils.unescapeHtml(content.trim().replaceAll(/\<.*?\>/, ""))
						}
						
						//category information is unavailable
						entry.categories = new JSONArray()
						
						//collation identifier
						entry.__collationIdentifier = "${ saneString(entry.artistName) }_${ saneString(entry.locationName) }_${ entry.startDate }".toString()
						
						entries.add(entry)
						//println entry
					}
					catch (ex) {
						//failed to parse properly; just log and move on to the next one
						//ex.printStackTrace()
						println "Failed to parse; url=${ link }, error=${ ex }"
					}
				}
			}
			
			cachedResults = entries
			cachedResultsDay = cal.get(Calendar.DAY_OF_YEAR)
		}
		
		return cachedResults
	}
	
	static def main(args) {
		def entries = theInstance.handleRequest("Event/Music/Live", null)
		println entries.toJSONString();
	}
	
	static def saneString(str) {
		return str.toLowerCase().replaceAll(/[^a-z]/, "")
	}
}
