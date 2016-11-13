package au.com.suncoastpc.datagateway;

import org.json.simple.JSONArray;

public interface Handler {
	public JSONArray handleRequest(String context, String params);
}
