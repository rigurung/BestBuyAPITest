package com.bestbuy.testcases.products;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bestbuy.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
public class T003_Post_Product extends TestBase{
	
	HashMap< String, Object> bodyMap; 
	@BeforeClass
	public void T003_Post_Product_setUp() {
		logger.info("***** Starting T003_Post_Product *****");
		RestAssured.baseURI = "http://localhost:3030/";
		httpRequest = RestAssured.given();
		
		send_post_request();
	}
	
	public void send_post_request() {
		bodyMap = new HashMap<>();
		
		 bodyMap.put("name" ,"Apple Watch Series Ultimate");
		 bodyMap.put( "type" , "Electronics");
		 bodyMap.put( "price", 1000.00);
		 bodyMap.put("shipping", 12.99);
		 bodyMap.put( "upc", "654654321");
		 bodyMap.put( "description", "Series 10");
		 bodyMap.put("manufacturer", "Apple Inc.");
		 bodyMap.put( "model", "Series 10 - Pro");
		 bodyMap.put("url", "someURLS");
		 bodyMap.put("image", "SomeImages");
		 
		 JSONObject bodyJsonObject = new JSONObject(bodyMap);
		 
		 httpRequest.header( "content-type",expected_Content_Type);
		 httpRequest.body(bodyJsonObject.toJSONString());
		 
		 response = httpRequest.request(Method.POST, "/products");
		// int statuscode = response.getStatusCode();
		 
	}
	
	@Test
	public void check_status_code() {
		logger.info("# check_status_code");
		//System.out.println( response.getStatusCode());
		int statusCode = response.getStatusCode();
		logger.info("Status code ==> " + statusCode);
		Assert.assertEquals(statusCode, expected_Status_Code_201);
		
	}
	
	@Test
	public void check_content_type() {
		logger.info("# check_content_type");
		String contentType = response.getContentType();
		logger.info("Content Type ==> " + contentType);
		Assert.assertEquals(contentType, expected_Content_Type);
		//System.out.println("Content Type: " + contentType);
	}
	
	@Test
	public void check_response_body() {
		logger.info("# check_response_body ");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody != null);
		//System.out.println(responsebody);
		
		logger.info("Response Body: " + responsebody);
		JsonPath reponseJsonPath = response.jsonPath();
		Assert.assertTrue(responsebody.contains("Apple Watch Series Ultimate"));
		//	System.out.println("Limit: "+limit);
	}
	
	@Test
	public void check_response_time() {
		logger.info("#check_response_time");
		double time = response.getTime();
		logger.info("Response Time ==> "  + time);
		Assert.assertTrue(time < 5000);
	//	System.out.println("Time: " + time);
	}
	
	@AfterClass
	public  void tearDown() {
		logger.info("***** End of T001_Get_Product_All *****");
	}
}