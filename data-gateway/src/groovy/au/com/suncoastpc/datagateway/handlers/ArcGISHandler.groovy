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
		HandlerRegistry.registerHandler("Assetic/CCTV_Camera_Mapping_SecureEdit_SCRC/MapServer/0", theInstance)	//CCTV Camera - Complete
		HandlerRegistry.registerHandler("Assetic/CCTV_Camera_Mapping_SecureEdit_SCRC/MapServer/1", theInstance)	//CCTV Camera - Cannot Find
		HandlerRegistry.registerHandler("Assetic/CCTV_Camera_Mapping_SecureEdit_SCRC/MapServer/2", theInstance)	//CCTV Camera - Pending
		HandlerRegistry.registerHandler("Assetic/Cemetery_SecureEdit_SCRC/MapServer/0", theInstance)	//Kulangoor Cemetery
		HandlerRegistry.registerHandler("Assetic/Tree_Planting_SecureEdit_TEST/MapServer/0", theInstance)	//Tree Planting
		HandlerRegistry.registerHandler("Biota/Biota_SCRC/MapServer/0", theInstance)	//Regional Ecosystem PreClear - Broad Vegetation Group (1:5 million)
		HandlerRegistry.registerHandler("Biota/Biota_SCRC/MapServer/1", theInstance)	//Regional Ecosystem (v8) - Vegetation Management Status
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/0", theInstance)	//Electoral Divisions (Council-2016)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/1", theInstance)	//Electoral Divisions (Council-2014)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/2", theInstance)	//Electoral Divisions (Council-2012)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/3", theInstance)	//Electoral Divisions (State)
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/4", theInstance)	//Localities
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/5", theInstance)	//Local Government Areas
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/6", theInstance)	//Local Law 2 Animal Management
		HandlerRegistry.registerHandler("Boundaries/Boundaries_SCRC/MapServer/7", theInstance)	//Local Law 5 Parking
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/0", theInstance)	//Sunshine Coast Clip Layer
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/1", theInstance)	//2014 Contours
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/4", theInstance)	//Contours 5m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/5", theInstance)	//Contours 1m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/6", theInstance)	//10k-3k
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/8", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/9", theInstance)	//Contours 25m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/10", theInstance)	//Contours 10m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/11", theInstance)	//Contours 5m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/12", theInstance)	//25k-10k
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/14", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/15", theInstance)	//Contours 10m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/16", theInstance)	//50k-25k
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/18", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/19", theInstance)	//Contours 20m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/20", theInstance)	//100k-50k
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/22", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/23", theInstance)	//150k-100k
		HandlerRegistry.registerHandler("Elevation/Contours_AerialMap_SCRC/MapServer/25", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/0", theInstance)	//Sunshine Coast Clip Layer
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/1", theInstance)	//2014 Contours
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/4", theInstance)	//Contours 5m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/5", theInstance)	//Contours 1m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/6", theInstance)	//10k-3k
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/8", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/9", theInstance)	//Contours 25m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/10", theInstance)	//Contours 10m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/11", theInstance)	//Contours 5m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/12", theInstance)	//25k-10k
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/14", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/15", theInstance)	//Contours 10m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/16", theInstance)	//50k-25k
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/18", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/19", theInstance)	//Contours 20m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/20", theInstance)	//100k-50k
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/22", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/23", theInstance)	//150k-100k
		HandlerRegistry.registerHandler("Elevation/Contours_SCRC/MapServer/25", theInstance)	//Contours 50m
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/0", theInstance)	//Development Area (Mask)
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/1", theInstance)	//Development Area
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/2", theInstance)	//Stormtide flood model extent Sept 2012
		HandlerRegistry.registerHandler("Emergency/DisasterManagementStormtideFlood_SCRC/MapServer/3", theInstance)	//Stormtide flood extent categories Sept 2012
		HandlerRegistry.registerHandler("Emergency/FloodHazardArea_SCRC/MapServer/0", theInstance)	//Defined Flood Extent
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/0", theInstance)	//Traffic Web Cameras
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/1", theInstance)	//Storm Tide Gauges
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/2", theInstance)	//River Gauges
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/3", theInstance)	//Sandbag Stations
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/4", theInstance)	// Evacuation Centres
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/5", theInstance)	//Evacuation Areas
		HandlerRegistry.registerHandler("Emergency/SituationalAwareness_SCRC/MapServer/6", theInstance)	//Isolated Areas
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/0", theInstance)	//Conservation Group Meeting Point
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/1", theInstance)	//Environmental Landmarks
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/2", theInstance)	//Openspace (Constructed Water Bodies)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/3", theInstance)	//Openspace (Operational Category)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/4", theInstance)	//Maintenance Area (Turf Contract Zones-Boundary)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/5", theInstance)	//Maintenance Area (Turf-Open Space)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/6", theInstance)	//Maintenance Area (Turf-Road Network)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/7", theInstance)	//Maintenance Area (Turf-Grid_5000)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/8", theInstance)	//Maintenance Area (Mowing and Landscaping)
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/9", theInstance)	//(Mowing and Landscaping) Labels
		HandlerRegistry.registerHandler("Environment/Environment_SCRC/MapServer/10", theInstance)	//Slope (Grouped)
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/0", theInstance)	//Picnic Tables
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/1", theInstance)	//Street Sweeping Services
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/2", theInstance)	//Open Space Turf Maintenance
		HandlerRegistry.registerHandler("Environment/ParksandGardensContracts_SCRC/MapServer/3", theInstance)	//Road Network Turf Maintenance
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
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/0", theInstance)	//PublicPlaceBins - Mapping
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/1", theInstance)	//Garden Waste Services (Domestic)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/3", theInstance)	//Green - On Property Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/4", theInstance)	//Garden - Kerbside Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/5", theInstance)	//Recycle Waste Services (Bulk)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/6", theInstance)	//Recycle Waste Services (Commercial)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/8", theInstance)	//Recycle - Commercial Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/9", theInstance)	//Recycle Waste Services (Domestic)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/11", theInstance)	//Recycle - On Property Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/12", theInstance)	//Recycle - Kerbside Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/13", theInstance)	//General Waste Services (Bulk)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/14", theInstance)	//General Waste Services (Commercial)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/16", theInstance)	//General - Commercial Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/17", theInstance)	//General Waste Services (Domestic)
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/19", theInstance)	//General - On Property Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/20", theInstance)	//General - Kerbside Domestic Bin Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/21", theInstance)	//All Waste Services
		HandlerRegistry.registerHandler("Health/Health_SCRC/MapServer/22", theInstance)	//Holding Tanks
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/0", theInstance)	//Water Bodies
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/1", theInstance)	//Watercourses
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/2", theInstance)	//Watercourses (Indicative)
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/3", theInstance)	//Catchments (Minor) - State of the Rivers 2006
		HandlerRegistry.registerHandler("InlandWaters/InlandWaters_SCRC/MapServer/4", theInstance)	//Catchments (Major) - State of the Rivers 2006
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
		HandlerRegistry.registerHandler("Maximo/EZMaxProd_NonSecure/MapServer/22", theInstance)	//maxcust.dbo.vwEZMaxOpenWOJoin
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
		HandlerRegistry.registerHandler("Maximo/EZMaxTrain_NonSecure2/MapServer/22", theInstance)	//maxcust.dbo.vwSpatialWkOrderJoin
		HandlerRegistry.registerHandler("Maximo/TRAINMaxCWSLoggerEdit_SecureEdit/MapServer/0", theInstance)	//[system use only]
		HandlerRegistry.registerHandler("PlanningCadastre/Applications_SCRC/MapServer/0", theInstance)	//DevelopmentApps - In Progress
		HandlerRegistry.registerHandler("PlanningCadastre/Applications_SCRC/MapServer/1", theInstance)	//DevelopmentApps - Decided or Past
		HandlerRegistry.registerHandler("PlanningCadastre/Applications_SCRC/MapServer/2", theInstance)	//BuildingApps - In Progress
		HandlerRegistry.registerHandler("PlanningCadastre/Applications_SCRC/MapServer/3", theInstance)	//BuildingApps - Decided or Past
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/0", theInstance)	//Localities of Interest (2014)
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/1", theInstance)	//Localities Of Interest (2009)
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/2", theInstance)	//Recreation Trail Plan 2012
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/4", theInstance)	//New Medium Term Priority
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/5", theInstance)	//New Short Term Priority
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/6", theInstance)	//Planning Short/Medium Term
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/7", theInstance)	//Existing Pathway
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/8", theInstance)	//State Horse Trail
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/9", theInstance)	//Existing Signed Trail
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/10", theInstance)	//Coastal Pathway Corridor
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/11", theInstance)	//Open Space Strategy 2011
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/13", theInstance)	//Community Hubs
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/14", theInstance)	//Existing Signed Trails
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/15", theInstance)	//Existing Open Space
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/16", theInstance)	//Social Infrastructure Strategy 2011
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/19", theInstance)	//Community Hubs (Catchment)
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/20", theInstance)	//Future Community Facilities
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/21", theInstance)	//Existing Community Facilities
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/23", theInstance)	//Existing Community Facilities
		HandlerRegistry.registerHandler("PlanningCadastre/EndorsedStrategies_SCRC/MapServer/24", theInstance)	//Existing Community Facilities
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/0", theInstance)	//Lot Plan 
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/1", theInstance)	//Lot Plan (Aerial)
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/2", theInstance)	//Property Name
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/3", theInstance)	//House
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/4", theInstance)	//Road Names - Aerials
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/6", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/7", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/8", theInstance)	//Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/10", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/11", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/Labels_SCRC/MapServer/12", theInstance)	//Cadastre Road Centrelines
		HandlerRegistry.registerHandler("PlanningCadastre/LandUse_SCRC/MapServer/0", theInstance)	//Urban Landuse (not a regulatory layer)
		HandlerRegistry.registerHandler("PlanningCadastre/LandUse_SCRC/MapServer/1", theInstance)	//Rural Landuse (not a regulatory layer)
		HandlerRegistry.registerHandler("PlanningCadastre/LandUse_SCRC/MapServer/2", theInstance)	//CoreEdit.SCRC.PlantblLanduseCodes
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/0", theInstance)	//Covenants
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/1", theInstance)	//Easements
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/2", theInstance)	//Council Leases
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/3", theInstance)	//Land Parcel Boundaries
		HandlerRegistry.registerHandler("PlanningCadastre/ParcelInformation_SCRC/MapServer/4", theInstance)	//Land Parcel Boundaries (Aerial)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/0", theInstance)	//Local Growth Management Boundaries
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/1", theInstance)	//Local Plan & Other Areas
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/3", theInstance)	//Local Plan Precincts and Sub-precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/5", theInstance)	//Local Plan Precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/6", theInstance)	//Priority Development Area (Maroochydore City Centre)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/7", theInstance)	//Priority Development Area (Caloundra South)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/8", theInstance)	//Declared Master Planned Areas (Maroochydore & Palmview)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/9", theInstance)	//Land within Development Control Plan 1 - Kawana Waters
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Boundaries_SCC/MapServer/10", theInstance)	//Priority Infrastructure Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Index_SCC/MapServer/0", theInstance)	//Map Tiles
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Index_SCC/MapServer/1", theInstance)	//Local Plan Areas
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/0", theInstance)	//Acid Sulfate Soils
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/1", theInstance)	//Airport Environs
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/4", theInstance)	//Runway Separation Distances
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/6", theInstance)	//Runway Separation Distances
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/7", theInstance)	//On Airport Aviation Facilities
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/8", theInstance)	//Aviation Facility Sensitive Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/9", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/11", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/12", theInstance)	//Australian Noise Exposure Forecast (ANEF) Level            
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/13", theInstance)	//Caloundra Aerodrome
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/15", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/17", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/18", theInstance)	//Australian Noise Exposure Forecast (ANEF) Level
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/19", theInstance)	//Maleny
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/21", theInstance)	//Aviation Facility Sensitive Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/22", theInstance)	//Biodiversity, Waterways and Wetlands
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/25", theInstance)	//Declared Fish Habitat Area (indicative cadastral extent)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/26", theInstance)	//Riparian Protection Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/27", theInstance)	//Ramsar Wetlands (indicative cadastral extent)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/28", theInstance)	//Wetlands
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/29", theInstance)	//Waterbodies
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/30", theInstance)	//Native Vegetation Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/31", theInstance)	//Bushfire Hazard
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/33", theInstance)	//High Bushfire Hazard Area Buffer
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/34", theInstance)	//Medium Bushfire Hazard Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/35", theInstance)	//Medium Bushfire Hazard Area Buffer
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/36", theInstance)	//Coastal Protection
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/38", theInstance)	//Maritime Development Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/39", theInstance)	//Extractive Resources
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/41", theInstance)	//Local Resource / Processing Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/42", theInstance)	//Local Separation Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/43", theInstance)	//State Key Resource Area - Resource / Processing Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/44", theInstance)	//State Key Resource Area - Separation Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/45", theInstance)	//Flood Hazard
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/47", theInstance)	//Drainage Deficient Areas (Fig 8.2.7)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/48", theInstance)	//Height of Buildings and Structures
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/50", theInstance)	//Specific Site Note (for Height)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/51", theInstance)	//Heritage and Character Areas
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/53", theInstance)	//Local Heritage Place
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/54", theInstance)	//Neighbourhood Character Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/55", theInstance)	//Historical Tramway
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/56", theInstance)	//Landslide Hazard Area
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/57", theInstance)	//Steep Land (Slope)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/58", theInstance)	//Regional Infrastructure
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/60", theInstance)	//High Voltage Electricity Line and Buffer - (Electricity - Distribution)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/61", theInstance)	//High Voltage Electricity Line and Buffer (Electricity - Transmission)
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/62", theInstance)	//Water Supply Pipeline and Buffer
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/63", theInstance)	//Wastewater Treatment Plant and Buffer
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/64", theInstance)	//Railway Corridor and Buffer
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/65", theInstance)	//Dedicated Transit Corridor and Buffer
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/66", theInstance)	//Major Road Corridor and Buffer
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/67", theInstance)	//Scenic Amenity
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/69", theInstance)	//Regional Inter-Urban Break
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/70", theInstance)	//Water Resource Catchments
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Overlays_SCC/MapServer/72", theInstance)	//Water Supply Storage
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/0", theInstance)	//Zone Precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/2", theInstance)	//Rural Zone Precinct
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/3", theInstance)	//Tourism Zone Precincts
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/4", theInstance)	//Community Facilities Zone Annotations
		HandlerRegistry.registerHandler("PlanningCadastre/PlanningScheme_SunshineCoast_Zoning_SCC/MapServer/5", theInstance)	//Zones
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_20160607/MapServer/0", theInstance)	//Road Names - Aerials
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_20160607/MapServer/2", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_20160607/MapServer/3", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_20160607/MapServer/4", theInstance)	//Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_20160607/MapServer/6", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_20160607/MapServer/7", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_20160607/MapServer/8", theInstance)	//Cadastre Road Centrelines
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_AANormal/MapServer/0", theInstance)	//Road Names - Aerials
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_AANormal/MapServer/2", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_AANormal/MapServer/3", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_AANormal/MapServer/4", theInstance)	//Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_AANormal/MapServer/6", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_AANormal/MapServer/7", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_AANormal/MapServer/8", theInstance)	//Cadastre Road Centrelines
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_antialiasing/MapServer/0", theInstance)	//Road Names - Aerials
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_antialiasing/MapServer/2", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_antialiasing/MapServer/3", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_antialiasing/MapServer/4", theInstance)	//Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_antialiasing/MapServer/6", theInstance)	//Intermediate Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_antialiasing/MapServer/7", theInstance)	//Major Road Names
		HandlerRegistry.registerHandler("PlanningCadastre/temp_cadastreroadnames_antialiasing/MapServer/8", theInstance)	//Cadastre Road Centrelines
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/0", theInstance)	//2011 ABS Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/2", theInstance)	//Number of Dwellings. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/3", theInstance)	//Language other than english spoken at home - % of total persons. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/4", theInstance)	//SEIFA 2011 Disadvantage Deciles
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/5", theInstance)	//Age
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
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/17", theInstance)	//Education
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/19", theInstance)	//Highest year of school completed - Year 10. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/20", theInstance)	//Highest year of school completed - Year 11. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/21", theInstance)	//Highest year of school completed - Year 12. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/22", theInstance)	//Highest year of school completed - Year 8 or below. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/23", theInstance)	//Highest year of school completed - Year 9. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/24", theInstance)	//Income
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/26", theInstance)	//Median Total Personal Income Per Week. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/27", theInstance)	//Indigenous Status
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/29", theInstance)	//Total Indigenous Population. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/30", theInstance)	//Total Torres Strait Islander Population. Based on ABS 2011 Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/31", theInstance)	//Statistical Areas
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/33", theInstance)	//SA3 Boundaries. Based on ABS 2011 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/34", theInstance)	//2006 ABS Census Data
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/36", theInstance)	//Language other than english spoken at home - num persons. Based on ABS 2006 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/37", theInstance)	//Age
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/39", theInstance)	//5 - 14 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/40", theInstance)	//15 - 19 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/41", theInstance)	//20 - 24 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/42", theInstance)	//25 - 34 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/43", theInstance)	//35 - 44 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/44", theInstance)	//45 - 54 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/45", theInstance)	//55 - 64 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/46", theInstance)	//65 - 74 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/47", theInstance)	//75 - 84 years. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/48", theInstance)	//85 years and over. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/49", theInstance)	//Education
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/51", theInstance)	//Year 11 - percent of CD. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/52", theInstance)	//Year 10 - percent of CD. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/53", theInstance)	//Year 9 - percent of CD. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/54", theInstance)	//Year 8 or below - percent of CD. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/55", theInstance)	//Did not go to school - no. persons. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/56", theInstance)	//Income
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/58", theInstance)	//Median Family Income Per Week. Based on ABS 2006 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/59", theInstance)	//Median Individual Income Per Week. Based on ABS 2006 Census Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/60", theInstance)	//Indigenous Status
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/62", theInstance)	//Total Aboriginal Population - Sunshine Coast. Based on ABS 2006 Census  Data.
		HandlerRegistry.registerHandler("Society/Society_Demographic_SCRC/MapServer/63", theInstance)	//Total Torres Strait Islander Population - Sunshine Coast. Based on ABS 2006 Census  Data.
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
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/25", theInstance)	//Takeaway Food Store
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/26", theInstance)	//Hotel Tavern or Bar
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/27", theInstance)	//Cafe or Restaurant
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/28", theInstance)	//Bread and Cake Store & Bakery Retailing
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/29", theInstance)	//Delicatessen
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/30", theInstance)	//Meat & Butchery
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/31", theInstance)	//Fish and Seafood Retailing
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/32", theInstance)	//Supermarket
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/33", theInstance)	//Grocery Retailing
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/34", theInstance)	//Health Food Store
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/35", theInstance)	//Fruit Retailing and Vegetable Retailing
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/36", theInstance)	//Holiday Parks
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/37", theInstance)	//Hospitals (Public)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/38", theInstance)	//Hospitals (Private)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/39", theInstance)	//Libraries
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/40", theInstance)	//Libraries (Mobile)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/41", theInstance)	//Libraries (Community)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/42", theInstance)	//Playground
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/43", theInstance)	//Playground (Liberty Swing)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/44", theInstance)	//Police
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/45", theInstance)	//Police (State)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/46", theInstance)	//Public Amenities
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/47", theInstance)	//Public Artwork
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/48", theInstance)	//Schools (Public)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/49", theInstance)	//Schools (Private)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/50", theInstance)	//SES
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/51", theInstance)	//Skate Facility
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/52", theInstance)	//Visitor Information Centres
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/53", theInstance)	//Visitor Information Centres (Private)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/54", theInstance)	//Waste Facility
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/55", theInstance)	//Determinations of Native Title (National Native Title Register)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/56", theInstance)	//Schedule Of Native Title Determination Applications (NNTT)
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/57", theInstance)	//Not for Profit Groups
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/58", theInstance)	//Public Venues
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/59", theInstance)	//Dog Parks
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/60", theInstance)	//Sportground
		HandlerRegistry.registerHandler("Society/Society_SCRC/MapServer/61", theInstance)	//Table Tennis
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/0", theInstance)	//Stormwater Assets (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/2", theInstance)	//Stormwater End Structure (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/3", theInstance)	//Stormwater Misc Infrastructure (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/4", theInstance)	//Stormwater Open Drain (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/5", theInstance)	//Stormwater Quality Device (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/6", theInstance)	//Stormwater Pipe (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/7", theInstance)	//Stormwater Culvert (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/8", theInstance)	//Stormwater Water Quality Area (Council)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/9", theInstance)	//Water Fittings - Outdoor
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/10", theInstance)	//Land Parcels (Address)
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/11", theInstance)	//Augmented 3D Model Locations A
		HandlerRegistry.registerHandler("Staging/Augview_Test_SCRC/MapServer/12", theInstance)	//Augmented 3D Model Locations B
		HandlerRegistry.registerHandler("Staging/FloodHazardArea_SCRC_review/MapServer/0", theInstance)	//Defined Flood Extent
		HandlerRegistry.registerHandler("Staging/Media_SCRC/MapServer/0", theInstance)	//Media locations
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/0", theInstance)	//Local Growth Management Boundaries
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/1", theInstance)	//Local Plan & Other Areas
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/3", theInstance)	//Local Plan Precincts and Sub-precincts
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/5", theInstance)	//Local Plan Precincts
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/6", theInstance)	//Priority Development Area (Maroochydore City Centre)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/7", theInstance)	//Priority Development Area (Caloundra South)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/8", theInstance)	//Declared Master Planned Areas (Maroochydore & Palmview)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/9", theInstance)	//Land within Development Control Plan 1 - Kawana Waters
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Boundaries_SCC_20160808/MapServer/10", theInstance)	//Priority Infrastructure Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Index_SCC_WFS_TEST/MapServer/0", theInstance)	//Map Tiles
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Index_SCC_WFS_TEST/MapServer/1", theInstance)	//Local Plan Areas
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/0", theInstance)	//Acid Sulfate Soils
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/1", theInstance)	//Airport Environs
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/4", theInstance)	//Runway Separation Distances
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/6", theInstance)	//Runway Separation Distances
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/7", theInstance)	//On Airport Aviation Facilities
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/8", theInstance)	//Aviation Facility Sensitive Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/9", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/11", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/12", theInstance)	//Australian Noise Exposure Forecast (ANEF) Level            
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/13", theInstance)	//Caloundra Aerodrome
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/15", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/17", theInstance)	//Obstacle Limitation Surface (OLS)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/18", theInstance)	//Australian Noise Exposure Forecast (ANEF) Level
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/19", theInstance)	//Maleny
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/21", theInstance)	//Aviation Facility Sensitive Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/22", theInstance)	//Biodiversity, Waterways and Wetlands
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/25", theInstance)	//Declared Fish Habitat Area (indicative cadastral extent)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/26", theInstance)	//Riparian Protection Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/27", theInstance)	//Ramsar Wetlands (indicative cadastral extent)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/28", theInstance)	//Wetlands
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/29", theInstance)	//Waterbodies
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/30", theInstance)	//Native Vegetation Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/31", theInstance)	//Bushfire Hazard
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/33", theInstance)	//High Bushfire Hazard Area Buffer
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/34", theInstance)	//Medium Bushfire Hazard Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/35", theInstance)	//Medium Bushfire Hazard Area Buffer
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/36", theInstance)	//Coastal Protection
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/38", theInstance)	//Maritime Development Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/39", theInstance)	//Extractive Resources
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/41", theInstance)	//Local Resource / Processing Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/42", theInstance)	//Local Separation Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/43", theInstance)	//State Key Resource Area - Resource / Processing Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/44", theInstance)	//State Key Resource Area - Separation Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/45", theInstance)	//Flood Hazard
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/47", theInstance)	//Drainage Deficient Areas (Fig 8.2.7)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/48", theInstance)	//Height of Buildings and Structures
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/50", theInstance)	//Specific Site Note (for Height)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/51", theInstance)	//Heritage and Character Areas
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/53", theInstance)	//Local Heritage Place
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/54", theInstance)	//Neighbourhood Character Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/55", theInstance)	//Historical Tramway
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/56", theInstance)	//Landslide Hazard Area
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/57", theInstance)	//Steep Land (Slope)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/58", theInstance)	//Regional Infrastructure
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/60", theInstance)	//High Voltage Electricity Line and Buffer - (Electricity - Distribution)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/61", theInstance)	//High Voltage Electricity Line and Buffer (Electricity - Transmission)
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/62", theInstance)	//Water Supply Pipeline and Buffer
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/63", theInstance)	//Wastewater Treatment Plant and Buffer
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/64", theInstance)	//Railway Corridor and Buffer
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/65", theInstance)	//Dedicated Transit Corridor and Buffer
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/66", theInstance)	//Major Road Corridor and Buffer
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/67", theInstance)	//Scenic Amenity
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/69", theInstance)	//Regional Inter-Urban Break
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/70", theInstance)	//Water Resource Catchments
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Overlays_SCC_20160808/MapServer/72", theInstance)	//Water Supply Storage
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Zoning_SCC_20160808/MapServer/0", theInstance)	//Zone Precincts
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Zoning_SCC_20160808/MapServer/2", theInstance)	//Rural Zone Precinct
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Zoning_SCC_20160808/MapServer/3", theInstance)	//Tourism Zone Precincts
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Zoning_SCC_20160808/MapServer/4", theInstance)	//Community Facilities Zone Annotations
		HandlerRegistry.registerHandler("Staging/PlanningScheme_SunshineCoast_Zoning_SCC_20160808/MapServer/5", theInstance)	//Zones
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/0", theInstance)	//SmartCCTV
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/1", theInstance)	//SmartLighting
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/2", theInstance)	//SmartWaste
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/3", theInstance)	//SmartWaterMeters
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/4", theInstance)	//SmartWaterSensors
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/5", theInstance)	//SmartWiFi
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/6", theInstance)	//SmartParking
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/7", theInstance)	//SmartSense
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/8", theInstance)	//SmartSignage
		HandlerRegistry.registerHandler("Staging/Smart_Utlities/MapServer/9", theInstance)	//SmartFibreOptic
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/0", theInstance)	//Water Infrastructure
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/2", theInstance)	//Pump Station
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/3", theInstance)	//Network Meter
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/4", theInstance)	//Control Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/5", theInstance)	//System Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/6", theInstance)	//Auxiliary Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/7", theInstance)	//Hydrant
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/8", theInstance)	//Service Point
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/9", theInstance)	//Fitting
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/10", theInstance)	//Surge Vessel
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/11", theInstance)	//Pump Location
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/12", theInstance)	//Abandoned Water Device
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/13", theInstance)	//Service
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/14", theInstance)	//Raw Water & Trunk Main
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/15", theInstance)	//Reticulation & Scour Pipe
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/16", theInstance)	//Abandoned Water Pipe
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/17", theInstance)	//Access Chamber
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/18", theInstance)	//Structure Footprint
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/19", theInstance)	//Pressure Zone
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/20", theInstance)	//Cathodic Protection
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/21", theInstance)	//Label
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/22", theInstance)	//Water Catchments
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/24", theInstance)	//Sewer Infrastructure
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/26", theInstance)	//Pump Station
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/27", theInstance)	//Network Structure
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/28", theInstance)	//Network Meter
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/29", theInstance)	//Manhole
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/30", theInstance)	//System Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/31", theInstance)	//Auxiliary Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/32", theInstance)	//Fitting
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/33", theInstance)	//Pressure Sewer Unit
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/34", theInstance)	//Pump Location
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/35", theInstance)	//Sampling Station
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/36", theInstance)	//Service Point
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/37", theInstance)	//Storage Pipe
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/38", theInstance)	//Structure
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/39", theInstance)	//Surge Vessel
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/40", theInstance)	//Vent
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/41", theInstance)	//Abandoned Sewer Device
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/42", theInstance)	//Trunk Gravity Main
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/43", theInstance)	//Gravity Main
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/44", theInstance)	//Pressure Main
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/45", theInstance)	//Service
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/46", theInstance)	//Access Chamber
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/47", theInstance)	//Abandoned Sewer Pipe
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/48", theInstance)	//Vacuum Jump Up Point
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/49", theInstance)	//Storage Facility Footprint
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/50", theInstance)	//Label
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/51", theInstance)	//Sewer Catchments
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/53", theInstance)	//SPS Catchment
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/54", theInstance)	//Recycled Water Infrastructure
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/56", theInstance)	//Control Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/57", theInstance)	//Network Meter
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/58", theInstance)	//System Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/59", theInstance)	//Auxiliary Valve
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/60", theInstance)	//Hydrant
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/61", theInstance)	//Service Point
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/62", theInstance)	//Fitting
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/63", theInstance)	//Surge Vessel
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/64", theInstance)	//Pump Location
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/65", theInstance)	//Abandoned Recycled Water Device
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/66", theInstance)	//Service
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/67", theInstance)	//Recycled Water Main
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/68", theInstance)	//Abandoned Recycled Water Pipe
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/69", theInstance)	//Access Chamber
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/70", theInstance)	//Label
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/71", theInstance)	//Structure Footprint
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/72", theInstance)	//Sewer Infrastructure Labels
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/74", theInstance)	//Gravity Main - Diameter
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/75", theInstance)	//Pressure Main - Diameter
		HandlerRegistry.registerHandler("Staging/Utilities_Secure_UW_staging/MapServer/76", theInstance)	//Service - Diameter
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/0", theInstance)	//Barbeques
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/1", theInstance)	//Water Fittings - Outdoor
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/2", theInstance)	//Shelters (Open Space)
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/3", theInstance)	//Seats
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/4", theInstance)	//Tables
		HandlerRegistry.registerHandler("Structure/Structure_SCRC/MapServer/5", theInstance)	//Misc Public Space Infrastructure
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/0", theInstance)	//Roads (Labels)
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/2", theInstance)	//Major Road Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/4", theInstance)	//Major Roads - Medium Scale Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/5", theInstance)	//Major Roads - Large Scale Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/6", theInstance)	//Street Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/8", theInstance)	//Streets - Large Scale Labels
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/9", theInstance)	//State Roads
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/10", theInstance)	//Roads
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/12", theInstance)	//Major Roads
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/14", theInstance)	//Major Roads - Medium Scale
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/15", theInstance)	//Major Roads - Large Scale
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/16", theInstance)	//Streets
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/18", theInstance)	//Streets - Large Scale
		HandlerRegistry.registerHandler("Transportation/Roads_SCRC/MapServer/19", theInstance)	//All Roads
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
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/7", theInstance)	//Paths, Trails and Crossings
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/8", theInstance)	//Pathways (Future)
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/9", theInstance)	//Railway Stations
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/10", theInstance)	//Railway Lines
		HandlerRegistry.registerHandler("Transportation/Transportation_SCRC/MapServer/11", theInstance)	//Road Segments
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/0", theInstance)	//WiFi Access Points
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/1", theInstance)	//Telecommunication Network
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/2", theInstance)	//Stormwater Network
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/5", theInstance)	//Stormwater End Structure (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/6", theInstance)	//Stormwater Misc Infrastructure (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/7", theInstance)	//Stormwater Open Drain (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/8", theInstance)	//Stormwater Quality Device (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/9", theInstance)	//Stormwater Pipe (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/10", theInstance)	//Stormwater Culvert (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/11", theInstance)	//Stormwater Water Quality Area (Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/12", theInstance)	//Stormwater Assets (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/14", theInstance)	//Stormwater End Structure (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/15", theInstance)	//Stormwater Misc Infrastructure (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/16", theInstance)	//Stormwater Open Drain (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/17", theInstance)	//Stormwater Quality Device (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/18", theInstance)	//Stormwater Pipe (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/19", theInstance)	//Stormwater Culvert (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/20", theInstance)	//Stormwater Water Quality Area (Non Council)
		HandlerRegistry.registerHandler("UtilitiesCommunication/Utilities_SCRC/MapServer/21", theInstance)	//Electrical Infrastructure
		
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
		def paramMap = params ? params.split('&').collectEntries { param ->
			param.split('=').collect { it }
		} : [:]
		
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
			
			//println "\nRequest:  ${ requestUrl } -> \n${ response }"
			
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
				
				if (newObj.ram_id) {
					newObj._collationIdentifier = newObj.ram_id
				}
				else if (newObj.OBJECTID) {
					newObj._collationIdentifier = newObj.OBJECTID
				}
				
				result.add(newObj)
			}
		}
		catch (e) {
			//nothing we can do
			e.printStackTrace()
		}
		
		return result
	};
}
