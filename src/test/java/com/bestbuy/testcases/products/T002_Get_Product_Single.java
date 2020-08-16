package com.bestbuy.testcases.products;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bestbuy.testbase.TestBase;

import groovyjarjarantlr4.v4.runtime.misc.ParseCancellationException;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class T002_Get_Product_Single extends TestBase{
	@BeforeClass
	public void T001_Get_Product_All_SetUp() {
		logger.info("***** Starting T002_Get_Product_Single *****");
		RestAssured.baseURI = "http://localhost:3030/";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "products/" + product_id);
	}
	
	@Test 
	public void check_status_code() {
		logger.info("# check_status_code ");
		int statuscode = response.getStatusCode();
		logger.info("Status Code ==> " + statuscode );
		Assert.assertEquals(statuscode, expected_Status_Code_200);
		System.out.println("Statucode: " + statuscode);
	}
	
	@Test 
	public void check_content_type() {
		logger.info("# check_content_type");
		String contentType = response.getContentType();
		logger.info("Content Type ==> " + contentType);
		Assert.assertEquals(contentType, expected_Content_Type);
		System.out.println("Content Type: " + contentType);
	}
	
	@Test
	public void check_response_body() {
		logger.info("# check_response_body ");
		String responsebody = response.getBody().asString();
		logger.info("Response Body: " + responsebody);
		Assert.assertTrue(responsebody != null);
		System.out.println(responsebody);
	}
	
	@Test
	public void check_response_id() {
		logger.info("#check_response_id");
		JsonPath responseJsonPath = response.jsonPath();
		String actual_id = responseJsonPath.getString("id");
		Assert.assertEquals(actual_id, product_id);
		logger.info("Product ID ==> " + actual_id);
		System.out.println("Product id: " + actual_id);
	}
	
	@Test
	public void check_response_content() {
		logger.info("#check_response_content");
		JsonPath responseJsonPath = response.jsonPath(); 
		String productName = responseJsonPath.getString("name");
		assertEquals(productName, expected_product_name);
		System.out.println("Product Name: " + productName);
		
	}
	@Test
	public void check_response_time() {
		logger.info("#check_response_time");
		double time = response.getTime();
		logger.info("Response Time ==> "  + time);
		Assert.assertTrue(time < 5000);
		System.out.println("Time: " + time);
	}
	
	@AfterClass
	public  void tearDown() {
		logger.info("***** End of T001_Get_Product_All *****");
	}
}
