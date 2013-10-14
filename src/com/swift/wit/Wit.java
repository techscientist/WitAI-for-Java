package com.swift.wit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Wit {

private WitToken token;
boolean isLogging = false;

public void setToken(WitToken token){
if(token == null){
	try {
		throw(new WitException("Token provided was null"));
	} catch (WitException e) {
		e.printStackTrace();
	} 
}else{
if(isLogging){
System.out.println("Wit: Set token to "+token.getToken());	
}
this.token = token;
}
}

public void setDebugMode(boolean isLogging){
this.isLogging = isLogging;
}

public WitObject sendQuery(String data){
	if(token != null){
	try{
	String url = "https://api.wit.ai/message?q="+URLEncoder.encode(data, "UTF-8");
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");
	String bearer = "Bearer "+token.getToken();
	con.addRequestProperty("Authorization", bearer);
	int responseCode = con.getResponseCode();
	if(isLogging){
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
	}

	BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();
	if(isLogging){
		System.out.println("Response JSON: "+response.toString());
	}
	JSONParser parser = new JSONParser();
	JSONObject root = (JSONObject)parser.parse(response.toString());
	String msgBody = root.get("msg_body").toString();
	String msgID = root.get("msg_id").toString();
	JSONObject outcome = (JSONObject)root.get("outcome");
	String intent = outcome.get("intent").toString();
	String confidence = outcome.get("confidence").toString();
	List<WitEntity> entitysList = new ArrayList<WitEntity>();
	JSONObject entitys = (JSONObject)outcome.get("entities");
	Iterator<?> it = entitys.entrySet().iterator();
    while (it.hasNext()) {
        @SuppressWarnings("rawtypes")
		Map.Entry pairs = (Map.Entry)it.next();
        JSONObject obj2 = (JSONObject)pairs.getValue();
        entitysList.add(new WitEntity((String)obj2.get("value"), (String)obj2.get("body"), (long)obj2.get("start"), (long)obj2.get("end")));
        it.remove(); // avoids a ConcurrentModificationException
    }
	return new WitObject(msgID, msgBody, new WitIntent(intent, entitysList), Double.valueOf(confidence));
	}catch(Exception e){
		try {
			throw(new WitException("An error has happened."));
		} catch (WitException e1) {
			e1.printStackTrace();
		}	
	}
	}else{
	try {
		throw(new WitException("Token provided was null"));
	} catch (WitException e) {
		e.printStackTrace();
	} 
    return null;	
	}
	return null;
}
}
