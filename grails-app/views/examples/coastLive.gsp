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
			<h1>Coast Live!</h1>
			<p>Coast Live! is a native Android application that allows users to find live music events going on around the Sunshine Coast.   
			</p>
			<p>
				<img src="http://terra.suncoastpc.com.au:8181/data-gateway/images/coastlive_screenshot.png" style="width:480px;" />
			</p>
			<h1>Installation</h1>
			<p>You can download and install the Coast Live! application directly from the following URL:</p>
			<a href="http://terra.suncoastpc.com.au:8181/data-gateway/release/coastlive-0.1.apk" target="_blank">http://terra.suncoastpc.com.au:8181/data-gateway/release/coastlive-0.1.apk</a>
			<br />
			<br />
			<br />
			<p>Alternately, simply scan the QR code below from your Android device.</p>
			<img src="http://terra.suncoastpc.com.au:8181/data-gateway/images/coastlive_qr.jpg" />
			<h1>Source Code</h1>
			<p>Application source-code for the Coast Live! app can be found <a href="https://github.com/adam-roth/coastlive-app" target="_blank">on Github</a>, at:</p>
			<a href="https://github.com/adam-roth/coastlive-app" target="_blank">https://github.com/adam-roth/coastlive-app</a>			
			<br />
			<br />
			<h1>More Info/Submission Video</h1>
			<p>
				<iframe width="640" height="480" src="https://www.youtube.com/embed/rcjjCBC0xLc" frameborder="0" allowfullscreen></iframe>
			</p>
			<p>
				<ul style="position: relative; left: 20px;">
					<li><a href="http://terra.suncoastpc.com.au:8181/data-gateway/release/coastlive_slides.pptx" target="_blank">Download video slides and script</a></li>
				</ul>
			</p>
		</div>
	</body>
</html>
