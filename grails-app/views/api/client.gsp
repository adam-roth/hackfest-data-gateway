//HackFest Data Gateway - JavaSCript API Client
<g:if test="${ apiKey }">
if (! window.gateway) {
	window.gateway = {};
}
window.gateway.server = "${g.createLink(absolute: true, uri:'/')}";
window.gateway.apiKey = "${ apiKey }";

window.gateway.datasets = {
    "(Mowing and Landscaping) Labels": "Environment/Environment_SCRC/MapServer/9",
    "2011 ABS Census Data": "Society/Society_Demographic_SCRC/MapServer/0",
    "2014 Contours": "Elevation/Contours_SCRC/MapServer/1",
    "3k-0": "Elevation/Contours_SCRC/MapServer/2",
    "Acid Sulfate Soils": "PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/0",
    "Aerial_2015_SCRC": "Staging/temp/MapServer/0",
    "Age": "Society/Society_Demographic_SCRC/MapServer/5",
    "Age 00 - 04 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/6",
    "Age 05 - 14 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/7",
    "Age 15 - 19 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/8",
    "Age 20 - 24 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/9",
    "Age 25 - 34 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/10",
    "Age 35 - 44 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/11",
    "Age 45 - 54 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/12",
    "Age 55 - 64 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/13",
    "Age 65 - 74 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/14",
    "Age 75 - 84 years. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/15",
    "Age 85 years and over. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/16",
    "Airport Environs": "PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/1",
    "Ambulance (State)": "Society/Society_SCRC/MapServer/0",
    "Aquatic Centres": "Society/Society_SCRC/MapServer/1",
    "Aquatic Centres (Private)": "Society/Society_SCRC/MapServer/3",
    "Aquatic Centres (State)": "Society/Society_SCRC/MapServer/2",
    "BMX Facilities": "Society/Society_SCRC/MapServer/7",
    "Barbeques": "Structure/Structure_SCRC/MapServer/0",
    "Basketball Courts": "Society/Society_SCRC/MapServer/4",
    "BeachAccess - Assets": "Maximo/EZMaxTrain_NonSecure2/MapServer/6",
    "Beaches (Access Points)": "Society/Society_SCRC/MapServer/5",
    "Beaches (Patrolled)": "Society/Society_SCRC/MapServer/6",
    "Boundary": "Staging/temp/MapServer/1",
    "Bread and Cake Store & Bakery Retailing": "Society/Society_SCRC/MapServer/28",
    "Bridges": "Transportation/Transportation_SCRC/MapServer/6",
    "Bridges - Assets": "Maximo/EZMaxTrain_NonSecure2/MapServer/16",
    "BuildingApps - All": "Staging/Applications_SCRC/MapServer/5",
    "BuildingApps - Decided": "Staging/Applications_SCRC/MapServer/4",
    "BuildingApps - In Progress": "Staging/Applications_SCRC/MapServer/3",
    "Buildings (SCRC) - Assets": "Maximo/EZMaxTrain_NonSecure2/MapServer/18",
    "Buildings (SCRC-Leased) - Assets": "Maximo/EZMaxTrain_NonSecure2/MapServer/20",
    "Bus Stop (No Shelter)": "Transportation/Transportation_SCRC/MapServer/0",
    "Bus Stop (With Shelter)": "Transportation/Transportation_SCRC/MapServer/1",
    "Bus Stops - Assets": "Maximo/EZMaxTrain_NonSecure2/MapServer/5",
    "BushlandOperationalAssessments": "Staging/Offline_Mobile_Basemap_NAM/MapServer/4",
    "Cafe or Restaurant": "Society/Society_SCRC/MapServer/27",
    "Catchments (Stormwater)": "InlandWaters/InlandWaters_SCRC/MapServer/3",
    "Cemeteries": "Society/Society_SCRC/MapServer/8",
    "Child Care": "Society/Society_SCRC/MapServer/9",
    "Cities": "Staging/Offline_Mobile_Basemap_NAM/MapServer/12",
    "Cleaning Contract Zones": "Environment/ParksandGardensContracts_SCRC/MapServer/7",
    "Coast Guard": "Society/Society_SCRC/MapServer/10",
    "Coast Guard (State)": "Society/Society_SCRC/MapServer/11",
    "Coastal Pathway Corridor": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/10",
    "Community Centres": "Society/Society_SCRC/MapServer/12",
    "Community Centres (Private)": "Society/Society_SCRC/MapServer/14",
    "Community Centres (State)": "Society/Society_SCRC/MapServer/13",
    "Community Gardens": "Society/Society_SCRC/MapServer/15",
    "Community Markets (Monthly)": "Society/Society_SCRC/MapServer/17",
    "Community Markets (Seasonal)": "Society/Society_SCRC/MapServer/18",
    "Community Markets (Weekly)": "Society/Society_SCRC/MapServer/16",
    "Conservation Group Meeting Point": "Environment/Environment_SCRC/MapServer/0",
    "Contours 1m": "Elevation/Contours_SCRC/MapServer/5",
    "Contours 25m": "Elevation/Contours_SCRC/MapServer/3",
    "Contours 5m": "Elevation/Contours_SCRC/MapServer/4",
    "Contract Zones": "Environment/ParksandGardensContracts_SCRC/MapServer/8",
    "Council Leases": "PlanningCadastre/ParcelInformation_SCRC/MapServer/2",
    "Counter Sites (Permanent)": "Transportation/Transportation_SCRC/MapServer/3",
    "Covenants": "PlanningCadastre/ParcelInformation_SCRC/MapServer/0",
    "Current REC, MCU, OPWORK": "PlanningCadastre/Applications_SCRC/MapServer/2",
    "Customer Service Centres": "Society/Society_SCRC/MapServer/19",
    "Defined Flood Extent": "Emergency/FloodHazardArea_SCRC/MapServer/0",
    "Delicatessen": "Society/Society_SCRC/MapServer/29",
    "Development Applications": "PlanningCadastre/Applications_SCRC/MapServer/0",
    "Development Area": "Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/1",
    "Development Area (Mask)": "Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/0",
    "DevelopmentApps - All": "Staging/Applications_SCRC/MapServer/2",
    "DevelopmentApps - Decided": "Staging/Applications_SCRC/MapServer/1",
    "DevelopmentApps - In Progress": "Staging/Applications_SCRC/MapServer/0",
    "Domestic Bin Collection": "Health/DomesticBinCollectionDays_SCRC/MapServer/0",
    "Easements": "PlanningCadastre/ParcelInformation_SCRC/MapServer/1",
    "Electoral Divisions (Council-2012)": "Boundaries/Boundaries_SCRC/MapServer/1",
    "Electoral Divisions (Council-2014)": "Boundaries/Boundaries_SCRC/MapServer/0",
    "Electoral Divisions (State)": "Boundaries/Boundaries_SCRC/MapServer/2",
    "Environmental Landmarks": "Environment/Environment_SCRC/MapServer/1",
    "Environmental Reserve Slashing": "Environment/ParksandGardensContracts_SCRC/MapServer/12",
    "Evacuation Areas": "Emergency/SituationalAwareness_SCRC/MapServer/5",
    "Evacuation Centres": "Emergency/SituationalAwareness_SCRC/MapServer/4",
    "Existing Pathway": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/7",
    "Existing Signed Trail": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/9",
    "Facilities (SCRC) - Locations": "Maximo/EZMaxTrain_NonSecure2/MapServer/19",
    "Fire Brigade": "Society/Society_SCRC/MapServer/20",
    "Fire Brigade (State)": "Society/Society_SCRC/MapServer/21",
    "FireTrails": "Staging/Offline_Mobile_Basemap_NAM/MapServer/2",
    "Fish and Seafood Retailing": "Society/Society_SCRC/MapServer/31",
    "Fitness Areas": "Society/Society_SCRC/MapServer/22",
    "Food Retail Stores": "Society/Society_SCRC/MapServer/23",
    "Footprint": "Staging/temp/MapServer/2",
    "Fruit Retailing and Vegetable Retailing": "Society/Society_SCRC/MapServer/35",
    "Garden - Infirm Domestic Bin Services": "Health/Health_SCRC/MapServer/2",
    "Garden - Kerbside Domestic Bin Services": "Health/Health_SCRC/MapServer/4",
    "Garden Waste Services (Domestic)": "Health/Health_SCRC/MapServer/1",
    "Green - On Property Domestic Bin Services": "Health/Health_SCRC/MapServer/3",
    "Grocery Retailing": "Society/Society_SCRC/MapServer/33",
    "Health Food Store": "Society/Society_SCRC/MapServer/34",
    "Highway and Motorway Labels": "Transportation/Roads_SCRC/MapServer/1",
    "Hotel Tavern or Bar": "Society/Society_SCRC/MapServer/26",
    "House": "PlanningCadastre/Labels_SCRC/MapServer/3",
    "Image": "Staging/temp/MapServer/3",
    "Isolated Areas": "Emergency/SituationalAwareness_SCRC/MapServer/6",
    "Land Parcel Boundaries": "PlanningCadastre/ParcelInformation_SCRC/MapServer/3",
    "Land Parcel Boundaries (Aerial)": "PlanningCadastre/ParcelInformation_SCRC/MapServer/4",
    "Land Parcels": "Maximo/EZMaxTrain_NonSecure2/MapServer/3",
    "Landscape Contract": "Environment/ParksandGardensContracts_SCRC/MapServer/4",
    "Language other than english spoken at home - % of total persons. Based on ABS 2011 Census Data": "Society/Society_Demographic_SCRC/MapServer/3",
    "Liquor Store & Bottle Shop": "Society/Society_SCRC/MapServer/24",
    "Local Government Areas": "Boundaries/Boundaries_SCRC/MapServer/4",
    "Local Growth Management Boundaries": "PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/0",
    "Local Law 2 Animal Management": "Boundaries/Boundaries_SCRC/MapServer/5",
    "Local Law 5 Parking": "Boundaries/Boundaries_SCRC/MapServer/6",
    "Local Plan & Other Areas": "PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/1",
    "Local Plan Area Boundary": "PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/2",
    "Local Plan Areas": "PlanningCadastre/PlanningScheme_SunshineCoast_Index_SCC/MapServer/1",
    "Local Plan Precincts": "PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/5",
    "Local Plan Precincts and Sub-precincts": "PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/3",
    "Local Plan Sub-precincts": "PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/4",
    "Local Road Events": "Transportation/Transportation_RoadEvents_SCRC/MapServer/0",
    "Localities": "Staging/Offline_Mobile_Basemap_NAM/MapServer/10",
    "Localities Of Interest (2009)": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/1",
    "Localities of Interest (2014)": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/0",
    "Lot Plan": "PlanningCadastre/Labels_SCRC/MapServer/0",
    "Lot Plan (Aerial)": "PlanningCadastre/Labels_SCRC/MapServer/1",
    "Low Density Residential Zone Precinct": "PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/1",
    "Maintenance Area (Mowing and Landscaping)": "Environment/Environment_SCRC/MapServer/8",
    "Maintenance Area (Turf Contract Zones-Boundary)": "Environment/Environment_SCRC/MapServer/4",
    "Maintenance Area (Turf-Grid_5000)": "Environment/Environment_SCRC/MapServer/7",
    "Maintenance Area (Turf-Part A-Open Space)": "Environment/Environment_SCRC/MapServer/5",
    "Maintenance Area (Turf-Part B-Road Network)": "Environment/Environment_SCRC/MapServer/6",
    "Maintenance Grid 5000": "Environment/ParksandGardensContracts_SCRC/MapServer/9",
    "Major Road Labels": "Transportation/Roads_SCRC/MapServer/2",
    "Major Roads - Large Scale Labels": "Transportation/Roads_SCRC/MapServer/5",
    "Major Roads - Medium Scale Labels": "Transportation/Roads_SCRC/MapServer/4",
    "Major Roads - Small Scale Labels": "Transportation/Roads_SCRC/MapServer/3",
    "ManagementConsiderations": "Staging/Offline_Mobile_Basemap_NAM/MapServer/0",
    "Map Tiles": "PlanningCadastre/PlanningScheme_SunshineCoast_Index_SCC/MapServer/0",
    "Meat & Butchery": "Society/Society_SCRC/MapServer/30",
    "New Group Layer": "Staging/Offline_Mobile_Basemap_NAM/MapServer/5",
    "New Medium Term Priority": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/4",
    "New Short Term Priority": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/5",
    "Noosa Shire": "Maximo/EZMaxTrain_NonSecure2/MapServer/0",
    "Notional Only (Potential Future Link/Trail)": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/3",
    "Number of Dwellings. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/2",
    "Number of Persons Usually Resident. Based on ABS 2011 Census Data.": "Society/Society_Demographic_SCRC/MapServer/1",
    "Open Space - Locations": "Maximo/EZMaxTrain_NonSecure2/MapServer/4",
    "Openspace (Constructed Water Bodies)": "Environment/Environment_SCRC/MapServer/2",
    "Openspace (Operational Category)": "Environment/Environment_SCRC/MapServer/3",
    "Part A - Open Space": "Environment/ParksandGardensContracts_SCRC/MapServer/2",
    "Part B - Road Network": "Environment/ParksandGardensContracts_SCRC/MapServer/3",
    "Paths, Cycleways and Crossings": "Transportation/Transportation_SCRC/MapServer/7",
    "Paths, Cycleways and Crossings - Assets": "Maximo/EZMaxTrain_NonSecure2/MapServer/15",
    "Pathways (Future)": "Transportation/Transportation_SCRC/MapServer/8",
    "Picnic Tables": "Environment/ParksandGardensContracts_SCRC/MapServer/0",
    "Planning Short/Medium Term": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/6",
    "Precinct Inspection Area": "Maximo/EZMaxTrain_NonSecure2/MapServer/2",
    "Property Name": "PlanningCadastre/Labels_SCRC/MapServer/2",
    "Public Place Bins": "Health/Health_SCRC/MapServer/0",
    "Public Safety Area": "PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/3",
    "Railway Lines": "Transportation/Transportation_SCRC/MapServer/10",
    "Railway Stations": "Transportation/Transportation_SCRC/MapServer/9",
    "Recreation Trail Plan 2012": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/2",
    "River Gauges": "Emergency/SituationalAwareness_SCRC/MapServer/2",
    "Road Closure Hotspots": "Transportation/Transportation_RoadEvents_SCRC/MapServer/2",
    "Road Segments": "Transportation/Transportation_SCRC/MapServer/11",
    "Roads (Labels)": "Transportation/Roads_SCRC/MapServer/0",
    "Roads - Locations": "Maximo/EZMaxTrain_NonSecure2/MapServer/17",
    "Roadside Cleaning": "Environment/ParksandGardensContracts_SCRC/MapServer/5",
    "Roadside Cleaning AREA": "Environment/ParksandGardensContracts_SCRC/MapServer/6",
    "Runway Separation Distances": "PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/6",
    "Rural Landuse (not a regulatory layer)": "PlanningCadastre/LandUse_SCRC/MapServer/1",
    "Rural Zone Precinct": "PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/2",
    "SCRC Constructed Projects (Current Financial Year)": "Administration/Administration_SCRC/MapServer/0",
    "SCRC Constructed Projects (Divisional Allocations)": "Administration/Administration_SCRC/MapServer/1",
    "SEIFA 2011 Disadvantage Deciles": "Society/Society_Demographic_SCRC/MapServer/4",
    "Sandbag Stations": "Emergency/SituationalAwareness_SCRC/MapServer/3",
    "Shelters - Open Space": "Structure/Structure_SCRC/MapServer/2",
    "Signs (Speed - SCRC Roads)": "Transportation/Transportation_SCRC/MapServer/2",
    "Slashing Contract": "Environment/ParksandGardensContracts_SCRC/MapServer/10",
    "Slashing Contract Zones": "Environment/ParksandGardensContracts_SCRC/MapServer/11",
    "Slope (Grouped)": "Environment/Environment_SCRC/MapServer/10",
    "State Horse Trail": "PlanningCadastre/EndorsedStrategies_SCRC/MapServer/8",
    "State Road Events": "Transportation/Transportation_RoadEvents_SCRC/MapServer/1",
    "Storm Tide Gauges": "Emergency/SituationalAwareness_SCRC/MapServer/1",
    "Stormtide flood extent categories Sept 2012": "Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/3",
    "Stormtide flood model extent Sept 2012": "Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/2",
    "Stormwater Assets (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/3",
    "Stormwater Culvert (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/10",
    "Stormwater End Structure (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/5",
    "Stormwater Misc Infrastructure (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/6",
    "Stormwater Network": "UtilitiesCommunication/Utilities_SCRC/MapServer/2",
    "Stormwater Open Drain (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/7",
    "Stormwater Pipe (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/9",
    "Stormwater Pit (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/4",
    "Stormwater Quality Device (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/8",
    "Stormwater Water Quality Area (Council)": "UtilitiesCommunication/Utilities_SCRC/MapServer/11",
    "Street Sweeping Services": "Environment/ParksandGardensContracts_SCRC/MapServer/1",
    "Sunshine Coast Airport": "PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/2",
    "Supermarket": "Society/Society_SCRC/MapServer/32",
    "Takeaway Food Store": "Society/Society_SCRC/MapServer/25",
    "Telecommunication Network": "UtilitiesCommunication/Utilities_SCRC/MapServer/1",
    "Tourism Zone Precincts": "PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/3",
    "Traffic Signals (SCRC)": "Transportation/Transportation_SCRC/MapServer/4",
    "Traffic Signals (TMR)": "Transportation/Transportation_SCRC/MapServer/5",
    "Traffic Web Cameras": "Emergency/SituationalAwareness_SCRC/MapServer/0",
    "TrailMaintenance": "Staging/Offline_Mobile_Basemap_NAM/MapServer/3",
    "Trails": "Staging/Offline_Mobile_Basemap_NAM/MapServer/1",
    "Urban Landuse (not a regulatory layer)": "PlanningCadastre/LandUse_SCRC/MapServer/0",
    "Water Bodies": "InlandWaters/InlandWaters_SCRC/MapServer/0",
    "Water Fittings - Outdoor": "Structure/Structure_SCRC/MapServer/1",
    "Watercourses": "InlandWaters/InlandWaters_SCRC/MapServer/1",
    "Watercourses (Indicative)": "InlandWaters/InlandWaters_SCRC/MapServer/2",
    "WiFi Access Points": "UtilitiesCommunication/Utilities_SCRC/MapServer/0",
    "WorkOrder_Point": "Maximo/EZMaxTrain_NonSecure2/MapServer/21",
    "Zone Precincts": "PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/0",
    "[system use only]": "Maximo/TRAINMaxCWSLoggerEdit_SecureEdit/MapServer/0"
};

