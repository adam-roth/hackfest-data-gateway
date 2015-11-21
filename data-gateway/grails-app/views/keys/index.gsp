<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>Application Status</h1>
			<ul>
				<li>App version: <g:meta name="app.version"/></li>
				<li>Grails version: <g:meta name="app.grails.version"/></li>
				<li>Groovy version: ${GroovySystem.getVersion()}</li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
			<h1>Installed Plugins</h1>
			<ul>
				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
					<li>${plugin.name} - ${plugin.version}</li>
				</g:each>
			</ul>
		</div>
		<div id="page-body" role="main">
			<h1>API Keys</h1>
			<p>Below are all the keys that have been generated, and some basic stats for each one.  Normally this information would 
			not be publicly displayed.    
			</p>
			<g:if test="${ keys.size() < 1 }">
				<p>
					Oops.  It looks like you haven't created any keys yet.  Why not <g:link controller="keys" action="newKey">make one now</g:link>?
				</p>
			</g:if>
			<g:else>
				<table class="keysTable">
					<thead>
						<tr>
							<th>API Key</th>
							<th class="centeredCol">Secret Key</th>
							<th class="centeredCol">Requests Made</th>
						</tr>
					</thead>
					<tbody>
						<g:each var="key" in="${ keys }">
							<tr>
								<td>${ key.apiKey }</td>
								<td style="cursor: pointer;" onclick="this.innerHTML = '${ key.secretKey }'">(click to view)</td>
								<td>${ stats[key.apiKey].count }</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<h1>Prerequisites</h1>
				<p>The JavaScript API client requires the presence of <a href="http://jquery.com" target="_blank">jQuery version 1.5 or above</a>.  Please ensure this has been sourced in to your webapp before you use the API client.
				</p>
				<h1>Usage Notes</h1>
				<p>You can include the JavaScript API client in your webapp like so:</p>
				<pre class="code">&lt;script src='http://[your_server_address]${createLink(uri: "/api/client?apiKey=")}[your_api_key]'&gt;
&lt;/script&gt;</pre>
				<p>Once you've included the JavaScript API client in your webapp as shown in the example above, you can use it as follows:
				</p>
				<pre class="code">//create a reusable client instance
var client = new gateway.Client();

//get some data to work with
client.loadData({
    context: "Staging/Applications_SCRC/MapServer/2",
    params: {
	    where: "1=1",
	    f: "pjson"
	},
    success: function(data) {
        console.log(data);
    },
    failure: function(errMsg) {
        alert(errMsg);
    }
});
						</pre>
						<p>The API client currently only supports a single method, called 'loadData()'.  The data to load is identified by the 'context' parameter, which should match an existing 
						<a href="http://gisservices.scc.qld.gov.au/arcgis/rest/services/" target="_blank">ArcGIS Service Name</a>.  Any fields you list under the 'params' parameter will be 
						passed into the nominated webservice, and the result of your query will be passed to the 'success' callback that you provide.  If something goes terribly wrong, the 
						'failure' callback will be invoked instead.</p>
			</g:else>
		</div>
	</body>
</html>
