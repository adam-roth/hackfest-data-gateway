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
			#status select {
				margin-bottom: 5px;
				max-width: 200px;
				font-size: 12px;
			}
			#status input {
				font-size: 12px;
			}
		</style>
		<script>
			window.startingCoord = {lat: -26.6522586, lng: 153.0906231};		//HackFest HQ
		    if (navigator.geolocation) {
		        navigator.geolocation.getCurrentPosition(function(pos) {
		            //console.log("Got position from browser", pos)
		            startingCoord = {lat: pos.coords.latitude, lng: pos.coords.longitude};
		    
		            if (window.map && window.map.setCenter) {
		                map.setCenter(startingCoord);
		            }
		        });
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

				runSearch(true);
		    };
		</script>
		
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvwJUP-fs5XdY7PMBHCUgcJKGbtrVXkGU&callback=mapsAvailable" async defer></script>
		<script src='http://terra.suncoastpc.com.au:8181/data-gateway/api/client?apiKey=a3579c0b937f483c9b52247544d4bb5a'></script>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>Datasets</h1>
			<select id="datasetSelect">
			    <option value="-1">(select a dataset)</option>
				<option value="Society/Society_SCRC/MapServer/1">Aquatic Centres</option>
				<option value="Structure/Structure_SCRC/MapServer/0">Barbeques</option>
				<option value="Society/Society_SCRC/MapServer/4">Basketball Courts</option>
				<option value="Society/Society_SCRC/MapServer/5">Beach Access Points</option>
				<option value="Staging/Applications_SCRC/MapServer/3">Building Applications</option>
				<option value="Maximo/EZMaxTrain_NonSecure2/MapServer/18">Buildings - SCC Assets</option>
				<option value="Transportation/Transportation_SCRC/MapServer/1">Bus Stop (w/ Shelter)</option>
				<option value="Society/Society_SCRC/MapServer/9">Child Care</option>
				<option value="Society/Society_SCRC/MapServer/12">Com. Centres</option>
				<option value="Society/Society_SCRC/MapServer/17">Com. Markets (Monthly)</option>
				<option value="Society/Society_SCRC/MapServer/16">Com. Markets (Weekly)</option>
				<option value="Staging/Applications_SCRC/MapServer/0">Development Applications</option>
				<option value="Society/Society_SCRC/MapServer/22">Fitness Areas</option>
				<option value="Society/Society_SCRC/MapServer/24">Liquor/Bottle Shop</option>
				<option value="Society/Society_SCRC/MapServer/30">Meat/Butchery</option>
				<option value="Maximo/EZMaxTrain_NonSecure2/MapServer/4">Open Spaces</option>
				<option value="Environment/ParksandGardensContracts_SCRC/MapServer/0">Picnic Tables</option>
				<option value="Emergency/SituationalAwareness_SCRC/MapServer/2">River Gauges</option>
				<!--  <option value="Transportation/Transportation_RoadEvents_SCRC/MapServer/2">Road Closure Hotspots</option> -->
				<option value="Administration/Administration_SCRC/MapServer/0">SCC Projects (Current Year)</option>
				<option value="Administration/Administration_SCRC/MapServer/1">SCC Projects (Allocated)</option>
				<option value="UtilitiesCommunication/Utilities_SCRC/MapServer/0">WiFi Access Points</option>
			</select>
			<div>
				<input type="button" onclick="runSearch();" value="Load Data" />
			</div>
		</div>
		<div id="page-body" role="main">
			<h1>Basic Example</h1>
			<p>This example allows a selection of datasets to be plotted on the map.  You can hover over a marker to view some brief additional details.   The raw data used to plot the markers will be displayed beneath the map.
			</p>
			<div id="mapDiv"> 
			</div>
			<div class="output" style="display: none;">
			</div>
		</div>
		<script>
			//create a reusable client instance
			window.client = new gateway.Client();
	
			window.runSearch = function(zoom) {
			    var dataset = $("#datasetSelect option:selected").val();
			    if (dataset == -1) {
			    	dataset = gateway.datasets["DevelopmentApps - All"];
			    }
			    
			    $(".output").hide();
			    
			    //get some data to work with
			    client.loadData({
			        context: dataset,
			        params: {
			            where: "1=1",
			            f: "pjson"
			        },
			        success: function(data) {
			            //console.log(data);
			            $(".output").html(JSON.stringify(data));
			            $(".output").show();
			            
			            if (window.map) {
			            	drawDataOnMap(data, map);
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
	
			window.currentMarkers = [];
			window.drawDataOnMap = function(data, map) {
				//first, clear any overlays that currently exist
			    while (currentMarkers.length > 0) {
			    	currentMarkers.pop().setMap(null);
			    }
			    
			    //add new markers for the current dataset
			    for (var index = 0; index < data.length; index++) {
			        var details = data[index];
			        var title = titleFromDataItem(details);
			        
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
			            
			            setupListeners(gmarker);
			            currentMarkers.push(gmarker);
			        }
			        else if (details.geometry && details.geometry.rings && details.geometry.rings.length == 1) {
			        	//it's a region; we can plot the entire thing or take the centroid
			            var coords = details.geometry.rings[0];
			            //var gCoords = [];
			            
			            var bounds = new google.maps.LatLngBounds();
			            for (var coordIndex = 0; coordIndex < coords.length; coordIndex++) {
			            	var coord = coords[coordIndex];
			                //gCoords.push({lat: coord[1], lng: coord[0]});
			                bounds.extend(new google.maps.LatLng(coord[1], coord[0]));
			            }
			            
			            /*var region = new google.maps.Polygon({
			            	paths: gCoords,
			                title: title,
			                strokeColor: '#FF0000',
			                strokeOpacity: 0.8,
			                strokeWeight: 2,
			                fillColor: '#FF0000',
			                fillOpacity: 0.35
			         	});
			            region.setMap(map);
			            
			            currentMarkers.push(region);*/
			            
			            var gmarker = new google.maps.Marker({
			                flat: true,
			                icon: baseIcon,
			                map: map,
			                position: bounds.getCenter(),
			                title: title + " - Note that position is approximate",
			                visible: true
			            });
			            
			            setupListeners(gmarker);
			            currentMarkers.push(gmarker);
			        }
			        else if (details.geometry) {        
			        	console.log("Unsupported geometry", details.geometry);
			        }
			    }
			};
	
			window.setupListeners = function(marker) {
				google.maps.event.addListener(marker, "mouseover", function() {
			        marker.setIcon(hoverIcon);
			    });
			    google.maps.event.addListener(marker, "mouseout", function() {
			        marker.setIcon(baseIcon);
			    });
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
				map.bounds = bounds;
				map.fitBounds(bounds);
			};
		
			$(document).ready(function() {
				initMap();
			});
		</script>
	</body>
</html>