//detect different browser versions, in case there are any quirks we need to work atound
window.gateway.browser = {};
var ua = window.navigator.userAgent;
var old_ie = ua.indexOf('MSIE ');
var new_ie = ua.indexOf('Trident/');
if ((old_ie > -1) || (new_ie > -1)) {
    window.gateway.browser.ms_ie = true;
}
if(window.navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
	window.gateway.browser.firefox = true;
}
if (window.navigator.userAgent.search("Safari") >= 0 && window.navigator.userAgent.search("Chrome") < 0) {
	window.gateway.browser.safari = true;
}

//the Client object provides the user-level interface to the API
window.gateway.Client = function() {
	this.server = gateway.server;
	this.apiKey = gateway.apiKey;
	this.extraParams = "format=json";
};

//list all available sites
window.gateway.Client.prototype.loadData = function(paramObj) {
	if (! paramObj) {
		paramObj = {};
	}
	
	//this should only ever be a string
	var context = paramObj.context;
	if (! context || context.length < 1) {
		context = "Unspecified";
	}
	
	//this may be a query-string, like 'thing=1&otherThing=23&somethingElse=hello', or an object like {thing: 1, otherThing: 2, somethingElse: hello}
	var queryParams = paramObj.params;
	if (! queryParams) {
		queryParams = "";
	}
	if (! queryParams.charAt) {
		//we have an object, stringify it
		//queryParams = JSON.stringify(queryParams);
		var queryString = "";
		for (var key in queryParams) {
			if (queryString != "") {
				queryString += "&";
			}
			queryString += key + "=" + encodeURIComponent(queryParams[key]);
		}
		queryParams = queryString;
	}
	
	window.gateway.sendRequest("/api/loadData", paramObj.success, paramObj.failure, [this.apiKey, context, queryParams]);
};

