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
				def candidates = []
				def unfilteredItems = []
				handlers.each { handler ->
					handler.handleRequest(context, queryParams).each { item ->
						unfilteredItems.add(item)
					}
				}
				
				//now collate anything that we can
				def mergeMap = [:]
				unfilteredItems.each { item ->
					//first pass, collect everything that will definitely be in our result-set
					if (! item.ephemeral) {
						candidates.add(item)
						if (item._collationIdentifier) {
							if (! mergeMap.containsKey(item._collationIdentifier)) {
								mergeMap.put(item._collationIdentifier, [])
							}
							mergeMap.get(item._collationIdentifier).add(item)
							//mergeMap.put(item._collationIdentifier, item)
						}
					}
				}
				
				unfilteredItems.each{ item ->
					//second pass, merge in any ephemeral items, if possible
					if (item.ephemeral && item._collationIdentifier && mergeMap.get(item._collationIdentifier)) {
						//we have something to merge with
						def masterItems = mergeMap.get(item._collationIdentifier)
						if (item.persistentFields) {
							def newMasterItems = []
							masterItems.each { masterItem ->
								item.persistentFields.each { field ->
									masterItem.put(field, item.get(field))
									//mergeMap.put(item._collationIdentifier, masterItem)
								}
								newMasterItems.add(masterItem)
							}
							mergeMap.put(item._collationIdentifier, newMasterItems)
						}
					}
					else if (item.ephemeral) {
						//it's a choice between an ephemeral item and a non-existent one; allow the ephemeral one to proceed
						candidates.add(item)
					}
				}
				
				//XXX:  fix strange issue where changes made in the merge process aren't reflected in the final output
				def handledCollations = new HashSet()
				candidates.each { item ->
					if (item._collationIdentifier && mergeMap.get(item._collationIdentifier)) {
						if (! handledCollations.contains(item._collationIdentifier)) {
							result.addAll(mergeMap.get(item._collationIdentifier))
							handledCollations.add(item._collationIdentifier)	
						}
					}
					else {
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
