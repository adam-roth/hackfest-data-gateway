package data.gateway

class ApiCredentials {
	
	static constraints = {
		apiKey blank:false, maxLength:255, unique:true
		secretKey blank: false, maxLength:255
	}
	
	static mapping = {
		//default sort order
		sort "apiKey"
	}
	
	//fields
	String apiKey
	String secretKey
	
	//relationships
	static hasMany = [requests:LoggedApiRequest]
	//XXX:  normally we'd also be associated with a user account

}
