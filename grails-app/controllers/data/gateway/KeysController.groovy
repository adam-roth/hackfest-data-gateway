package data.gateway

import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions.CountFunction;

import au.com.suncoastpc.auth.util.StringUtilities;

class KeysController {

    def index() { 
		//list all keys
		def allKeys = ApiCredentials.findAll().sort{ left, right ->
			left.apiKey <=> right.apiKey
		}
		
		def keyStats = [:]
		
		allKeys.each { creds ->
				def stats = [count: creds.requests.size()]
				keyStats.put(creds.apiKey, stats)
		}
		
		request.keys = allKeys
		request.stats = keyStats
	}
	
	def newKey() {
		ApiCredentials.withTransaction {
			def newKey = new ApiCredentials()
			newKey.apiKey = StringUtilities.randomStringOfLength(32)
			newKey.secretKey = StringUtilities.randomStringOfLength(16)
			newKey.save(flush:true)	
			
			request.apiKey = newKey
		}
	}
}
