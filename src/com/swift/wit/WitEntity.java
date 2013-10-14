package com.swift.wit;

public class WitEntity {

private String value;
private long start;
private long end;
private String body;

public WitEntity(String value, String body, long start, long end){
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
public long getStart(){
return start;	
}
public long getEnd(){
return end;	
}
}
