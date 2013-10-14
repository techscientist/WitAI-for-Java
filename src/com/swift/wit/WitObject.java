package com.swift.wit;

public class WitObject {

String messageID;
String messageBody;
WitIntent intent;
double confidence;

public WitObject(String messageID, String messageBody, WitIntent intent, double confidence){
this.messageID = messageID;
this.messageBody = messageBody;
this.intent = intent;
this.confidence = confidence;
}
public String getMessageID(){
return messageID;	
}
public String getMessageBody(){
return messageBody;	
}
public WitIntent getIntent(){
return intent;
}
public double getConfidence(){
return confidence;	
}
}
