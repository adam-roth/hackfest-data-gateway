package au.com.suncoastpc.datagateway.handlers

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.JSONValue

import au.com.suncoastpc.datagateway.Handler
import au.com.suncoastpc.datagateway.HandlerRegistry
import data.gateway.SubmittedComment

class CommentsHandler implements Handler {
	static final def theInstance = new CommentsHandler()
	
	static {
		//register for all the context-paths that we support here
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/2", theInstance)	//DevelopmentApps - All
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/5", theInstance)	//BuildingApps - All
	}
	
	public JSONArray handleRequest(String context, String params) {
		def result = new JSONArray()
		
		if (! params) {
			params = "placeholder=1"
		}
		
		//manipulate the input params a bit
		def paramMap = params.split('&').collectEntries { param ->
			param.split('=').collect { it }
		}
		
		if (paramMap.geometry) {
			//can only search if we have a bounding box
			def parts = URLDecoder.decode(paramMap.geometry, "utf-8").split("\\,")
			def minLng = Double.parseDouble(parts[0])
			def minLat = Double.parseDouble(parts[1])
			def maxLng = Double.parseDouble(parts[2])
			def maxLat = Double.parseDouble(parts[3])
			
			def comments = []
			def where = paramMap.where
			if (where.contains("ram_id=") && where.contains("DESCRIPTION LIKE")) {
				//where ram_id='$1' and DESCRIPTION LIKE '%$2%'
				def externalId = where.split(" and ")[0].split("\\'")[1]
				def keyword = where.split(" and ")[1].split("\\'")[1]
			
				comments = SubmittedComment.findAll("FROM SubmittedComment AS c WHERE latitude > ? AND latitude < ? AND longitude > ? AND longitude < ? AND externalId = ? AND context = ? AND externalData LIKE ?", [minLat, maxLat, minLng, maxLng, externalId, context, keyword])
			}
			else if (where.contains("ram_id")) {
				//where ram_id='$1'
				def externalId = where.split("\\'")[1]
				comments = SubmittedComment.findAll("FROM SubmittedComment AS c WHERE latitude > ? AND latitude < ? AND longitude > ? AND longitude < ? AND externalId = ? AND context = ?", [minLat, maxLat, minLng, maxLng, externalId, context])
			}
			else if (where.contains("DESCRIPTION LIKE")) {
				//DESCRIPTION LIKE '%$1%'
				def keyword = where.split("\\'")[1]
				comments = SubmittedComment.findAll("FROM SubmittedComment AS c WHERE latitude > ? AND latitude < ? AND longitude > ? AND longitude < ? AND context = ? AND externalData LIKE ?", [minLat, maxLat, minLng, maxLng, context, keyword])
			}
			else {
				//don't need to worry about the 'where' clause
				comments = SubmittedComment.findAll("FROM SubmittedComment AS c WHERE latitude > ? AND latitude < ? AND longitude > ? AND longitude < ? AND context = ?", [minLat, maxLat, minLng, maxLng, context])
			}
			
			//now we have to group/merge the comments together, by item
			def mergeMap = [:]
			comments.each { comment ->
				def container = mergeMap.get(comment.externalId)
				if (! container) {
					container = JSONValue.parse(comment.externalData)
					container.comments = new JSONArray()
					
					//data used to help us intelligently merge things
					container.ephemeral = true
					container.persistentFields = new JSONArray()
					container.persistentFields.add("comments")
					container._collationIdentifier = container.ram_id
					
					mergeMap.put(comment.externalId, container)
					result.add(container)
				}
				
				def commentJson = new JSONObject()
				commentJson.timestamp = comment.timestamp.getTime()
				commentJson.ip = comment.ip
				commentJson.text = comment.commentText
				
				container.comments.add(commentJson)
			}
		}
		
		return result
	};
}
