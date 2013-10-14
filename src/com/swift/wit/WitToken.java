package com.swift.wit;

public class WitToken {

String authToken;
String instance;
public WitToken(String token, String instance){
this.authToken = token;
this.instance = instance;
}
public String getToken(){
return this.authToken;	
}
public String getInstance(){
return instance;	
}
}
