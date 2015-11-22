package data.gateway

import java.lang.ref.ReferenceQueue.Null;

import org.json.simple.JSONValue;

import au.com.suncoastpc.auth.util.StringUtilities;

class ExamplesController {

    def index() { 
		
	}
	
	def basic() {
		
	}
	
	def geoPDO() {
		
	}
	
	def geoPDODetails() {
		def projectData = params.project ? JSONValue.parse(params.project) : null
		if (! projectData || ! params.project) {
			redirect(controller: 'examples', action: 'geoPDO')
			return;
		}
		
		//println "${ params.project }"
		//println "${ projectData.getClass().getName() }:  ${ projectData }"
		
		//pull in comments for the project
		def comments = SubmittedComment.findAll("FROM SubmittedComment AS c WHERE c.externalId=?", [projectData.ram_id])
		projectData.comments = null;
		
		request.project = projectData
		request.comments = comments
	}
	
	def geoPDOAddComment() {
		def projectData = params.project ? JSONValue.parse(params.project) : null
		if (! projectData) {
			redirect(controller: 'examples', action: 'geoPDO')
			return;
		}
		
		def text = params.commentText
		if (! StringUtilities.isEmpty(text)) {
			SubmittedComment.withTransaction {
				def newComment = new SubmittedComment()
				newComment.externalId = projectData.ram_id
				newComment.externalData = params.project
				newComment.ip = request.getRemoteAddr()
				newComment.latitude = projectData.latitude
				newComment.longitude = projectData.longitude
				newComment.timestamp = new Date()
				newComment.commentText = text
				
				newComment.save(flush:true)
			}
		}
		
		redirect(controller: 'examples', action: 'geoPDODetails', params: params)
	}
}
