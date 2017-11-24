<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Horizon Sync</title>
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
				display: none;
			}
			.logoSubtitle {
				display: none;
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
				margin: 2em 1em 1.25em 1em;
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
			input:invalid+span:after {
				content: '✖';
				padding-left: 5px;
				display: none;
			}
			
			input:valid+span:after {
				content: '✓';
				padding-left: 5px;
				display: none;
			}
			
			.venuePreview {
				width: 420px;
			}
			
			.venueZone {
				vertical-align: middle;
			}
			
			.venueZone select {
				min-width: 200px;
			}
			
			.zoneEditor input[type=number],
			.zoneEditor .color {
				max-width: 100px;
			}
			
			.zoneEditor .imageUrl,
			.zoneEditor .messageText {
				max-width: 180px;
			}
			
			.label {
				font-weight: bold;
				text-align: right;
				vertical-align: middle;
				max-width: 150px;
			}
			
			.ui-datepicker-calendar tr:hover {
				background-color: white;
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
			<h1>Events</h1>
			<p>Below are all the events that have been created, and some basic details for each one.  
			</p>
			<g:if test="${ ! events || events.size() < 1 }">
				<p>
					Oops.  It looks like there aren't any events yet.  You can create one below.
				</p>
			</g:if>
			<g:else>
				<table class="keysTable">
					<!-- FIXME:  list events here -->
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
			</g:else>
			<div class="eventBasicDetails eventMeta">
				<h1>New Event</h1>
				<table>
					<tr>
						<td class="label">Event Name</td>
						<td class="input">
							<input id="eventName" placeholder="name" required="required" />
						</td>
					</tr>
					<tr>
						<td class="label">Date</td>
						<td class="input">
							<input type="text" id="date" name="date" placeholder="event date" required="required" />
						</td>
					</tr>
					<tr>
						<td class="label">Time (hh:mm)</td>
						<td class="input">
							<select id="hour" name="hour"></select>:<select id="minute" name="minute"></select>
						</td>
					</tr>
					<tr>
						<td class="label">Timezone</td>
						<td class="input">
							<select id="timezone" name="timezone">
					            <option value="US/Hawaii">(GMT-10:00) Hawaii</option>
					            <option value="US/Alaska">(GMT-09:00) Alaska</option>
					            <option value="US/Pacific">(GMT-08:00) Pacific Time (US/Canada)</option>
					            <option value="US/Arizona">(GMT-07:00) Arizona</option>
					            <option value="US/Mountain">(GMT-07:00) Mountain Time (US/Canada)</option>
					            <option value="US/Central">(GMT-06:00) Central Time (US/Canada)</option>
					            <option value="US/Eastern">(GMT-05:00) Eastern Time (US/Canada)</option>
					            <option value="Canada/Atlantic">(GMT-04:00) Atlantic Time (Canada)</option>
					            <option value="Canada/Newfoundland">(GMT-03:30) Newfoundland</option>
					            <option value="America/Argentina/Buenos_Aires">(GMT-03:00) Buenos Aires</option>
					            <option value="Atlantic/Cape_Verde">(GMT-01:00) Cape Verde Is.</option>
					            <option value="Etc/GMT">(GMT+00:00) Greenwich Mean Time : Dublin, London</option>
					            <option value="Europe/Berlin">(GMT+01:00) Amsterdam, Berlin, Rome</option>
					            <option value="Europe/Athens">(GMT+02:00) Athens, Bucharest, Istanbul</option>
					            <option value="Asia/Baghdad">(GMT+03:00) Kuwait, Riyadh, Baghdad</option>
					            <option value="Europe/Moscow">(GMT+03:00) Moscow, St. Petersburg</option>
					            <option value="Asia/Tehran">(GMT+03:30) Tehran</option>
					            <option value="Asia/Muscat">(GMT+04:00) Abu Dhabi, Muscat</option>
					            <option value="Asia/Baku">(GMT+04:00) Baku</option>
					            <option value="Asia/Kabul">(GMT+04:30) Kabul</option>
					            <option value="Asia/Yekaterinburg">(GMT+05:00) Yekaterinburg</option>
					            <option value="Asia/Kolkata">(GMT+05:30) Chennai, Kolkata, Mumbai</option>
					            <option value="Asia/Kathmandu">(GMT+05:45) Kathmandu</option>
					            <option value="Asia/Novosibirsk">(GMT+06:00) Almaty, Novosibirsk</option>
					            <option value="Asia/Dhaka">(GMT+06:00) Astana, Dhaka</option>
					            <option value="Asia/Rangoon">(GMT+06:30) Yangon (Rangoon)</option>
					            <option value="Asia/Bangkok">(GMT+07:00) Bangkok, Hanoi, Jakarta</option>
					            <option value="Asia/Krasnoyarsk">(GMT+07:00) Krasnoyarsk</option>
					            <option value="Australia/Perth">(GMT+08:00) Perth</option>
					            <option value="Asia/Tokyo">(GMT+09:00) Osaka, Sapporo, Tokyo</option>
					            <option value="Asia/Yakutsk">(GMT+09:00) Yakutsk</option>
					            <option value="Australia/Adelaide">(GMT+09:30) Adelaide</option>
					            <option value="Australia/Brisbane" selected="selected">(GMT+10:00) Brisbane</option>
					            <option value="Australia/Sydney">(GMT+10:00) Melbourne, Sydney</option>
					            <option value="Asia/Magadan">(GMT+11:00) Magadan, Solomon Is.</option>
					            <option value="Pacific/Auckland">(GMT+12:00) Auckland, Wellington</option>
					            <option value="Pacific/Fiji">(GMT+12:00) Fiji, Kamchatka, Marshall Is.</option>
					        </select>
						</td>
					</tr>
					<tr>
						<td class="label">Location (lat, lng)</td>
						<td class="input">
							<!-- FIXME:  popup map and geocoder -->
							<input id="latitude" name="latitude" placeholder="latitude" required="required" />,<input id="longitude" name="longitude" placeholder="longitude" required="required" />
						</td>
					</tr>
					<tr>
						<td class="label">Venue Dimensions (meters)</td>
						<td class="input zones">
							<input id="radius" name="radius" type="number" step="50" min="50" max="5000" placeholder="size/radius (m)" list="defaultSizes" required />
					        <span class="validity"></span>
					        <datalist id="defaultSizes">
					          <option value="100">
					          <option value="250">
					          <option value="500">
					          <option value="1000">
					          <option value="2500">
					        </datalist>
					         <select id="shape" name="shape">
					            <option value="circle">Circular</option>
					            <option value="square">Square/Rectangle</option>
					        </select>
						</td>
					</tr>
					<tr>
						<td class="label">Audience Geometry (degrees)</td>
						<td class="input zones">
							<input id="audienceStart" name="audienceStart" type="number" step="1" min="0" max="360" placeholder="start" required /><span class="validity"></span>
							 to 
        					<input id="audienceEnd" name="audienceEnd" type="number" step="1" min="0" max="360" placeholder="end" required /><span class="validity"></span>
						</td>
					</tr>
					<tr>
						<td class="label">Num Zones</td>
						<td class="input zones">
							<select id="numZones" name="numZones">
					            <option value="1" selected="selected">1</option>
					            <option value="2">2</option>
					            <option value="3">3</option>
					            <option value="4">4</option>
					            <option value="5">5</option>
					            <option value="6">6</option>
					            <option value="7">7</option>
					            <option value="8">8</option>
					            <option value="9">9</option>
					            <option value="10">10</option>
					            <option value="11">11</option>
					            <option value="12">12</option>
					            <option value="13">13</option>
					            <option value="14">14</option>
					            <option value="15">15</option>
					            <option value="16">16</option>
					        </select>
						</td>
					</tr>
					<tr>
						<td class="label">Venue Preview</td>
						<td class="output preview">
							<canvas id="eventCanvas" width="400" height="400"></canvas>
						</td>
					</tr>
					<tr>
						<td class="submit" colspan="2">
							<input type="button" value="Create Event" onclick="createSyncEvent(this);"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="scriptEditor" style="display: none;">
				<h1>Event Script(s)</h1>
				<table>
					<tr>
						<td class="venuePreview">
						</td>
						<td class="venueZone">
							<select id="editZone" name="editZone">
			    			</select>
			    			<input type="button" value="Cancel" onclick="cancelEvent(this);" />
			    			<input type="button" value="Publish" onclick="publishEvent(this);" />
						</td>
					</tr>
				</table>
			    <h5>Zone <span class="zoneNumber">1</span> Script</h5>
			    <table class="zoneEditor keysTable">
			        <thead>
			            <tr>
			                <td>Timestamp (ms)</td>
			                <td>Duration (ms)</td>
			                <td>Color</td>
			                <td>Image</td>
			                <td>Text</td>
			                <td>Effect</td>
			            </tr>
			        </thead>
			        <tbody>
			            <tr class="templateRow" style="display: none;">
			                <td><input class="startTime" type="number" step="10" min="0" readonly="readonly" /></td>
			                <td><input class="duration" type="number" step="10" min="100" /></td>
			                <td>
			                	<input class="color" type="text" placeholder="screen color" />
			                </td>
			                <td><input class="imageUrl" type="text" placeholder="image url" /></td>
			                <td><input class="messageText" type="text" placeholder="text message" /></td>
			                <td>
			                    <select class="effect">
			                        <option value="-1">[None]</option>
			                        <option value="flash_100">Flash</option>
			                        <option value="flicker">Flicker</option>
			                        <option value="pulse">Pulse</option>
			                    </select>
			                </td>
			            </tr>
			        </tbody>
			    </table>
			    <input type="button" value="New Row" onclick="addNewRow(this);" style="margin-left: 20px;" />
			</div>
		</div>
		<script>
			window.createSyncEvent = function(button) {
				//transition to UI for configuring the script for each zone
				var name = $("#eventName").val();
				var date = $("#date").val();
				var zones = parseInt($("#numZones").val(), 10);

				var latitude = parseFloat($("#latitude").val());
				var longitude = parseFloat($("#longitude").val());

				var shape = $("#shape").val();
			    var startDeg = parseInt($("#audienceStart").val(), 10);
			    var stopDeg = parseInt($("#audienceEnd").val(), 10);
			    var meters = parseInt($("#radius").val(), 10);

				//validate inputs
				if (! name || name.length < 1) {
					alert("Please enter an event name!");
					return;
				}

				if (! date.match(/[0-9]+\/[0-9]+\/[0-9]+/) || date.match(/[0-9]+\/[0-9]+\/[0-9]+/)[0] != $("#date").val()) {
					alert("Please select a valid date!");
					return;
				}

			    if (zones < 1 || zones > 16) {
			    	alert("Please select a valid number of zones!");
					return;
				}

				if (isNaN(latitude) || isNaN(longitude) || latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
					alert("Please select a valid latitude/longitude!");
					return;
				}

				if (shape != "circle" && shape != "square") {
			    	alert("Please select a valid venue shape!");
					return;
				}

				if (! meters || isNaN(meters) || meters < 50 || meters > 5000) {
					alert("Please provide a valid venue size!");
					return;
				}

				if (isNaN(startDeg) || isNaN(stopDeg) || startDeg < 0 || startDeg > 360 || stopDeg < 0 || stopDeg > 360) {
					alert("Please provide a valid audience geometry!");
					return;
				}


			    //all valid, transition to the script editor
			    window.scripts = [];
			    for (var zone = 0; zone < zones; zone++) {
			    	scripts.push({id: zone, events:[]});
			        $("#editZone").append("<option value='" + zone + "'>Zone " + (zone + 1) + "</option>");
			    }
			    
			    $("#editZone").val("0");
			    drawZoneScript($("#editZone")[0]);

			    $(".scriptEditor").show();
			    $(".eventBasicDetails").hide();

			    $(".venuePreview").append($("#eventCanvas"));
			};
	
			window.balanceTimestamps = function() {
				var currentTime = 0;
			    $(".zoneEditor .scriptRow").each(function() {
			    	var duration = parseInt($(this).find(".duration").val(), 10);
			        if (! duration || isNaN(duration) || duration < 100) {
			        	duration = 100;
			        }
			        
			        $(this).find(".duration").val(duration);
			        $(this).find(".startTime").val(currentTime);
			        
			        currentTime += duration;
			    });
			};
	
			window.saveScript = function() {
				var script = scripts[$("#editZone").val()];
	
				var events = [];
			    $(".zoneEditor .scriptRow").each(function() {
			    	var event = {};
			        event.zone = $("#editZone").val();
			        event.time = parseInt($(this).find(".startTime").val(), 10);
			        event.duration = parseInt($(this).find(".duration").val(), 10);
			        event.color = $(this).find(".color").val();
			        event.image = $(this).find(".imageUrl").val();
			        event.message = $(this).find(".messageText").val();
			        
			        events.push(event);
			    });
			    
			    script.events = events;
			};

			window.cancelEvent = function(button) {
				//discard state and go back to the starting UI
				if (confirm("Are you sure you want to discard the current script and start over?")) {
					$(".scriptEditor").hide();
			    	$(".eventBasicDetails").show();

			    	$(".output.preview").append($("#eventCanvas"));
				}
			};
	
			window.publishEvent = function(button) {
				//collect all data and post to server
				var params = {};
				
				//basic details
				params.name = $("#eventName").val();
				params.date = $("#date").val();
				params.zones = parseInt($("#numZones").val(), 10);

				//location
				params.latitude = parseFloat($("#latitude").val());
				params.longitude = parseFloat($("#longitude").val());

				//geometry
				params.shape = $("#shape").val();
			    params.startDeg = parseInt($("#audienceStart").val(), 10);
			    params.stopDeg = parseInt($("#audienceEnd").val(), 10);
			    params.meters = parseInt($("#radius").val(), 10);
			    if (params.startDeg > params.stopDeg) {
			    	var temp = startDeg;
			        params.startDeg = stopDeg;
			        params.stopDeg = temp;
			    }
			    
			    //time
			    params.hour = $("#hour").val();
			    params.minute = $("#minute").val();
			    params.timezone = $("#timezone").val();
			    
			    //collect the script
			    params.scripts = JSON.stringify(scripts);
			    
			    //snapshot of the configuration
			    params.image = $("#eventCanvas")[0].toDataURL("image/png");
			    
			    //FIXME:  post to server, refresh page if/when successful
			    console.log(params);
				
			};
			
			window.setupColorPicker = function(row) {
				row.find(".color").ColorPicker({
					onSubmit: function(hsb, hex, rgb, el) {
						$(el).val(hex);
						$(el).ColorPickerHide();
					},
					onBeforeShow: function () {
						$(this).ColorPickerSetColor(this.value);
					},
					onChange: colorOnChange(row.find(".color"))
				})
				.bind('keyup', function(){
					$(this).ColorPickerSetColor(this.value);
				});
			};
	
			window.addNewRow = function(button) {
				var lastRow = $(".zoneEditor .scriptRow:last");
			    var currentTime = parseInt(lastRow.find(".startTime").val(), 10);
			    currentTime += parseInt(lastRow.find(".duration").val(), 10);
	
			    //draw a new row at the end of the table
			    var row = $(".templateRow").clone();
			    row.addClass("scriptRow");
			    row.removeClass("templateRow");
			    
			    row.find(".startTime").val(currentTime);
			    row.find(".duration").val(100);
			    
			    $(".zoneEditor tbody").append(row);
			    $(".scriptRow").show();
			    
			    setupColorPicker(row);
			    
			    saveScript();
			};
			
			window.colorOnChange = function(el) {
				return function(hsb, hex, rgb) {
					$(el).val(hex);
					$(el).css("border-color", "#" + hex);
				};
			};
	
			window.drawZoneScript = function(select) {
				//console.log(scripts, select, $(select).val())
				var script = scripts[$(select).val()];
			    var events = script.events;
			    
			    //clear the table content so that we can redraw
			    $(".scriptRow").remove();
			    
			    //draw UI based upon the script content
			    var currentTime = 0;
			    for (var index = 0; index < events.length; index++) {
			    	var event = events[index];
			        var time = event.time;
			        var duration = event.duration;
			        
			        var row = $(".templateRow").clone();
			        row.addClass("scriptRow");
			    	row.removeClass("templateRow");
			        
			        row.find(".startTime").val(currentTime);
			    	row.find(".duration").val(duration);
			        
			        currentTime += duration;
			        $(".zoneEditor tbody").append(row);
			        
			        setupColorPicker(row);
			    }
			    
			    if (events.length == 0) {
			    	//draw a new row at the end of the table
			    	var row = $(".templateRow").clone();
			    	row.addClass("scriptRow");
			    	row.removeClass("templateRow");
			    
			    	row.find(".startTime").val(currentTime);
			    	row.find(".duration").val(100);
			    
			    	$(".zoneEditor tbody").append(row);
			    	
			    	setupColorPicker(row);
			    }
			    
			    $(".scriptRow").show();
			};
	
			window.toRad = function(degrees) {
				var pi = Math.PI;
			  	return degrees * (pi / 180);
			};
	
			window.pad = function(item) {
				item += "";
			    return item.length != 1 ? item : "0" + item;
			}
	
			$(document).ready(function() {
				for (var hour = 0; hour < 24; hour++) {
			    	$("#hour").append("<option value='" + hour + "'>" + pad(hour) + "</option>");
			    }
			    for (var minute = 0; minute < 60; minute++) {
			    	$("#minute").append("<option value='" + pad(minute) + "'>" + pad(minute) + "</option>");
			    }
	
				$(".zones select,.zones input").on("change keyup", function(){ 
			    	drawSyncEvent($("#eventCanvas")[0]);
			    });
			    
			    $(document).on("change keyup", ".zoneEditor select,.zoneEditor input", function(){ 
			    	balanceTimestamps();
			        saveScript();
			    });
			    
			    $("#editZone").on("change keyup", function() {
				    $(".zoneNumber").html(parseInt($(this).val(), 10) + 1);
			        drawZoneScript(this);
			    });
			    
			    //datepicker
			    $("#date").datepicker({
					dateFormat: "d/mm/yy",
					minDate: 0
				});
			    
			    //starting canvas state
			    drawSyncEvent($("#eventCanvas")[0]);
			});


			//XXX:  canvas drawing code only below here
			window.drawSyncEvent = function(canvas) {
				//get graphics context
			    var context = canvas.getContext("2d");
			    
			    var centerX = canvas.width / 2;
			    var centerY = canvas.height / 2;
			    
			    //clear the canvas of any previous content
			    context.beginPath();
			    context.clearRect(0, 0, canvas.width, canvas.height);
			    
			    //see if we have anything that we can draw (looking only at shape, degrees, and number of zones)
			    var shape = $("#shape").val();
			    var startDeg = parseInt($("#audienceStart").val(), 10);
			    var stopDeg = parseInt($("#audienceEnd").val(), 10);
			    var zones = parseInt($("#numZones").val(), 10);
			    var meters = parseInt($("#radius").val(), 10);
			    if (startDeg > stopDeg) {
			    	var temp = startDeg;
			        startDeg = stopDeg;
			        stopDeg = temp;
			    }
			    
			    //draw some hints onto the canvas
			    context.fillStyle = "#000000";
			    context.strokeStyle = "#000000";
			    context.font = "bold 10pt Arial sans";
			    context.lineWidth = 4;
			    var maxDistance = (canvas.width < canvas.height ? canvas.width / 2 : canvas.height / 2) - (context.lineWidth * 5);
			    
			    context.fillText("0\xB0", centerX + maxDistance + 4, centerY + 3);
			    context.fillText("180\xB0", centerX - maxDistance - 23, centerY + 3);
			    context.fillText("90\xB0", centerX - 8, centerY + maxDistance + 12);
			    context.fillText("270\xB0", centerX - 10, centerY - maxDistance - 6);
			    
			    //see if we can draw a scale into the graphic
			    if (meters > 0 && ! isNaN(meters) && shape == "circle") {
			        context.lineWidth = 1;
			        
			        context.beginPath();
			        context.moveTo(centerX + maxDistance + 15, centerY - maxDistance);
			        context.lineTo(centerX + maxDistance + 19, centerY - maxDistance);
			        context.lineTo(centerX + maxDistance + 19, centerY + maxDistance);
			        context.lineTo(centerX + maxDistance + 15, centerY + maxDistance);
			        context.stroke();
			        
			        var text = (meters * 2) + "m";
			        context.fillText(text, centerX + maxDistance + 12 - text.length * 7, centerY + maxDistance - 15);
			        
			        context.lineWidth = 4;
			    }
			    
			    if ((shape != "circle" && shape != "square") || isNaN(startDeg) || isNaN(stopDeg) || isNaN(zones) || startDeg < 0 || startDeg > 360 || stopDeg < 0 || stopDeg > 360 || zones < 1 || zones > 16) {
			    	//invalid settings; cannot draw
			        return;
			    }
			    
			    if (shape == "circle") {
			    	//draw the active area
			        context.beginPath();
			        context.strokeStyle = "#008800";
			        context.fillStyle = "#008800";
			        context.arc(canvas.width / 2, canvas.height / 2, maxDistance, toRad(startDeg), toRad(stopDeg));
			        context.stroke();
			        
			        //fill the active area
			        context.beginPath();
			        context.lineWidth = 1;
			        context.moveTo(canvas.width / 2, canvas.height / 2);
			        context.lineTo(canvas.width / 2 + Math.cos(toRad(startDeg)) * maxDistance, canvas.height / 2 + Math.sin(toRad(startDeg)) * maxDistance);
			        context.arc(canvas.width / 2, canvas.height / 2, maxDistance, toRad(startDeg), toRad(stopDeg));
			        context.lineTo(canvas.width / 2, canvas.height / 2);
			        context.fill();
			        
			        //draw the inactive area(s)
			        context.strokeStyle = "#ff0000";
			        if (startDeg != 0) {
			        	console.log("Clear to start", startDeg);
			        
			        	context.beginPath();
			        	context.arc(canvas.width / 2, canvas.height / 2, maxDistance, toRad(0), toRad(startDeg));
			        	context.stroke();
			        }
			        
			        if (stopDeg != 360) {
			        	console.log("Clear to stop", stopDeg);
			        
			        	context.beginPath();
			        	context.arc(canvas.width / 2, canvas.height / 2, maxDistance, toRad(stopDeg), toRad(360));
			        	context.stroke();
			        }
			        
			        //mark each zone
			        context.beginPath();
			        
			        context.strokeStyle = "#000000";
			        
			        context.moveTo(canvas.width / 2, canvas.height / 2);
			        context.lineTo(canvas.width / 2 + Math.cos(toRad(startDeg)) * maxDistance, canvas.height / 2 + Math.sin(toRad(startDeg)) * maxDistance);
			        context.moveTo(canvas.width / 2, canvas.height / 2);
			        context.lineTo(canvas.width / 2 + Math.cos(toRad(stopDeg)) * maxDistance, canvas.height / 2 + Math.sin(toRad(stopDeg)) * maxDistance);
			        context.stroke();
			        
			        var degStep = (stopDeg - startDeg) / zones;
			        if (zones > 1) {
			            for (var zone = 1; zone < zones; zone++) {
			            	var pos = startDeg + (degStep * zone);
			                context.moveTo(canvas.width / 2, canvas.height / 2);
			        		context.lineTo(canvas.width / 2 + Math.cos(toRad(pos)) * maxDistance, canvas.height / 2 + Math.sin(toRad(pos)) * maxDistance);
			                context.stroke();
			            }
			        }
			        
			        //text label for each zone
			        var halfStep = degStep / 2;
			        var textPos = startDeg + halfStep;
			        var textDistance = maxDistance + 15;
			        for (var zone = 1; zone <= zones; zone++) {
			            context.fillStyle = "#efefef";
			            context.fillRect(canvas.width / 2 + Math.cos(toRad(textPos)) * textDistance - 8, 
			            				 canvas.height / 2 + Math.sin(toRad(textPos)) * textDistance - 12, 16, 16);
			            
			            context.fillStyle = "#000000";
			            context.fillText(zone + "", 
			                canvas.width / 2 + Math.cos(toRad(textPos)) * textDistance - 4, canvas.height / 2 + Math.sin(toRad(textPos)) * textDistance);
			            textPos += degStep;
			        }
			    }
			    else {
			    	//square-style venue
			        if (stopDeg == 360 && startDeg < 359) {
			        	//XXX:  hack
			            stopDeg = 359;
			        }
			        
			        var leftX = centerX - maxDistance;
			        var rightX = centerX + maxDistance;
			        var topY = centerY - maxDistance;
			        var bottomY = centerY + maxDistance;
			        
			        //start by marking the entire area as inactive
			        context.beginPath();
			        context.lineWidth = 1;
			        context.strokeStyle = "#ff0000";
			        context.rect(leftX, topY, maxDistance * 2, maxDistance * 2);
			        context.stroke();
			        
			        //now overwrite with the active area
			        context.beginPath();
			        context.lineWidth = 4;
			        context.strokeStyle = "#008800";
			        context.fillStyle = "#008800";
			        
			        context.moveTo(centerX, centerY);
			        var startPos = boundsIntercept(startDeg, centerX, centerY, maxDistance);
			        var stopPos = boundsIntercept(stopDeg, centerX, centerY, maxDistance);
			        
			        var minX = Math.min(startPos.x, stopPos.x);
			        var maxX = Math.max(startPos.x, stopPos.x);
			        var minY = Math.min(startPos.y, stopPos.y);
			        var maxY = Math.max(startPos.y, stopPos.y);
			        
			        //context.lineTo(startPos.x, startPos.y);
			        //context.stroke();
			        
			       	//now trace a path from the start position to the stop position
			        var numMoves = 0;
			        while (startPos.x != stopPos.x || startPos.y != stopPos.y) {
			        	if (startPos.x == rightX && startPos.y >= centerY && startPos.y != bottomY) {
			            	//right side, midline or below
			                if (startPos.x == stopPos.x && stopPos.y >= centerY) {
			                	//circuit completed
			                    startPos.y = stopPos.y;
			                }
			                else {
			                	startPos.y= bottomY;
			                }
			            }
			            else if (startPos.x == rightX && startPos.y >= centerY) {
			            	//bottom-right corner
			                startPos.x = stopPos.y == startPos.y ? stopPos.x : leftX;
			            }
			            else if (startPos.y == bottomY && startPos.x != leftX) {
			            	//bottom side, excluding corners
			            	startPos.x = stopPos.y == startPos.y ? stopPos.x : leftX;
			            }
			            else if (startPos.y == bottomY) {
			            	//bottom-left corner
			                startPos.y = stopPos.x == startPos.x ? stopPos.y : topY;
			            }
			            else if (startPos.x == leftX && startPos.y != topY) {
			            	//left edge, excluding corners
			                startPos.y = stopPos.x == startPos.x ? stopPos.y : topY;
			            }
			            else if (startPos.x == leftX) {
			            	//top-left corner
			                startPos.x = stopPos.y == startPos.y ? stopPos.x : rightX;
			            }
			            else if (startPos.y == topY && startPos.x != rightX) {
			            	//top edge, excluding corners
			                startPos.x = stopPos.y == startPos.y ? stopPos.x : rightX;
			            }
			            else if (startPos.y == topY) {
			            	//top-right corner
			                startPos.y = stopPos.y;
			                if (startPos.x != stopPos.x) {
			                	console.log("Completed a circuit without finding the stop point?!?", startPos, stopPos);
			                    break;
			                }
			            }
			            else {
			            	console.log("Unsupported coordinate!?!", startPos, stopPos);
			                break;
			            }
			            
			            numMoves++;
			            if (numMoves > 100) {
			            	console.log("Loop detected!", startPos, stopPos);
			                break;
			            }
			            
			            minX = Math.min(minX, startPos.x);
			            maxX = Math.max(maxX, startPos.x);
			            minY = Math.min(minY, startPos.y);
			            maxY = Math.max(maxY, startPos.y);
			            
			            //context.lineTo(startPos.x, startPos.y);
			        	//context.stroke();
			        }
			    }
			    
			    //context.lineTo(centerX, centerY);
			    //context.stroke();
			    //context.fill();
			    
			    minY = Math.min(minY, centerY);
			    
			    context.fillRect(minX, minY, maxX - minX, maxY - minY);
			    context.rect(minX, minY, maxX - minX, maxY - minY);
			    context.stroke();
			    
			    //draw the zones
			    context.lineWidth = 1;
			    context.strokeStyle = "#000000";
			    context.fillStyle = "#000000";
			    
			    context.beginPath();
			    var hSpan = maxX - minX;
			    var vSpan = maxY - minY;
			    if (hSpan > vSpan) {
			    	//vertical zones
			        context.moveTo(maxX, minY);
			        context.lineTo(maxX, maxY);
			        context.moveTo(minX, minY);
			        context.lineTo(minX, maxY);
			        
			        var zoneSize = hSpan / zones;
			        if (zones > 1) {
			            for (var zone = 1; zone < zones; zone++) {
			            	context.moveTo(maxX - (zone * zoneSize), minY);
			                context.lineTo(maxX - (zone * zoneSize), maxY);
			            }
			        }
			        
			        //label the zones
			        var halfStep = zoneSize / 2;
			        var pos = maxX - halfStep;
			        for (var zone = 1; zone <= zones; zone++) {
			        	context.fillStyle = "#efefef";
			            context.fillRect(pos - 14, maxY + 3, 16, 16);
			            
			            context.fillStyle = "#000000";
			            context.fillText(zone + "", pos - 6, maxY + 15);
			            pos -= zoneSize;
			        }
			    }
			    else {
			    	//horizontal zones
			        context.moveTo(minX, minY);
			        context.lineTo(maxX, minY);
			        context.moveTo(minX, maxY);
			        context.lineTo(maxX, maxY);
			        
			        var zoneSize = vSpan / zones;
			        if (zones > 1) {
			            for (var zone = 1; zone < zones; zone++) {
			            	context.moveTo(minX, minY + (zone * zoneSize));
			                context.lineTo(maxX, minY + (zone * zoneSize));
			            }
			        }
			        
			        //label the zones
			        var halfStep = zoneSize / 2;
			        var pos = minY + halfStep;
			        for (var zone = 1; zone <= zones; zone++) {
			            context.fillText("" + zone, maxX + 4, pos + 6);
			            pos += zoneSize;
			        }
			    }
			    context.stroke();
			    
			    //draw the 'stage'
			    context.beginPath();
			    context.lineWidth = 4;
			    context.fillStyle = "#0000ff";
			    context.arc(canvas.width / 2, canvas.height / 2, 5, 0, 2 * Math.PI);
			    context.fill();
			    
			    //text label for the stage
			    context.fillText("Stage", canvas.width / 2 + 6, canvas.height / 2 - 6);
			};
	
			window.boundsIntercept = function(angle, centerX, centerY, maxDistance) {
				var result = {};
			    var startX;
			    var startY;
			    
			    var leftX = centerX - maxDistance;
			    var rightX = centerX + maxDistance;
			    var topY = centerY - maxDistance;
			    var bottomY = centerY + maxDistance;
			    
			    if (angle % 90 != 0) {
			        //all other values
			        var circX = centerX + Math.cos(toRad(angle)) * maxDistance;
			        var circY = centerY + Math.sin(toRad(angle)) * maxDistance;
			        var slope = (circY - centerY) / (circX - centerX);
			        var slopeInv = (circX - centerX) / (circY - centerY);
	
			        if (angle >= 315 || angle <= 45) {
			            startX = rightX;
			            startY = circY + slope * (rightX - circX);
			        }
			        else if (angle >= 135 && angle <= 225) {
			            startX = leftX;
			            startY = circY + slope * (leftX - circX);
			        }
			        else if (angle > 45 && angle < 135) {
			            startY = bottomY;
			            startX = circX + slopeInv * (bottomY - circY);
			        }
			        else {
			            startY = topY;
			            startX = circX + slopeInv * (topY - circY);
			        }
			    }
			    else if (angle % 180 == 0) {
			        //0, 180, 360
			        startY = centerY;
			        startX = angle == 0 || angle == 360 ? rightX : leftX;
			    }
			    else {
			        //90, 270
			        startX = centerX;
			        startY = angle == 90 ? bottomY : topY;
			    }
			    
			    result.x = startX;
			    result.y = startY;
			    
			    return result;
			};
		</script>
	</body>
</html>
