package com.swift.wit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
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
	JSONParser parser = new JSONParser();
	JSONObject root = (JSONObject)parser.parse(response.toString());
	JSONObject msgIDobj = (JSONObject)root.get("msg_id");
	JSONObject msgBodyObj = (JSONObject)root.get("msg_body");
	String msgBody = msgBodyObj.toString();
	String msgID = msgIDobj.toString();
	JSONObject outcome = (JSONObject)root.get("outcome");
	JSONObject intentObj = (JSONObject)outcome.get("intent");
	String intent = intentObj.toString();
	JSONObject confidenceObj = (JSONObject)outcome.get("confidence");
	String confidence = confidenceObj.toString();
	List<WitEntity> entitysList = new ArrayList<WitEntity>();
	JSONArray entitys = (JSONArray)outcome.get("entities");
	for(Object entity : entitys){
		
	}
	return new WitObject(msgID, msgBody, new WitIntent(intent, entitysList), Double.valueOf(confidence));
	}catch(Exception e){
	e.printStackTrace();	
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
