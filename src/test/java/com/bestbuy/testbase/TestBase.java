package com.bestbuy.testbase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public RequestSpecification httpRequest;
	public static Response response;
	
	public static final int expected_Status_Code_200 = 200; 
	public static final int expected_Status_Code_201 = 201; 
	
	public static String expected_Content_Type = "application/json; charset=utf-8";
	public static String product_id= "43900";
	protected static String expected_product_name = "Duracell - AAA Batteries (4-Pack)";
	

	public static Logger logger;
	
	@BeforeClass
	public void setUp() {
		logger = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
}
