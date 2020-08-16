package com.bestbuy.testcases.products;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bestbuy.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class T001_Get_Product_All extends TestBase{

	@BeforeClass
	public void T001_Get_Product_All_SetUp() {
		logger.info("***** Starting T001_Get_Product_All *****");
		RestAssured.baseURI = "http://localhost:3030/";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/products");
		
	}
	
	@Test 
	public void check_status_code() {
		logger.info("# check_status_code ");
		int statuscode = response.getStatusCode();
		logger.info("Status Code ==> " + statuscode );
		Assert.assertEquals(statuscode, expected_Status_Code_200);
		//System.out.println("Statucode: " + statuscode);
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
		String limit = reponseJsonPath.getString("limit");
		Assert.assertEquals(limit, "10");
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

