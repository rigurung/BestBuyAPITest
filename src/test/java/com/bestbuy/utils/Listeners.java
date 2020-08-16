package com.bestbuy.utils;


import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listeners extends TestListenerAdapter{

	public ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter spark;
	
	
	@Override
	public void onStart(ITestContext testContext) {
		spark = new ExtentSparkReporter("Reports\\testReport.html");
		spark.config().setDocumentTitle("Best Buy API Test Reports");
		spark.config().setReportName("Functional Testing");
		spark.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Host", "localhost");
		extent.setSystemInfo("Project Name", "Best Buy API");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Rojina");
	}
	
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		test = extent.createTest(iTestResult.getName());
		test.log(Status.PASS, "Test Case is passed: " + iTestResult.getName());
	}
	
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		test = extent.createTest(iTestResult.getName());
		test.log(Status.FAIL, "Test case is failed: " + iTestResult.getName());
		test.log(Status.FAIL, "Test case is failed: " + iTestResult.getThrowable());
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		test = extent.createTest(iTestResult.getName());
		test.log(Status.SKIP, "Test Case is skipped: " + iTestResult.getName());
	}
	
	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
