package com.swift.wit;

public class TestClass {

	public static void main(String[] args) {
	Wit wit = new Wit();
	wit.setDebugMode(true);
	wit.setToken(new WitToken("", ""));
	WitObject obj = wit.sendQuery("hello from india where I speak albanian");
	System.out.println(obj.getMessageID());
	System.out.println(obj.getIntent().getIntentName());
	System.out.println(obj.getIntent().getEntitys().get(0).getBody());
	}
}
