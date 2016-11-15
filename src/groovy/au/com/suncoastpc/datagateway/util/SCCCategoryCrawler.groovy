package au.com.suncoastpc.datagateway.util

class SCCCategoryCrawler {
	static final def SERVER_ROOT = "http://gisservices.scc.qld.gov.au"
	static final def BASE_URL = "http://gisservices.scc.qld.gov.au/arcgis/rest/services"
	
	static final def OUTPUT_FORMAT = 'HandlerRegistry.registerHandler("[URL]", theInstance)	//[NAME]'
	
	static def main(args) {
		def markup = BASE_URL.toURL().text
		
		markup.findAll(/(?ms)\<li\>(.*?)\<\/li\>/) { fullMatch, content ->
			if (content.endsWith("</a>")) {
				content = SERVER_ROOT + content.split(/\"/)[1]
				//println content
			
				def containers = content.toURL().text
				containers.findAll(/(?ms)\<li\>(.*?)\<\/li\>/) { match, category ->
					if (category.contains("MapServer") && ! category.contains("ImageryBaseMapsEarthCover")) {
						category = SERVER_ROOT + category.split(/\"/)[1]
						
						def subcategories = category.toURL().text
						
						//println "CHECKING:  category=${ category }, subs=${ subcategories.findAll(/\<li\>(.*)\<\/li\>/) }"
						subcategories.findAll(/(?ms)\<li\>(.*?)\<\/li\>/) { full, subcategory ->
							if (subcategory.trim().startsWith("<a") && subcategory.contains("/arcgis/rest/services/")) {
								//println subcategory
							
								def url = subcategory.split(/\"/)[1].replace("/arcgis/rest/services", "")
								def name = subcategory.split(/\>/)[1].split(/\</)[0]
								
								println OUTPUT_FORMAT.replace("[URL]", url).replace("[NAME]", name)
							}
						}
					}
				}	
			}
		}
	}
}
