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
			<h1>HackFest Data Gateway</h1>
			<p>The HackFest Data Gateway exists to provide a centralized access-point and consistent API into various external 
			   datasets.  It provides a layer of abstraction between the type of data being requested, and the data itself.  This allows, 
			   for instance, for data from multiple independent sources to be collated and returned as a single result-set.
			</p>
			<p>The gateway also provides a JavaScript API client that web-based applications can use to access various datasets without 
			   needing to worry about same-origin policy or creating a bespoke server to sit in between the browser and the data.
			</p>
			<p>Normally you'd have to register an account before being able to do anything useful, and your API key would be private.  But considering the
			   time-constraints involved it wouldn't make sense to implement proper registration, authentication, 
			   password-recovery and related flows.  
			</p>
			<p>So for now, just grab an API key and get back to hacking!
			</p>

			<div id="controller-list" role="navigation">
				<h2>Available Actions:</h2>
				<ul>
					<li class="controller"><g:link controller="keys" action="index">View API Keys</g:link></li>
					<li class="controller"><g:link controller="keys" action="newKey">Create API Key</g:link></li>
				</ul>
			</div>
			<p>&nbsp;</p>
			<h1>Source Code</h1>
			<p>The code for this project can found on Github, at the following URL:
			</p>
			<a href="https://github.com/adam-roth/hackfest-data-gateway" target="_blank">https://github.com/adam-roth/hackfest-data-gateway</a>
			<h1>Binaries</h1>
			<p>Have your own <a href="http://tomcat.apache.org/" target="_blank">servlet container</a>?  Great!  You can download a prebuilt copy of the HackFest Data Gateway below:
			</p>
			<a href="${createLink(uri: '/release/data-gateway.war')}" target="_blank">${createLink(uri: '/release/data-gateway.war', absolute:true)}</a>
		</div>
	</body>
</html>
