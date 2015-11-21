package au.com.suncoastpc.datagateway.handlers

import org.json.simple.JSONArray
import org.json.simple.JSONValue

import au.com.suncoastpc.datagateway.Handler
import au.com.suncoastpc.datagateway.HandlerRegistry

class ArcGISHandler implements Handler {
	static final def theInstance = new ArcGISHandler()
	static final def serviceRoot = "http://gisservices.scc.qld.gov.au/arcgis/rest/services"
	
	static {
		//register for all the context-paths that we support here
		HandlerRegistry.registerHandler("Administration/Administration_SCRC/MapServer/0", theInstance)	//SCRC Constructed Projects (Current Financial Year)
		HandlerRegistry.registerHandler("Administration/Administration_SCRC/MapServer/1", theInstance)	//SCRC Constructed Projects (Divisional Allocations)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/0", theInstance)	//Electoral Divisions (Council-2014)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/1", theInstance)	//Electoral Divisions (Council-2012)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/2", theInstance)	//Electoral Divisions (State)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/3", theInstance)	//Localities
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/4", theInstance)	//Local Government Areas
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/5", theInstance)	//Local Law 2 Animal Management
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/6", theInstance)	//Local Law 5 Parking
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/0", theInstance)	//Sunshine Coast Clip Layer
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/1", theInstance)	//2014 Contours
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/2", theInstance)	//3k-0
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/3", theInstance)	//Contours 25m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/4", theInstance)	//Contours 5m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/5", theInstance)	//Contours 1m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/0", theInstance)	//Sunshine Coast Clip Layer
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/1", theInstance)	//2014 Contours
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/2", theInstance)	//3k-0
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/3", theInstance)	//Contours 25m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/4", theInstance)	//Contours 5m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/5", theInstance)	//Contours 1m
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/0", theInstance)	//Development Area (Mask)
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/1", theInstance)	//Development Area
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/2", theInstance)	//Stormtide flood model extent Sept 2012
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/3", theInstance)	//Stormtide flood extent categories Sept 2012
		HandlerRegistry.registerHandler("Emergency/FloodHazardArea_SCRC/MapServer/0", theInstance)	//Defined Flood Extent
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/0", theInstance)	//Traffic Web Cameras
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/1", theInstance)	//Storm Tide Gauges
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/2", theInstance)	//River Gauges
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/3", theInstance)	//Sandbag Stations
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/4", theInstance)	//Evacuation Centres
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/5", theInstance)	//Evacuation Areas
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/6", theInstance)	//Isolated Areas
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/0", theInstance)	//Conservation Group Meeting Point
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/1", theInstance)	//Environmental Landmarks
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/2", theInstance)	//Openspace (Constructed Water Bodies)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/3", theInstance)	//Openspace (Operational Category)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/4", theInstance)	//Maintenance Area (Turf Contract Zones-Boundary)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/5", theInstance)	//Maintenance Area (Turf-Part A-Open Space)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/6", theInstance)	//Maintenance Area (Turf-Part B-Road Network)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/7", theInstance)	//Maintenance Area (Turf-Grid_5000)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/8", theInstance)	//Maintenance Area (Mowing and Landscaping)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/9", theInstance)	//(Mowing and Landscaping) Labels
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/10", theInstance)	//Slope (Grouped)
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/0", theInstance)	//Picnic Tables
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/1", theInstance)	//Street Sweeping Services
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/2", theInstance)	//Part A - Open Space
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/3", theInstance)	//Part B - Road Network
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/4", theInstance)	//Landscape Contract
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/5", theInstance)	//Roadside Cleaning
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/6", theInstance)	//Roadside Cleaning AREA
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/7", theInstance)	//Cleaning Contract Zones
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/8", theInstance)	//Contract Zones
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/9", theInstance)	//Maintenance Grid 5000
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/10", theInstance)	//Slashing Contract
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/11", theInstance)	//Slashing Contract Zones
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/12", theInstance)	//Environmental Reserve Slashing
		HandlerRegistry.registerHandler("Health/DomesticBinCollectionDays_SCRC/MapServer/0", theInstance)	//Domestic Bin Collection
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/0", theInstance)	//Public Place Bins
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/1", theInstance)	//Garden Waste Services (Domestic)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/2", theInstance)	//Garden - Infirm Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/3", theInstance)	//Green - On Property Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/4", theInstance)	//Garden - Kerbside Domestic Bin Services
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/0", theInstance)	//Water Bodies
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/1", theInstance)	//Watercourses
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/2", theInstance)	//Watercourses (Indicative)
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/3", theInstance)	//Catchments (Stormwater)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/0", theInstance)	//Noosa Shire
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/1", theInstance)	//Localities
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/2", theInstance)	//Land Parcels
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/3", theInstance)	//Precinct Inspection Area
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/4", theInstance)	//Facilities (SCRC) - Locations
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/5", theInstance)	//Buildings (SCRC-Leased) - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/6", theInstance)	//Buildings (SCRC) - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/7", theInstance)	//Paths, Cycleways and Crossings - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/8", theInstance)	//Open Space - Locations
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/9", theInstance)	//Stormwater Water Quality Area (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/10", theInstance)	//Bus Stops - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/11", theInstance)	//Bridges - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/12", theInstance)	//Stormwater Pipe (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/13", theInstance)	//BeachAccess - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/14", theInstance)	//Stormwater End Structure (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/15", theInstance)	//Stormwater Misc Infrastructure (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/16", theInstance)	//Stormwater Open Drain (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/17", theInstance)	//Stormwater Quality Device (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/18", theInstance)	//Stormwater Culvert (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/19", theInstance)	//Stormwater Pit (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/20", theInstance)	//Roads - Locations
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/21", theInstance)	//WorkOrder_Point
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/0", theInstance)	//Noosa Shire
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/1", theInstance)	//Localities
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/2", theInstance)	//Precinct Inspection Area
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/3", theInstance)	//Land Parcels
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/4", theInstance)	//Open Space - Locations
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/5", theInstance)	//Bus Stops - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/6", theInstance)	//BeachAccess - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/7", theInstance)	//Stormwater Pipe (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/8", theInstance)	//Stormwater End Structure (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/9", theInstance)	//Stormwater Misc Infrastructure (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/10", theInstance)	//Stormwater Open Drain (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/11", theInstance)	//Stormwater Quality Device (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/12", theInstance)	//Stormwater Culvert (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/13", theInstance)	//Stormwater Water Quality Area (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/14", theInstance)	//Stormwater Pit (Council)
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/15", theInstance)	//Paths, Cycleways and Crossings - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/16", theInstance)	//Bridges - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/17", theInstance)	//Roads - Locations
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/18", theInstance)	//Buildings (SCRC) - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/19", theInstance)	//Facilities (SCRC) - Locations
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/20", theInstance)	//Buildings (SCRC-Leased) - Assets
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/21", theInstance)	//WorkOrder_Point
		HandlerRegistry.registerHandler("Maximo/TRAINMaxCWSLoggerEdit_SecureEdit/MapServer/0", theInstance)	//[system use only]
		HandlerRegistry.registerHandler("PlanningCadastre/Applications_SCRC/MapServer/0", theInstance)	//Development Applications
		HandlerRegistry.registerHandler("PlanningCadastre/Applications_SCRC/MapServer/2", theInstance)	//Current REC, MCU, OPWORK
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/0", theInstance)	//Localities of Interest (2014)
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/1", theInstance)	//Localities Of Interest (2009)
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/2", theInstance)	//Recreation Trail Plan 2012
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/3", theInstance)	//Notional Only (Potential Future Link/Trail)
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/4", theInstance)	//New Medium Term Priority
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/5", theInstance)	//New Short Term Priority
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/6", theInstance)	//Planning Short/Medium Term
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/7", theInstance)	//Existing Pathway
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/8", theInstance)	//State Horse Trail
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/9", theInstance)	//Existing Signed Trail
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/10", theInstance)	//Coastal Pathway Corridor
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/0", theInstance)	//Lot Plan
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/1", theInstance)	//Lot Plan (Aerial)
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/2", theInstance)	//Property Name
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/3", theInstance)	//House
		HandlerRegistry.registerHandler("PlanningCadastre/LandUse_SCRC/MapServer/0", theInstance)	//Urban Landuse (not a regulatory layer)
		HandlerRegistry.registerHandler("PlanningCadastre/LandUse_SCRC/MapServer/1", theInstance)	//Rural Landuse (not a regulatory layer)
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/0", theInstance)	//Covenants
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/1", theInstance)	//Easements
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/2", theInstance)	//Council Leases
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/3", theInstance)	//Land Parcel Boundaries
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/4", theInstance)	//Land Parcel Boundaries (Aerial)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/0", theInstance)	//Local Growth Management Boundaries
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/1", theInstance)	//Local Plan &amp; Other Areas
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/2", theInstance)	//Local Plan Area Boundary
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/3", theInstance)	//Local Plan Precincts and Sub-precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/4", theInstance)	//Local Plan Sub-precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/5", theInstance)	//Local Plan Precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Index_SCC/MapServer/0", theInstance)	//Map Tiles
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Index_SCC/MapServer/1", theInstance)	//Local Plan Areas
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/0", theInstance)	//Acid Sulfate Soils
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/1", theInstance)	//Airport Environs
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/2", theInstance)	//Sunshine Coast Airport
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/3", theInstance)	//Public Safety Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/6", theInstance)	//Runway Separation Distances
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/0", theInstance)	//Zone Precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/1", theInstance)	//Low Density Residential Zone Precinct
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/2", theInstance)	//Rural Zone Precinct
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/3", theInstance)	//Tourism Zone Precincts
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/0", theInstance)	//2011 ABS Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/1", theInstance)	//Number of Persons Usually Resident. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/2", theInstance)	//Number of Dwellings. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/3", theInstance)	//Language other than english spoken at home - % of total persons. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/4", theInstance)	//SEIFA 2011 Disadvantage Deciles
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/5", theInstance)	//Age
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/6", theInstance)	//Age 00 - 04 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/7", theInstance)	//Age 05 - 14 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/8", theInstance)	//Age 15 - 19 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/9", theInstance)	//Age 20 - 24 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/10", theInstance)	//Age 25 - 34 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/11", theInstance)	//Age 35 - 44 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/12", theInstance)	//Age 45 - 54 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/13", theInstance)	//Age 55 - 64 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/14", theInstance)	//Age 65 - 74 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/15", theInstance)	//Age 75 - 84 years. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/16", theInstance)	//Age 85 years and over. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/0", theInstance)	//Ambulance (State)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/1", theInstance)	//Aquatic Centres
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/2", theInstance)	//Aquatic Centres (State)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/3", theInstance)	//Aquatic Centres (Private)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/4", theInstance)	//Basketball Courts
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/5", theInstance)	//Beaches (Access Points)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/6", theInstance)	//Beaches (Patrolled)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/7", theInstance)	//BMX Facilities
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/8", theInstance)	//Cemeteries
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/9", theInstance)	//Child Care
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/10", theInstance)	//Coast Guard
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/11", theInstance)	//Coast Guard (State)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/12", theInstance)	//Community Centres
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/13", theInstance)	//Community Centres (State)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/14", theInstance)	//Community Centres (Private)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/15", theInstance)	//Community Gardens
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/16", theInstance)	//Community Markets (Weekly)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/17", theInstance)	//Community Markets (Monthly)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/18", theInstance)	//Community Markets (Seasonal)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/19", theInstance)	//Customer Service Centres
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/20", theInstance)	//Fire Brigade
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/21", theInstance)	//Fire Brigade (State)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/22", theInstance)	//Fitness Areas
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/23", theInstance)	//Food Retail Stores
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/24", theInstance)	//Liquor Store &amp; Bottle Shop
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/25", theInstance)	//Takeaway Food Store
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/26", theInstance)	//Hotel Tavern or Bar
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/27", theInstance)	//Cafe or Restaurant
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/28", theInstance)	//Bread and Cake Store &amp; Bakery Retailing
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/29", theInstance)	//Delicatessen
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/30", theInstance)	//Meat &amp; Butchery
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/31", theInstance)	//Fish and Seafood Retailing
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/32", theInstance)	//Supermarket
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/33", theInstance)	//Grocery Retailing
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/34", theInstance)	//Health Food Store
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/35", theInstance)	//Fruit Retailing and Vegetable Retailing
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/0", theInstance)	//DevelopmentApps - In Progress
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/1", theInstance)	//DevelopmentApps - Decided
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/2", theInstance)	//DevelopmentApps - All
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/3", theInstance)	//BuildingApps - In Progress
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/4", theInstance)	//BuildingApps - Decided
		HandlerRegistry.registerHandler("Staging/Applications_SCRC/MapServer/5", theInstance)	//BuildingApps - All
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/0", theInstance)	//Stormwater Assets (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/1", theInstance)	//Stormwater Pit (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/2", theInstance)	//Stormwater End Structure (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/3", theInstance)	//Stormwater Misc Infrastructure (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/4", theInstance)	//Stormwater Open Drain (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/5", theInstance)	//Stormwater Quality Device (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/6", theInstance)	//Stormwater Pipe (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/7", theInstance)	//Stormwater Culvert (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/8", theInstance)	//Stormwater Water Quality Area (Council)
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/0", theInstance)	//ManagementConsiderations
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/1", theInstance)	//Trails
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/2", theInstance)	//FireTrails
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/3", theInstance)	//TrailMaintenance
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/4", theInstance)	//BushlandOperationalAssessments
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/5", theInstance)	//New Group Layer
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/12", theInstance)	//Cities
		HandlerRegistry.registerHandler("Staging/Offline_Mobile_Basemap_NAM/MapServer/10", theInstance)	//Localities
		HandlerRegistry.registerHandler("Staging/temp/MapServer/0", theInstance)	//Aerial_2015_SCRC
		HandlerRegistry.registerHandler("Staging/temp/MapServer/1", theInstance)	//Boundary
		HandlerRegistry.registerHandler("Staging/temp/MapServer/2", theInstance)	//Footprint
		HandlerRegistry.registerHandler("Staging/temp/MapServer/3", theInstance)	//Image
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/0", theInstance)	//Barbeques
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/1", theInstance)	//Water Fittings - Outdoor
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/2", theInstance)	//Shelters - Open Space
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/0", theInstance)	//Roads (Labels)
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/1", theInstance)	//Highway and Motorway Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/2", theInstance)	//Major Road Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/3", theInstance)	//Major Roads - Small Scale Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/4", theInstance)	//Major Roads - Medium Scale Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/5", theInstance)	//Major Roads - Large Scale Labels
		HandlerRegistry.registerHandler("Transportation/Transportation_RoadEvents_SCRC/MapServer/0", theInstance)	//Local Road Events
		HandlerRegistry.registerHandler("Transportation/Transportation_RoadEvents_SCRC/MapServer/1", theInstance)	//State Road Events
		HandlerRegistry.registerHandler("Transportation/Transportation_RoadEvents_SCRC/MapServer/2", theInstance)	//Road Closure Hotspots
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/0", theInstance)	//Bus Stop (No Shelter)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/1", theInstance)	//Bus Stop (With Shelter)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/2", theInstance)	//Signs (Speed - SCRC Roads)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/3", theInstance)	//Counter Sites (Permanent)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/4", theInstance)	//Traffic Signals (SCRC)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/5", theInstance)	//Traffic Signals (TMR)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/6", theInstance)	//Bridges
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/7", theInstance)	//Paths, Cycleways and Crossings
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/8", theInstance)	//Pathways (Future)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/9", theInstance)	//Railway Stations
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/10", theInstance)	//Railway Lines
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/11", theInstance)	//Road Segments
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/0", theInstance)	//WiFi Access Points
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/1", theInstance)	//Telecommunication Network
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/2", theInstance)	//Stormwater Network
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/3", theInstance)	//Stormwater Assets (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/4", theInstance)	//Stormwater Pit (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/5", theInstance)	//Stormwater End Structure (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/6", theInstance)	//Stormwater Misc Infrastructure (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/7", theInstance)	//Stormwater Open Drain (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/8", theInstance)	//Stormwater Quality Device (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/9", theInstance)	//Stormwater Pipe (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/10", theInstance)	//Stormwater Culvert (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/11", theInstance)	//Stormwater Water Quality Area (Council)
		
		//XXX:  these API endpoints exist, but we do not support them
		/*
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1991_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1991_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1994_MSC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1994_MSC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1995_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1995_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1996_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1996_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1998_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1998_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1999_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_1999_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2000_MSC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2000_MSC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2001_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2001_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2003_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2003_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2003_MSC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2003_MSC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2005_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2005_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2005_MSC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2005_MSC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2007_CCC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2007_CCC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2007_MSC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2007_MSC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2008_DERM/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2008_DERM/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2011_SCRC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2011_SCRC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2013_DERM/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2013_DERM/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2015_SCRC/ImageServer/tile/0/0/0", theInstance)	//Start Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/Aerial_2015_SCRC/ImageServer/tile/0/0/0", theInstance)	//End Tile
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/AerialMap_SCRC/MapServer/0", theInstance)	//Cities
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/AerialMap_SCRC/MapServer/1", theInstance)	//Localities
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/AerialMap_SCRC/MapServer/2", theInstance)	//Beach (labels)
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/AerialMap_SCRC/MapServer/3", theInstance)	//Transportation
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/AerialMap_SCRC/MapServer/4", theInstance)	//Roads
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/AerialMap_SCRC/MapServer/5", theInstance)	//Roads (100k+)
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/AerialMap_SCRC/MapServer/6", theInstance)	//Roads (Major)
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/LightGreyMap_SCRC/MapServer/0", theInstance)	//Sunshine Coast and Coral Sea Clip Layer
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/LightGreyMap_SCRC/MapServer/1", theInstance)	//Sunshine Coast Clip Layer
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/LightGreyMap_SCRC/MapServer/2", theInstance)	//Reference Layers
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/LightGreyMap_SCRC/MapServer/3", theInstance)	//Labels (600k+)
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/LightGreyMap_SCRC/MapServer/4", theInstance)	//Cities
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/0", theInstance)	//Sunshine Coast and Coral Sea Clip Layer
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/1", theInstance)	//Sunshine Coast Clip Layer
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/2", theInstance)	//Cities
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/3", theInstance)	//Localities
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/4", theInstance)	//Beach (labels)
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/5", theInstance)	//Transportation
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/6", theInstance)	//Roads
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/7", theInstance)	//Roads (100k+)
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/ReferenceMap_SCRC/MapServer/8", theInstance)	//Roads (Major)
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/StreetMap_SCRC/MapServer/0", theInstance)	//Sunshine Coast and Coral Sea Clip Layer
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/StreetMap_SCRC/MapServer/1", theInstance)	//Sunshine Coast Clip Layer
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/StreetMap_SCRC/MapServer/2", theInstance)	//New Group Layer
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/StreetMap_SCRC/MapServer/9", theInstance)	//Cities
		HandlerRegistry.registerHandler("ImageryBaseMapsEarthCover/StreetMap_SCRC/MapServer/7", theInstance)	//Localities
		*/
	}
	
	public JSONArray handleRequest(String context, String params) {
		def result = new JSONArray()
		
		//manipulate the input params a bit
		def paramMap = params.split('&').collectEntries { param ->
			param.split('=').collect { it }
		}
		
		paramMap.f = "pjson"				//only JSON is supported
		if (! paramMap.outSR) {
			paramMap.outSR = "4326"			//set the output type to lat/long if none has been specified
		}
		if (! paramMap.outFields) {
			paramMap.outFields = "*"		//return all fields by default
		}
		if (! paramMap.where) {
			paramMap.where = "1=1"			//return all items by default
		}
		
		//put the input params back
		def newParams = ""
		paramMap.each { entry ->
			if (! newParams.equals("")) {
				newParams += "&"
			}
			newParams += "${ entry.key }=${ entry.value }"
		}
		params = newParams
		
		//now make the request
		def requestUrl = "${ serviceRoot }/${ context }/query?${ params }"
		println "${ requestUrl }"
		try {
			def response = requestUrl.toURL().text
			def responseObj = JSONValue.parse(response)	
			
			//we want to transform the result a bit, to make it more convenient to work with on the client-side
			responseObj.features.each { obj ->
				def newObj = obj.attributes
				newObj.geometry = obj.geometry
				if (newObj.geometry.y) {
					newObj.latitude = newObj.geometry.y
				}
				if (newObj.geometry.x) {
					newObj.longitude = newObj.geometry.x
				}
				
				result.add(newObj)
			}
		}
		catch (e) {
			//nothing we can do
		}
		
		return result
	};
}
