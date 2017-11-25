package horizon.sync

import java.util.Date;

class SyncEvent {
	static constraints = {
		createTime blank:false
		eventTime blank:false
		
		latitude blank:false
		longitude blank:false
		numZones blank:false
		radiusMeters blank:false
		startDegrees blank:false
		stopDegrees blank:false
		
		shape blank:false, maxLength:32
		name blank:false, maxLength:255
		thumbnail blank:false
		scriptsJson blank:false
	}
	
	static mapping = {
		//field types
		thumbnail type: 'text'
		scriptsJson type: 'text'
		
		//default sort order
		sort "eventTime"
	}
	
	//fields
	String name
	String shape
	
	String thumbnail
	String scriptsJson
	
	double latitude
	double longitude
	
	int numZones
	int radiusMeters
	int startDegrees
	int stopDegrees
	
	Date createTime
	Date eventTime
}
