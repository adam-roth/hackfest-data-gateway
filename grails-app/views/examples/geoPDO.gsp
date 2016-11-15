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
			
			#mapDiv {
			    width: 600px;
			    max-width: 600px;
			    height: 400px;
			    margin-top: 10px;
			    margin-bottom: 10px;
			}
			.output {
			    max-width: 580px;
			    padding: 10px;
			    border: 1px dashed black;
			    font-size: 10px;
			    word-wrap: break-word;
			    margin-top: 20px;
			    position: relative;
			    left: 5px;
			}
			#status h1 {
				margin-bottom: -8px;
			}
			#status select {
				max-width: 200px;
				font-size: 12px;
			}
			#status select, #status input {
				margin-bottom: 2px;
			}
			#status select.context, #status select.range {
				margin-bottom: 20px;
			}
			#status input {
				font-size: 12px;
			}
		</style>
		<script>
			window.startingCoord = {lat: -26.6522586, lng: 153.0906231};		//HackFest HQ
			window._lastGeocode = startingCoord;
		    if (navigator.geolocation) {
		        /*navigator.geolocation.getCurrentPosition(function(pos) {
		            //console.log("Got position from browser", pos)
		            startingCoord = {lat: pos.coords.latitude, lng: pos.coords.longitude};
		            window._lastGeocode = startingCoord;
		            
		            if (window.map) {
		                map.setCenter(startingCoord);
		            }

				    if (window.jQuery) {
						//FIXME:  jQuery may not be ready yet
		            	jQuery(".location").html(startingCoord.lat + ", " + startingCoord.lng);
				    }
		        });*/
		    }

			window._mapsReady = false;
		    window.mapsAvailable = function() {
				_mapsReady = true;
			};

		    window.initMap = function() {
				if (! _mapsReady) {
					setTimeout(initMap, 1000);
					return;
				}
			    
		        window.baseIcon = {};
		        baseIcon.url = "http://terra.suncoastpc.com.au:8181/data-gateway/images/map/marker.png";
		        baseIcon.size = new google.maps.Size(12, 20);
		        baseIcon.anchor = new google.maps.Point(6, 20);

		        window.hoverIcon = {};
		        hoverIcon.url = "http://terra.suncoastpc.com.au:8181/data-gateway/images/map/marker_hover.png?rnd=1";
		        hoverIcon.size = new google.maps.Size(12, 20);
		        hoverIcon.anchor =  new google.maps.Point(6, 20);
		    
		        window.map = new google.maps.Map(document.getElementById('mapDiv'), {
		            center: startingCoord,
		            zoom: 11
		        });

		        //pick a random dataset
				var options = $("#datasetSelect option");
				var randomIndex = Math.floor(Math.random() * options.length);
				$(options[randomIndex]).attr("selected", "selected");

				window.geocoder = new google.maps.Geocoder();
				
				runSearch(true);
		    };
		</script>
		
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvwJUP-fs5XdY7PMBHCUgcJKGbtrVXkGU&callback=mapsAvailable" async defer></script>
		<!-- XXX:  Development key:  http://localhost:8080/data-gateway/api/client?apiKey=25c22cc227a44505a2e756f37cef9bb0 -->
		<!-- PRODUCTION:  -->
			<script src='http://terra.suncoastpc.com.au:8181/data-gateway/api/client?apiKey=a3579c0b937f483c9b52247544d4bb5a'></script>
		<!-- DEVELOPMENT:
			<script src='http://localhost:8080/data-gateway/api/client?apiKey=e4335a64660e40b1826ab61296bb0a26'></script>
		-->
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>Search Type</h1>
			<div>
				<select class="context">
					<option value="PlanningCadastre/Applications_SCRC/MapServer/0" selected="true">Development Applications</option>
					<option value="PlanningCadastre/Applications_SCRC/MapServer/2">Building Applications</option>
					<!-- XXX:  2015 values
				    <option value="Staging/Applications_SCRC/MapServer/2" selected="true">Development Applications</option>
				    <option value="Staging/Applications_SCRC/MapServer/5">Building Applications</option>
				    -->
				</select>
			</div>
			
			<h1>Search Area</h1>
			<div>
				<input type="text" placeholder="location" class="location" value="-26.6522586, 153.0906231" onchange="window._lastGeocode = undefined;" />
			</div>
			<div>
				<select class="range">
				    <option value="0.1" selected="true">Within 100m</option>
				    <option value="0.25">Within 250m</option>
				    <option value="0.5" selected="true">Within 500m</option> 
				    <option value="1">Within 1km</option>
				    <option value="2">Within 2km</option>
				    <option value="5">Within 5km</option>
				    <option value="10">Within 10km</option>
				</select>
			</div>
			<!-- 
			<div>
				<select class="timespan">
				    <option value="7">Past Week</option>
				    <option value="14">Past Fortnight</option>
				    <option value="30">Past Month</option>
				    <option value="90">Past 3 Months</option>
				    <option value="180">Past 6 Months</option>
				    <option value="365" selected="true">Past Year</option>
				</select>
			</div>
			 -->
			
			<h1>Search Options</h1>
			<div>
				<input type="text" placeholder="[application #]" class="appId" />
			</div>
			<div>
				<input type="text" placeholder="[keyword]" class="keyword" />
			</div>
			
			<div>
				<input type="button" onclick="runSearch(true);" value="Search" />
			</div>
		</div>
		<div id="page-body" role="main">
			<h1>GeoPDO</h1>
			<p>This application allows for searches to be run along similar lines as the existing <a href="http://pdonline.sunshinecoast.qld.gov.au/MasterView/Modules/Applicationmaster/Default.aspx" target="_blank">PD Online</a> system, 
			but with a geographic component applied to the search, making it easier to discover new projects without prior knowledge and to answer the question of "what's going on around me?".
			</p>
			<div id="mapDiv"> 
			</div>
			<div class="output" style="display: none;">
				<!-- FIXME:  rewire to display output list; coordinate with map -->
			</div>
		</div>
		<script>
			//FIXME:  [add info-windows on click]
			//FIXME:  [add marker to show center of search]
			//FIXME:  [add different marker type for items with comments]
			
			//FIXME:  submit project(s), with videos
			//FIXME:  review photos for any other easy extensions

			//create a reusable client instance
			window.client = new gateway.Client();
			window.searchContext = gateway.datasets["DevelopmentApps - All"];

			//brightwater; bottom-left=-26.714226,153.09968
			//brightwater; top-right=-26.701115,153.115816
			
			window.measure = function(lat1, lon1, lat2, lon2){  // generally used geo measurement function
			    var R = 6378.137; // Radius of earth in KM
			    var dLat = (lat2 - lat1) * Math.PI / 180;
			    var dLon = (lon2 - lon1) * Math.PI / 180;
			    var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
			    Math.sin(dLon/2) * Math.sin(dLon/2);
			    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			    var d = R * c;
			    return d * 1000; // meters
			};
			
			window.boundingBox = function(lat, lng, targetKm) {
				var kmPer1Lat = measure(lat, lng, lat + 1, lng) / 1000.0;
			    var kmPer1Lng = measure(lat, lng, lat, lng + 1) / 1000.0;
			    
			    console.log(kmPer1Lat, kmPer1Lng, targetKm, lat, lng);
			    
			    var dLatPerKm = 1 / kmPer1Lat;
			    var dLngPerKm = 1 / kmPer1Lng;
			    
			    var dLat = dLatPerKm * (targetKm / 2);
			    var dLng = dLngPerKm * (targetKm / 2);
			    
			    var lat1 = lat + dLat;
			    var lat2 = lat - dLat;
			    var lng1 = lng + dLng;
			    var lng2 = lng - dLng;
			    
			    //minLon,minLat,maxLon,maxLat
			    return [
			        Math.min(lng1, lng2),
			        Math.min(lat1, lat2),
			        Math.max(lng1, lng2),
			        Math.max(lat1, lat2)
			    ];
			};

			window.geocodeAddress = function() {
			    var address = $(".location").val();
			    geocoder.geocode( { 'address': address, bounds: map.getBounds(), region: "aus"}, function(results, status) {
			    	if (status == google.maps.GeocoderStatus.OK) {
			        	map.setCenter(results[0].geometry.location);
			            window._lastGeocode = {lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()};
			        }
			        else {
			        	alert("The location provided could not be geocoded: " + status);
			            $(".location").val(map.getCenter().lat() + ", " + map.getCenter().lng());
			            window._lastGeocode = {lat: map.getCenter().lat(), lng: map.getCenter().lng()};
			        }
			        
			        runSearch(true);
			    });
			};
	
			window.runSearch = function(zoom) {
				if (! window._lastGeocode) {
			    	//call google and attempt to geocode; fall back to map center if we cannot get a valid geocode
			        geocodeAddress();
			        return;
			    }
				
			    var dataset = $(".context option:selected").val();
			    //if (dataset == -1) {
			    //	dataset = gateway.datasets["DevelopmentApps - All"];
			    //}
			    
			    //FIXME:  should drop a unique marker for the search location
			    
			    $(".output").hide();
			    $(".output").html("");
			    
			    //get some data to work with
			    //can search within bounding box like:  inSR=4326&geometry=153.09968,-26.714226,153.10,-26.701115&geometryType=esriGeometryEnvelope
			    //												           minLon,minLat,maxLon,maxLat
			    var geomCoords = boundingBox(_lastGeocode.lat, _lastGeocode.lng, parseFloat($(".range option:selected").val()));
			    console.log(geomCoords);
			    
			    //can match keywords like: where=DESCRIPTION LIKE '%<keyword>%'
			    //can match project-id like:  where ram_id='<pid>'
			    //can combine above constraints using 'and'
			    var where = "1=1";
			    var appId = $(".appId").val();
			    var keyword = $(".keyword").val();
			    if (appId.length > 1 && keyword.length > 1) {
			    	where = "ram_id='" + appId + "' and DESCRIPTION LIKE '%" + keyword + "%'";
			    }
			    else if (appId.length > 1) {
			    	where = "ram_id='" + appId + "'";
			    }
			    else if (keyword.length > 1) {
			    	where = "DESCRIPTION LIKE '%" + keyword + "%'";
			    }
			    
			    var params = {
			        inSR: "4326",
			        geometryType: "esriGeometryEnvelope",
			        geometry: geomCoords.join(","),
			        orderByFields: "D_Date_Rec DESC",			//can prefer newer items by sending: orderByFields=D_Date_Rec+DESC
			        where: where,
			        f: "pjson"
			    };
			    
			    client.loadData({
			        context: dataset,
			        params: params,
			        success: function(data) {
			            console.log(data);
			            //$(".output").html(JSON.stringify(data));
			            //$(".output").show();
			            
			            if (window.map) {
			            	drawDataOnMap(data, map, dataset);
			            	
			            	if (zoom) {
			            		zoomToFit(map, currentMarkers);
			            	}
			            }
			        },
			        failure: function(errMsg) {
			            alert(errMsg);
			        }
			    });
			};
	
			window.markerMap = {};
			window.markerRamMap = {};
			window.currentMarkers = [];
			window.drawDataOnMap = function(data, map, dataset) {
				//first, clear any overlays that currently exist
			    while (currentMarkers.length > 0) {
			    	currentMarkers.pop().setMap(null);
			    }
			    markerMap = {};
			    markerRamMap = {};
			    
			    //timespan we have to apply ourselves because ArcGis api is doing crazy things...
			    var daysBack = $(".timespan option:selected").val();
			    
			    var cutoffDate = new Date();
			 	cutoffDate.setDate(cutoffDate.getDate() - daysBack);
			    
			    //only show entries timestamped later than this
			    var cutoffTime = cutoffDate.getTime();
			    
			   
			    
			    //add new markers for the current dataset
			    for (var index = 0; index < data.length; index++) {
			        //can use ram_process_ctr to link back to PDO, like:  http://pdonline.sunshinecoast.qld.gov.au/MasterView/Modules/Applicationmaster/default.aspx?page=wrapper&key=<ram_process_ctr>
			        var details = data[index];
			        if (details.D_Date_Rec && details.D_Date_Rec < cutoffDate) {
			        	//console.log("Ignoring result because " + details.D_Date_Rec + " < " + cutoffDate, details);
			            //continue;
			        }
			        if (! details.OBJECTID || ! details.ram_id || ! details.ram_process_ctr) {
			        	//missing information that we rely upon to enable other features
			        	console.log("Ingoring result because [OBJECTID, ram_id, or ram_process_ctr] fields are missing", details);
			        	continue;
			        }
			        
			        //store the dataset this belongs to
			        details.context = dataset;
			        
			        //compute (and store) the title
			    	var title = titleFromDataItem(details);
			    	details._title = title;
			        
			        //store mangled version of the ram_id
			        details.ram_key = details.ram_id.replace(/[^a-zA-Z0-9_]/g, "_");
			        
			        if (details.latitude && details.longitude) {
			            //it's a point
			            var gmarker = new google.maps.Marker({
			                flat: true,
			                icon: baseIcon,
			                map: map,
			                position: new google.maps.LatLng(details.latitude, details.longitude),
			                title: title,
			                visible: true
			            });
			            
			            gmarker._rawData = details;
			            markerMap[details.OBJECTID] = gmarker;
			            if (! markerRamMap[details.ram_key]) {
			            	markerRamMap[details.ram_key] = [];
			            }
			            markerRamMap[details.ram_key].push(gmarker);
			            
			            setupListeners(gmarker);
			            currentMarkers.push(gmarker);
			        }
			        else if (details.geometry && details.geometry.points && details.geometry.points.length == 1) {
			        	//it's (still) a point
			            var gmarker = new google.maps.Marker({
			                flat: true,
			                icon: baseIcon,
			                map: map,
			                position: new google.maps.LatLng(details.geometry.points[0][1], details.geometry.points[0][0]),
			                title: title,
			                visible: true
			            });
			            
			            gmarker._rawData = details;
			            markerMap[details.OBJECTID] = gmarker;
			            if (! markerRamMap[details.ram_key]) {
			            	markerRamMap[details.ram_key] = [];
			            }
			            markerRamMap[details.ram_key].push(gmarker);
			            
			            setupListeners(gmarker);
			            currentMarkers.push(gmarker);
			        }
			        else if (details.geometry) {        
			        	console.log("Unsupported geometry", details.geometry);
			        }
			    }
			    
			    if (currentMarkers.length > 0) {
			    	//display tabular version of results as well
			    	//FIXME:  make table sortable
			    	var table = $("<table></table>");
			    	table.append("<tr><th>Id</th><th>Type</th><th>Decision</th><th>Status</th><th>Comments</th><th>Actions</th></tr>");
			    	
			    	var seenIds = {};
			    	for (var index = 0; index < currentMarkers.length; index++) {
			    		var data = currentMarkers[index]._rawData;
			    		var flatData = encodeURIComponent(JSON.stringify(data));
			    		if (! seenIds[data.ram_key]) {
			    			//add a row for this project
			    			var row = $("<tr id='" + data.ram_key + "'></tr>");
			    			row.addClass(data.ram_key);
			    			row.addClass("projectRow");
			    			
			    			row.append("<td>" + data.ram_id + "</td>");
			    			row.append("<td>" + data.group_desc + "</td>");
			    			row.append("<td>" + data.DECISION + "</td>");
			    			row.append("<td>" + data.Progress + "</td>");
			    			row.append("<td>" + "<a href='/data-gateway/examples/geoPDODetails?project=" + flatData + "'>" + (data.comments ? data.comments.length : "0") + "</a></td>");
			    			row.append("<td>" + "<a href='/data-gateway/examples/geoPDODetails?project=" + flatData + "'>View Details</a>" + "</td>");
			    			
			    			table.append(row);
			    			seenIds[data.ram_key] = true;
			    		}
			    	}
			    	
			    	
			    	$(".output").append(table);
			    	$(".output").show();
			    	
			    	//event handlers to link table to map on hover
			    	table.find(".projectRow").mouseover(function(){
			    		var ramKey = $(this).attr("id");
			    		var markers = markerRamMap[ramKey];
			    		
			    		for (var index = 0; index < markers.length; index++) {
			    			var marker = markers[index];
			    			marker._zIndex = marker.getZIndex();
			    			marker.setZIndex(1000000);
			    			google.maps.event.trigger(marker, 'mouseover');
			    		}
			    		
			    		$(this).css("background", "#E1F2B6");
			    	});
			    	
			    	table.find(".projectRow").mouseout(function(){
			    		var ramKey = $(this).attr("id");
			    		var markers = markerRamMap[ramKey];
			    		
			    		for (var index = 0; index < markers.length; index++) {
			    			var marker = markers[index];
			    			if (marker._zIndex) {
			    				marker.setZIndex(marker._zIndex);
			    			}
			    			google.maps.event.trigger(marker, 'mouseout');
			    		}
			    		
			    		$(this).css("background", "#FFFFFF");
			    	});
			    }
			};
	
			window.setupListeners = function(marker) {
				google.maps.event.addListener(marker, "mouseover", function() {
					//link the marker to its entry in the list
					var key = marker._rawData.ram_key;
					$("#" + key).css("background", "#E1F2B6");
					
			        marker.setIcon(hoverIcon);
			    });
			    google.maps.event.addListener(marker, "mouseout", function() {
			    	//link the marker to its entry in the list
					var key = marker._rawData.ram_key;
					$("#" + key).css("background", "#FFFFFF");
			    
			        marker.setIcon(baseIcon);
			    });
			    
			    //FIXME:  link to list on hover
			    //FIXME:  info window
			};
	
			window.titleFromDataItem = function(item) {
				if (item.Owner && item.WiFiType) {
			    	return item.WiFiType + " - " + item.Owner;
			    }
			    if (item.Address && item.Locality) {
			    	return item.Address + ", " + item.Locality;
			    }
			    if (item.Facility) {
			    	return item.Facility;
			    }
			    if (item.DESCRIPTION) {
			    	return item.DESCRIPTION;
			    }
			    if (item.ReferenceID) {
			    	return item.ReferenceID;
			    }
			    if (item.NAME) {
			    	return item.NAME;
			    }
			    if (item.Name) {
			    	if (item.Day && item.Frequency) {
			        	return item.Name + " - " + item.Frequency + " - " + item.Day;
			        }
			        if (item.Day) {
			        	return item.Name + item.Day;
			        }
			        if (item.Frequency) {
			        	return item.Name + " - " + item.Frequency;
			        }
			        
			        return item.Name;
			    }
			    
			    if (item.LocationDesc) {
			    	if (item.ConditionComments) {
			        	return item.LocationDesc + " - " + item.ConditionComments;
			        }
			        return item.LocationDesc;
			    }
			    if (item.ConditionComments) {
			    	return item.ConditionComments;
			    }
			    
			    if (item.Beach) {
			    	if (item.LocationDescription && item.DogInfo) {
			        	return item.Beach + " - " + item.LocationDescription + " - " + item.DogInfo;
			        }
			        if (item.LocationDescription) {
			        	return item.Beach + " - " + item.LocationDescription;
			        }
			        if (item.DogInfo) {
			        	return item.Beach + " - " + item.DogInfo;
			        }
			        return item.Beach;
			    }
			    
			    if (item.StreetName) {
			    	return item.StreetName;
			    }
			    
			    if (item.StationName) {
			        //XXX:  note that 'PageURL' is also pretty interesting
			    	return item.StationName;
			    }
			    
			    if (item.Hazard) {
			    	if (item.Location) {
			        	return item.Hazard + " - " + item.Location;
			        }
			        return item.Hazard;
			    }
			    
			    if (item.Description) {
			    	if (item.Description2 && item.ProjectDetailComments) {
			        	return item.Description + " - " + item.Description2 + " - " + item.ProjectDetailComments;
			        }
			        if (item.Description2) {
			        	return item.Description + " - " + item.Description2;
			        }
			        if (item.ProjectDetailComments) {
			        	return item.Description + " - " + item.ProjectDetailComments; 
			        }
			        
			        return item.Description;
			    }
			    
			    console.log("Unsupported Item Type", item);
			    return "Unknown Item";
			};

			window.zoomToFit = function(map, markers) {
				var coords = [];
				for (var index = 0; index < markers.length; index++) {
					var marker = markers[index];
					coords.push(marker.getPosition());
				}
				
				var bounds = new google.maps.LatLngBounds();
				for (var i = 0; i < coords.length; i++) {
			  		bounds.extend (coords[i]);
				}
				
				if (coords.length < 1 && _lastGeocode) {
					var searchBounds = boundingBox(_lastGeocode.lat, _lastGeocode.lng, parseFloat($(".range option:selected").val()));
					bounds.extend({lat: searchBounds[1], lng: searchBounds[0]});
					bounds.extend({lat: searchBounds[3], lng: searchBounds[2]});
				}
				
				map.bounds = bounds;
				map.fitBounds(bounds);
				
				
			};
		
			$(document).ready(function() {
				initMap();
			});
		</script>
	</body>
</html>
