package Extentreportcomponents;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extentreports {

    public static ExtentReports configuration() {

        // ✅ Define report path
        String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";

        // ✅ Ensure reports folder exists before creating report
        File reportDir = new File(System.getProperty("user.dir") + "\\reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs(); // creates folder automatically if missing
            System.out.println("📁 Created reports folder at: " + reportDir.getAbsolutePath());
        }

        // ✅ Create HTML reporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Report");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.DARK);

        // ✅ Create and configure ExtentReports instance
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        
    	extent.setSystemInfo("Tester","Trupti");
        extent.setSystemInfo("Browser", "Chrome");

        System.out.println("✅ Extent Report configured at: " + reportPath);
        return extent;
    }
    
    
  
	
	

}
