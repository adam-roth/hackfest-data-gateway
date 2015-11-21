package data.gateway

import java.sql.ResultSet;

import org.json.simple.JSONArray

import au.com.suncoastpc.datagateway.HandlerRegistry

class ApiController {

    def client() {
		def lookupKey = params.apiKey
		def apiKey = ApiCredentials.find("FROM ApiCredentials AS a WHERE a.apiKey=?", [lookupKey])
		
		if (apiKey) {
			request.apiKey = apiKey.apiKey
			//request.baseUrl = createLink({uri:"/", absolute:true})
		}
	}
	
	def loadData() {
		def lookupKey = params.apiKey
		def apiKey = ApiCredentials.find("FROM ApiCredentials AS a WHERE a.apiKey=?", [lookupKey])
		
		if (apiKey) {
			//log the request for this key
			LoggedApiRequest.withTransaction {
				def newLog = new LoggedApiRequest()
				newLog.credentials = apiKey
				newLog.timestamp = new Date()
				newLog.requestPath = request.requestURI + '?' + request.queryString 
				
				newLog.save(flush:true)	
			}
			
			//attempt to process the request
			//lookup handlers registered against the specified context, combine their results, and return the merged results-set as the result
			def context = params.context
			def queryParams = params.params //URLDecoder.decode(params.params, "utf-8")
			
			def handlers = HandlerRegistry.handlersForContext(context)
			if (! handlers || handlers.isEmpty()) {
				request.status = "error"
				request.message = "No handlers exist for context:  ${ context }"
			}
			else {
				def result = new JSONArray()
				handlers.each { handler ->
					handler.handleRequest(context, queryParams).each { item ->
						result.add(item)
					}
				}
				
				request.status = "success"
				request.result = result.toJSONString()
			}
			
			
		}
		else {
			request.status = "error"
			request.message = "Invalid API key:  ${ params.apiKey }"
		}
		
		//honor jQuery's request for jsonp data
		request.callback = params.callback
	}
}