//JSDL for the actual webservice API
window.gateway.jsdl = {
    "/api/loadData": ["apiKey", "context", "params"]
};

//private/internal API methods
window.gateway.parseResponse = function(text) {
	if (! text || text.status || ! (text instanceof String)) {
		return text;   //it's not something we need to/can parse here
	}
	try {
		text = jQuery.trim(text); 
		return eval( "(" + text + ")" );
	}
	catch (ex) {
		console.log("ERROR:  Could not parse response:  " + text);
		return {"status":"error", "message": "Unreadable response received from server!"};
	}
};

//handler function for our JSDL bindings
window.gateway.sendRequest = function(apiMethod, success, failure, args, numPages) {
	//provide sensible defaults
	if (! success) {
		success = function(data, apiMethod, numResults) {
			console.log(data, apiMethod, numResults);
		};
	}
	if (! failure) {
		failure = function(data, apiMethod) {
			console.log(data, apiMethod);
		};
	}
	
	//make sure jQuery exists
	if (! jQuery || ! jQuery.ajax || ! jQuery.trim || ! jQuery.isArray) {	//FIXME:  should avoid using an external framework
		if (failure) {
			failure("jQuery not found; please include jQuery >= 1.5 and try again.");
		}
		return;
	}
	
	//make sure it's a valid API call
	if (! apiMethod || ! window.gateway.jsdl[apiMethod]) {
		if (failure) {
			failure("Unrecognized API method:  " + apiMethod);
		}
		return;
	}
	
	//construct URL and params
	var paramNames = window.gateway.jsdl[apiMethod];
	var url = window.gateway.server + apiMethod.substring(1);
	var params = {};
	for (var index = 0; args && index < args.length; index++) {
		var paramValue = args[index];
		if (paramValue && (paramValue.length > 0 || (paramValue.toString && paramValue.toString().length > 0))) {
			params[paramNames[index]] = args[index];
		}
	}
	
	//invoke the URL
	jQuery.ajax({
		url:  url,
		cache: false,
		type: "POST",
		data:  params,
		dataType: "jsonp",
		//dataType: "text",
		success: function(data, status, xhr) {
			//console.log("Successful request:  " + data);
			data = window.gateway.parseResponse(data);
			if (data.status != "success") {
				if (data.message) {
					failure(data.message, apiMethod);
				}
				else {
					failure("API request failed (1); url=" + url + ", response=" + JSON.stringify(data), apiMethod);
				}
			}
			else {
				success(data.result, apiMethod);
			}
		},
		error: function(xhr, text, error) {
			var message = "The request to the server failed";
			if (text) {
				message += "; " + text;
				if (error) {
					message += "=" + error;
				}
			}
			else if (error) {
				message += "; " + error;
			}
			
			failure("API request failed (2); url=" + url + ", params=" + JSON.stringify(params) + ", reason=" + message, apiMethod, xhr, text, error);
		}
	});
};
</g:if>
<g:else>
	alert("Invalid API key:  ${ params.apiKey }!");
</g:else>