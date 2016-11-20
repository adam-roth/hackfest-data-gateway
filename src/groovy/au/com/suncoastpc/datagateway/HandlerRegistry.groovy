package au.com.suncoastpc.datagateway

import au.com.suncoastpc.datagateway.handlers.AggregationHandler;
import au.com.suncoastpc.datagateway.handlers.ArcGISHandler
import au.com.suncoastpc.datagateway.handlers.CommentsHandler
import au.com.suncoastpc.datagateway.handlers.EventfindaHandler;
import au.com.suncoastpc.datagateway.handlers.MusoGarageHandler;
import au.com.suncoastpc.datagateway.handlers.SCCEventsHandler;
import au.com.suncoastpc.datagateway.handlers.SolbarHandler;


class HandlerRegistry {
	static final def registeredHandlers = [:]
	
	static {
		//XXX:  hack to force evaluation of static initializers (may be superfluous? no, actually doesn't appear to be superfluous)
		new ArcGISHandler()
		new CommentsHandler()
		new EventfindaHandler()
		new SolbarHandler()
		new MusoGarageHandler()
		new AggregationHandler()
		new SCCEventsHandler()
	}
	
	static synchronized def registerHandler(context, handler) {
		def handlerList = registeredHandlers.get(context)
		if (! handlerList) {
			handlerList = []
			registeredHandlers.put(context, handlerList)
		}
		
		if (! handlerList.contains(handler)) {
			handlerList.add(handler)
		}
	}
	
	static synchronized def handlersForContext(context) {
		return registeredHandlers.get(context) ?: []
	}
}
