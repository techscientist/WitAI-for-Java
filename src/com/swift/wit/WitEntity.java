package com.swift.wit;

public class WitEntity {

String value;
int start;
int end;
String body;

public WitEntity(String value, String body, int start, int end){
this.value = value;
this.body = body;
this.start = start;
this.end = end;
}
public String getValue(){
return value;	
}
public String getBody(){
return body;	
}
public int getStart(){
return start;	
}
public int getEnd(){
return end;	
}
}
