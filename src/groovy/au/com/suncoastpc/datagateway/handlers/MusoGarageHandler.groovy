package au.com.suncoastpc.datagateway.handlers

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

import org.apache.commons.lang.StringEscapeUtils
import org.json.simple.JSONArray
import org.json.simple.JSONObject

import au.com.suncoastpc.auth.util.CookieManager
import au.com.suncoastpc.datagateway.Handler
import au.com.suncoastpc.datagateway.HandlerRegistry

//FIXME:  should explicitly set AEST as timezone on all Date.parse() calls
class MusoGarageHandler implements Handler {
	static final def theInstance = new MusoGarageHandler()
	
	static final def serverRoot = "https://www.musosgarage.com"
	static final def baseUrl = "${ serverRoot }/gigs/"
	static final def searchBase = "${ serverRoot }/gigs/SearchForm"
	
	static def cachedResults = null
	static def cachedResultsDay = -1
	
	static final def supportedVenues = ["The Yacht Club":["lat":-26.6854, "lng":153.1301, "address": "33-45 Parkyn Parade, Mooloolaba, Sunshine Coast"], 
										"Solbar":["lat":-26.6522, "lng":153.0903, "address": "19 Ocean Street, Maroochydore, Sunshine Coast"],
										"The Imperial Hotel":["lat":-26.4758, "lng":152.9506, "address": "1 Etheridge St, Eumundi, Sunshine Coast"],
										"Noosa Reef Hotel":["lat":-26.3926, "lng":153.0900, "address": "19 Noosa Dr, Noosa Heads"], 
										"The Peregian Beach Hotel":["lat":-26.4807754, "lng":153.0952556, "address": "221-229 David Low Way, Peregian Beach, Sunshine Coast"], 
										"Cooroy RSL":["lat":-26.4176, "lng":152.9107, "address":"25 Maple St, Cooroy, Sunshine Coast"],
										"Harbour Wine Bar":["lat":-26.3943, "lng":153.0415, "address":"2 Parkyn Ct, Tewantin, Sunshine Coast"],
										"Coolum Beach Bowls Club":["lat":-26.5295, "lng":153.0900, "address":"7-13 Elizabeth St, Coolum Beach, Sunshine Coast"],
										"Artisan Marketplace Maleny": ["lat":-26.7582, "lng":152.8514, "address":"38 Maple St, Maleny, Sunchine Coast"],
										"Tewantin Noosa RSL":["lat":-26.3928, "lng":153.0397, "address":"1 Memorial Ave, Tewantin, Sunshine Coast"],
										"Coolum Beach Hotel":["lat":-26.5269, "lng":153.0900, "address":"David Low Way, Coolum Beach, Sunshine Coast"],
										"The Surf Club":["lat":-26.6815, "lng":153.1215, "address":"The Esplanade, Mooloolaba, Sunshine Coast"],
										"Palmwoods Hotel":["lat":-26.6887, "lng":152.9596, "address":"28-34 Main St, Palmwoods, Sunshine Coast"]]
	
