package data.gateway

class LoggedApiRequest {

    static constraints = {
		timestamp blank:false
		requestPath blank:false, maxLength:4096
    }
	
	static mapping = {
		//default sort order
		sort "name"
	}
	
	//fields
	Date timestamp
	String requestPath
	
	//relationships
	static hasOne = [credentials:ApiCredentials]
}
