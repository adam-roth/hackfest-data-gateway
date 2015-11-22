package data.gateway

import java.awt.geom.Arc2D.Double;
import java.util.Date;

class SubmittedComment {

    static constraints = {
		timestamp blank:false
		ip blank:false, maxLength: 128
		commentText blank:false
		latitude blank: false
		longitude blank: false
		externalId blank: false, maxLength: 256
		externalData blank: false
    }
	
	static mapping = {
		//default sort order
		sort "timestamp"
		commentText type: 'text'
		externalData type: 'text'
	}
	
	//fields
	Date timestamp
	String ip
	String commentText
	double latitude
	double longitude
	String externalId
	String externalData
}
