package horizon.sync

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.JSONValue

class SyncController {
	static final def ADMIN_PIN = "12345a"
	
	def ping() {
		//no-op
	}
	
	def about() {
		//static content
	}

    def index() { 
		//list/expose all events
		def allEvents = SyncEvent.findAll("from SyncEvent as e where e.eventTime > ?", [ new Date() ])
		
		request.events = allEvents
	}
	
	def create() {
		//validate params and create event
		def eventName = params.name
		
		//timezones make everything crazy!!!
		def dateString = params.eventDate
		def javaTzId = dateString.split(" ")[2]
		TimeZone tz = TimeZone.getTimeZone(javaTzId)
		
		def tentativeDate = Date.parse("d/MM/yyyy H:mm", dateString.replace(" ${ javaTzId }", ""))
		int offsetMillis = tz.getOffset(tentativeDate.getTime())
		
		boolean negative = offsetMillis < 0
		if (negative) {
			offsetMillis *= -1
		}
		
		int offsetHours = offsetMillis / (1000 * 60 * 60)
		offsetMillis -= offsetHours * 1000 * 60 * 60
		int offsetMinutes = offsetMillis / (1000 * 60)
		
		def rfcTz = negative ? "-" : "+"
		rfcTz += offsetHours < 10 ? "0" + offsetHours : offsetHours
		rfcTz += offsetMinutes < 10 ? "0" + offsetMinutes : offsetMinutes
		//end timezone craziness
	
		dateString = dateString.replace(javaTzId, rfcTz)
		def eventDate = Date.parse("d/MM/yyyy H:mm Z", dateString)
		if (eventDate.getTime() > System.currentTimeMillis()) {
			def latitude = params.double("latitude")
			def longitude = params.double("longitude")
			if (latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180) {
				def shape = params.shape
				def radiusMeters = params.int("meters")
				if (radiusMeters >= 50 && radiusMeters <= 10000) {
					def startDeg = params.int("startDeg")
					def stopDeg = params.int("stopDeg")
					if (startDeg > stopDeg) {
						def temp = startDeg;
						startDeg = stopDeg;
						stopDeg = temp;
					}
					if (startDeg >= 0 && startDeg <= 360 && stopDeg >= 0 && stopDeg <= 360) {
						def numZones = params.int("zones")
						if (numZones >= 1 && numZones <= 16) {
							def thumb = params.image
							def scriptJson = params.scripts
							
							def parsedScripts = JSONValue.parse(scriptJson)
							parsedScripts.each { zoneScript ->
								zoneScript.get("events").each { event ->
									if (event.get("image")) {
										def address = event.get("image")
										def fName = "${ address.tokenize('/')[-1] }.${ address.hashCode() }.png"
										try {
											new File(fName).withOutputStream { out ->
												out << new URL(address).openStream()
											}
											//FIXME:  uncomment to proxy the images internally (good for not DoS'ing people!)
											//event.put("image", g.createLink(controller:"sync", action:"getFile", params:[file: fName]))
										}
										catch (ex) {
											request.status = "error"
											request.message = "Unable to download the image resource located at '${ address }'!"
										}
									}
								}
							}
							
							
							def pin = params.pin
							if (pin == ADMIN_PIN) {
								//all good, we should be able to create the event
								SyncEvent.withTransaction {
									def newEvent = new SyncEvent()
									newEvent.createTime = new Date()
									newEvent.eventTime = eventDate
									newEvent.latitude = latitude
									newEvent.longitude = longitude
									newEvent.name = eventName
									newEvent.numZones = numZones
									newEvent.radiusMeters = radiusMeters
									newEvent.scriptsJson = parsedScripts.toJSONString()
									newEvent.shape = shape
									newEvent.startDegrees = startDeg
									newEvent.stopDegrees = stopDeg
									newEvent.thumbnail = thumb
									
									def result = newEvent.save(flush:true, failOnError: true)
									
									request.status = "success"
									request.message = "Created new event with id=${ newEvent.id }"
								}
							}
							else {
								request.status = "error"
								request.message = "Unauthorized request!"
							}
							
						}
						else {
							request.status = "error"
							request.message = "The select number of zones must be between 1 and 16!"
						}
					}
					else {
						request.status = "error"
						request.message = "The specified audience geometry is not valid!"
					}
				}
				else {
					request.status = "error"
					request.message = "The specified venue dimensions are not valid!"
				}
			}
			else {
				request.status = "error"
				request.message = "The specified latitude/longitude is not valid!"
			}			
		}
		else {
			request.status = "error"
			request.message = "The specified event date/time is in the past!"
		}
	}
	
	def getScript() {
		def event = SyncEvent.findById(params.int("id"))
		request.data = event?.scriptsJson
	}
	
	def getUpdates() {
		//need to compute and report clock skew, which should happen first
		long serverTime = System.currentTimeMillis()
		long clientTime = params.long("clientTime") + params.long("clientLatency")
		long timeOffset = serverTime - clientTime		//amount client must ADD to their clock readings
		
		def output = new JSONObject()
		output.put("timeOffset", timeOffset)
		
		def result = new JSONArray()
		def timestamp = new Date(params.long("timestamp"))
		SyncEvent.findAll("from SyncEvent as e where e.createTime > ? and e.eventTime > ?", [ timestamp, new Date() ] ).each { event ->
			JSONObject json = new JSONObject()
			json.put("name", event.name)
			json.put("start", event.eventTime.getTime())
			json.put("created", event.createTime.getTime())
			json.put("latitude", event.latitude)
			json.put("longitude", event.longitude)
			json.put("numZones", event.numZones)
			json.put("radiusMeters", event.radiusMeters)
			json.put("shape", event.shape)
			json.put("startDegrees", event.startDegrees)
			json.put("stopDegrees", event.stopDegrees)
			json.put("zoneScripts", JSONValue.parse(event.scriptsJson))
		
			result.add(json)		
		}
		
		output.put("updates", result)
		
		request.status = "success"
		request.result = output.toJSONString()
	}
	
	def getFile() {
		//FIXME:  implement
		def file = params.file  //XXX:  dangerous!!!
	}
}
