package ReuseableComponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;

import Extentreportcomponents.Extentreports;





public class Listeners extends Baseclass implements ITestListener {


	  public Listeners() {
	        super(null); // we don’t have a driver at this point
	    }

	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();     // in parallel execution thread is used

    // ✅ Called before any test starts in the suite
    
    @Override
    public void onStart(ITestContext context) {
        extent = Extentreports.configuration();  // ✅ This must call your Extentreports class
    }

    // ✅ Called at the start of each test
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "✅ Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        try {
            WebDriver driver = Baseclass.getDriver(); // ✅ Always fetch from ThreadLocal
            String filePath = getscreenshot(result.getMethod().getMethodName(), driver);
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    

  

    // ✅ Called when all tests are finished
    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // write report to file
    }
}
