package com.swift.wit;

public class TestClass {

	public static void main(String[] args) {
	Wit wit = new Wit();
	wit.setDebugMode(true);
	wit.setToken(new WitToken("", ""));
	wit.sendQuery("tes");
	}
}
