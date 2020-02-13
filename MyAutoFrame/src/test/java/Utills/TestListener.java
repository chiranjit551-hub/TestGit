package Utills;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import TestBases.TestCasesBase;

public class TestListener extends TestCasesBase implements ITestListener 
{
		 
	    private static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }
	 
	    @Override
	    public void onStart(ITestContext iTestContext) {
	        System.out.println("I am in onStart method " + iTestContext.getName());
	        iTestContext.setAttribute("WebDriver", this.driver);
	    }
	 
	    @Override
	    public void onFinish(ITestContext iTestContext) {
	        System.out.println("I am in onFinish method " + iTestContext.getName());
	        //Do tier down operations for extentreports reporting!
	        ExtentTestManager.endTest();
	        ExtentManager.getReporter().flush();
	    }
	 
	    @Override
	    public void onTestStart(ITestResult iTestResult) {
	        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	    }
	 
	    @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	        //ExtentReports log operation for passed tests.
	        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	    }
	 
	    
	    
	    
	    
	    
	    @Override
	    public void onTestFailure(ITestResult result) {
	    	System.out.println("***** Error "+result.getName()+" test has failed *****");
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
	    	String methodName=result.getName().toString().trim();
	        ITestContext context = result.getTestContext();
	    	takeScreenShot(methodName, driver);
	    	
	    }
	    
	    public void takeScreenShot(String methodName, WebDriver driver) {
	    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	 
	    	 String filePath =System.getProperty("user.dir") + "/ScreenShots/";
	         //The below method will save the screen shot in d drive with test method name 
	            try {
					FileUtils.copyFile(scrFile, new File(filePath+ methodName+ ".png"));
					System.out.println("***Placed screen shot in "+filePath+" ***");
				} catch (IOException e) {
					e.printStackTrace();
				}
	    }
	 
	    @Override
	    public void onTestSkipped(ITestResult iTestResult) {
	        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	        //ExtentReports log operation for skipped tests.
	        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	    }
	 


	
	
	
}
