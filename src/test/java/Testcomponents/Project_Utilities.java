package Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.landingpage;

public class Project_Utilities {

  // Thread-safe driver instance
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
   protected landingpage lp;

    // Initialize WebDriver based on browser name
    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "/src/main/java/projectGlobalData/Globaldata.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        //prop.getProperty("browser");

        WebDriver localDriver;
        if (browserName.contains("chrome")) {
        	
        	ChromeOptions options = new ChromeOptions();
        	 WebDriverManager.chromedriver().setup();
        	 

             if(browserName.contains("headless")) {
        	options.addArguments("headless");
             }
           
            localDriver = new ChromeDriver(options);
            localDriver.manage().window().setSize(new Dimension(1440,900));   // to maximize
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            localDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            localDriver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name in Globaldata.properties");
        }

        localDriver.manage().window().maximize();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Store driver in ThreadLocal
        driver.set(localDriver);
        return getDriver();
    }

    // Retrieve driver for current thread
    public WebDriver getDriver() {
        return driver.get();
    }
    @BeforeMethod(alwaysRun = true)
    public landingpage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        lp = new landingpage(driver);
        lp.navigateToUrl();
		return lp;
    }


    @AfterMethod(alwaysRun = true)
    public void teardown() {
    	if (getDriver() != null) {
        getDriver().close();  // closes that thread's browser
        driver.remove();     // clears ThreadLocal reference
    }
    }
    
	//dataprovider
	public List<HashMap<String,String>> getjsondataToMap(String filePath) throws IOException {
		//json to string
		//readfiletostring is deprecated so we are using StandardCharsets, then will resolve the issue
	String jsondata =	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//testdatapackage//testdata.json"), StandardCharsets.UTF_8);
	

	//it will convert string data to hashmap data
	//it requires Jackson databind dependency
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsondata, new TypeReference<List<HashMap<String, String>>>() {});
	return data;

	}
	
	
}
