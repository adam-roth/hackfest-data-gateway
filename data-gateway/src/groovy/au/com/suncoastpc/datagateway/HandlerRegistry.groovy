package au.com.suncoastpc.datagateway

import au.com.suncoastpc.datagateway.handlers.ArcGISHandler


class HandlerRegistry {
	static final def registeredHandlers = [:]
	
	static {
		//XXX:  hack to force evaluation of static initializers
		new ArcGISHandler()
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