	static {
		//XXX:  VERY BAD that we're doing this, however something is wrong with certs on muso's side; this is the fastest worksaround for now
		HandlerRegistry.registerHandler("Event/Music/Live", theInstance)				//Live music shows
		
		def nullTrustManager = [
			checkClientTrusted: { chain, authType ->  },
			checkServerTrusted: { chain, authType ->  },
			getAcceptedIssuers: { null }
		]
		
		def nullHostnameVerifier = [
			verify: { hostname, session -> true }
		]
		
		SSLContext sc = SSLContext.getInstance("SSL")
		sc.init(null, [nullTrustManager as X509TrustManager] as TrustManager[], null)
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
		HttpsURLConnection.setDefaultHostnameVerifier(nullHostnameVerifier as HostnameVerifier)
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
			
			//manipulate the input params a bit
			def paramMap = params ? params.split('&').collectEntries { param ->
				param.split('=').collect { it }
			} : [:]
			
			if (! paramMap.ClassificationID) {
				paramMap.ClassificationID = 24  //XXX:  live concerts
			}
			if (! paramMap.CountryID) {
				paramMap.CountryID = 1			//XXX:  'Australia'
			}
			if (! paramMap.StateID) {
				paramMap.StateID = 2			//XXX:  'QLD'
			}
			if (! paramMap.CityID) {
				paramMap.CityID = 24			//XXX:  'Sunshine Coast'
			}
			
			println baseUrl
			
			//XXX:  we need to properly handle cookies across requests
			def cookieManager = new CookieManager()
			def conn = baseUrl.toURL().openConnection()
			cookieManager.setCookies(conn)
			conn.connect()
			cookieManager.storeCookies(conn)
			
			//only thing we actually need to get here is the security code for our session
			def indexPage = conn.getInputStream().text
			paramMap.SecurityID = indexPage.split(/SecurityID\"/)[1].split(/\"/)[1]

			//put the input params back (only necessary if we've added new params, above)
			def newParams = ""
			paramMap.each { entry ->
				if (! newParams.equals("")) {
					newParams += "&"
				}
				newParams += "${ entry.key }=${ entry.value }"
			}
			params = newParams
			
			//now we can load some actual data
			def requestUrl = "${ searchBase }?${ params }"
			println "${ requestUrl }"
			
			conn = requestUrl.toURL().openConnection()
			cookieManager.setCookies(conn)
			conn.connect()
			cookieManager.storeCookies(conn)
			
			def allGigs = conn.getInputStream().text
			while (allGigs) {
				def nextUrl = null
				if (allGigs.contains("Next</a>")) {
					nextUrl = serverRoot + "/" + StringEscapeUtils.unescapeHtml(allGigs.split(/next.*pagination-link.*href\=\"/)[1].split(/\"/)[0])
				}
				
				//process this batch of items
				allGigs.findAll(/(?ms)\<li.*?\>(.*?)\<\/li\>/) { fullMatch, item ->
					//println item
					
					def artist = StringEscapeUtils.unescapeHtml(item.split(/details\-title\-value.*\"\>/)[1].split(/\<\/td\>/)[0].replaceAll(/\(.*\)/, "").trim())
					def otherArtists = []
					def venue = StringEscapeUtils.unescapeHtml(item.split(/Venue\:/)[1].split(/\<td\>/)[1].split(/\<\/td\>/)[0].trim())
					def date = StringEscapeUtils.unescapeHtml(item.split(/\<p .*class=\"day\"/)[1].split(/\<p\>/)[1].split(/\<\/p\>/)[0].trim())
					def time = StringEscapeUtils.unescapeHtml(item.split(/Time\:/)[1].split(/\<td\>/)[1].split(/\<\/td\>/)[0].trim())
					def admission = StringEscapeUtils.unescapeHtml(item.split(/Admission\:/)[1].split(/\<td\>/)[1].split(/\<\/td\>/)[0].trim())
					def detailsLink = serverRoot + item.split(/href/)[1].split(/\"/)[1].split(/\"/)[0]

					if (artist.contains(" + ")) {
						otherArtists = artist.split(/ \+ /) - artist.split(/ \+ /)[0]
						artist = artist.split(/ \+ /)[0]
					}
					
					//XXX:  extendedInfo may also include samples, photos, and similar media
					def allGenres = null
					def extendedInfo = detailsLink.toURL().text
					if (extendedInfo && extendedInfo.contains("Genres")) {
						allGenres = StringEscapeUtils.unescapeHtml(extendedInfo.split(/Genres/)[1].split(/\<td\>/)[1].split(/\<\/td\>/)[0].trim())
					}

					venue = venue.split(/ - /)[0]
					if (artist && date && time && admission && detailsLink && supportedVenues.get(venue)) {
						def entry = new JSONObject()
						def venueInfo = supportedVenues[venue]
						
						entry.locationName = venue
						entry.address = venueInfo.address
						entry.latitude = venueInfo.lat
						entry.longitude = venueInfo.lng
						entry.artistName = artist
						entry.ageType = "Unknown"
						entry.is_free = admission.toLowerCase().replaceAll(/[^a-z]/, "").equals("free") ? 1 : 0
						entry.detailsLink = detailsLink
						if (entry.is_free == 0) {
							entry.ticketLink = entry.detailsLink
						}
						
						//date/time; 18 November, 2016
						entry.startDate = Date.parse("dd MMM, yyyy", date).getTime();
						entry.startTime = Date.parse("dd MMM, yyyy hh:mma", date + " " + time).getTime();
						
						//other artists
						def otherArtistsJson = new JSONArray()
						otherArtistsJson.addAll(otherArtists)
						entry.otherArtists = otherArtistsJson
						
						entry.categories = new JSONArray()
						if (allGenres) {
							allGenres.split(/\,/).each { genre ->
								if (! "ALL (".equals(genre.trim())) {
									entry.categories.add(genre.trim())
								}
							}
						}
						
						//collation identifier
						entry.__collationIdentifier = "${ saneString(entry.artistName) }_${ saneString(entry.locationName) }_${ entry.startDate }".toString()
						
						entries.add(entry)
					}
					else {
						println "Unknown venue:  ${ venue }"
					}
				}
				
				if (nextUrl) {
					println nextUrl
					
					//grab content from the next page
					conn = nextUrl.toURL().openConnection()
					cookieManager.setCookies(conn)
					conn.connect()
					cookieManager.storeCookies(conn)
					
					allGigs = conn.getInputStream().text
				}
				else {
					allGigs = null
				}
			}
			
			cachedResults = entries
			cachedResultsDay = cal.get(Calendar.DAY_OF_YEAR)
		}
		
		return cachedResults
	}
	
	static def main(args) {
		def entries = theInstance.handleRequest("Event/Music/Live", null)
		println entries.toJSONString()
	}
	
	static def saneString(str) {
		return str.toLowerCase().replaceAll(/[^a-z]/, "")
	}
}
