package Test_Framework.Ecommerce_framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ReuseableComponents.Retry;
import Testcomponents.Project_Utilities;
import pageobject.Cartpage;
import pageobject.checkout;
import pageobject.landingpage;
import pageobject.productCatalogue;

public class etest1 extends Project_Utilities {
	
	
	 public WebDriver driver;

    @Test(dataProvider="loginData",retryAnalyzer = Retry.class)
    public void firsttestcase(HashMap<String,String> input) throws IOException {
        // Local landingpage instance per test
        landingpage lp = launchApplication();
        productCatalogue catalogue = lp.loginpage(input.get("email"), input.get("pass"));

        List<WebElement> products = catalogue.getProductslist();
        catalogue.selectproductfromList(input.get("productname"));

        Cartpage ct = catalogue.itemsInCart();
        Assert.assertTrue(ct.verifyProductDisplayInCart(input.get("productname")));

        checkout check = ct.submitcheckout();
        check.checkoutpage("India");

        System.out.println("firsttestcase executed for: " + input.get("email") + " on Thread: " + Thread.currentThread().getId());
    }
    
    
    
    
    @Test(dataProvider = "orderHistoryData")
    public void orderhistorypage(String email, String password) throws IOException {
        // Local landingpage instance per test
        landingpage lp = launchApplication();
        productCatalogue catalogue = lp.loginpage(email, password);

        lp.gotoorderspage();

        System.out.println("orderhistorypage executed for: " + email + " on Thread: " + Thread.currentThread().getId());
    }

    // DataProviders
  /*  @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"Ramesh1@gmail.com", "Ramesh123@", "ZARA COAT 3"},
            {"hello@gmail.com", "rammm", "ZARA COAT 3"}
        };
    }

    @DataProvider(name = "orderHistoryData")
    public Object[][] orderHistoryData() {
        return new Object[][] {
            {"Ramesh1@gmail.com", "Ramesh123@"},
            {"hello@gmail.com", "rammm"}
        };
    }*/
    
 /*  @DataProvider(name = "loginData")
    public Object[][] loginData() {
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("email", "Ramesh1@gmail.com");
    	map.put("pass", "Ramesh123@");
    	map.put("productname", "ZARA COAT 3");
    	HashMap<String,String> map1 = new HashMap<String,String>();
    	map1.put("email", "Ramesh1gmail.com");
    	map1.put("pass", "Ramesh123");
    	map1.put("productname", "ZAR COAT 3");
    	return new Object[][] {{map}, {map}};
    
    	
    }*/
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
   List<HashMap<String,String>>data =  getjsondataToMap(System.getProperty("user.dir")+"//src//test//java//testdatapackage//testdata.json");
	return new Object[][] { {data.get(0)}, {data.get(1)}  };
	}
    
    

    
}

