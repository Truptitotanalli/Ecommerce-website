package Test_Framework.Ecommerce_framework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ReuseableComponents.Baseclass;
import Testcomponents.Project_Utilities;
import pageobject.landingpage;
import pageobject.productCatalogue;

public class ErrorValidation extends Project_Utilities {
	@Test
	public void secondTestCase() throws IOException {
	    String productName = "ZARA COAT 3";

	    // Properly initialize and launch the application
	    landingpage lp = new landingpage(getDriver());

	    // Attempt login with wrong credentials
	    lp.loginpage("Ramesh1@gmail.com", "Ramesh12");

	    // Validate error message
	    String actualError = lp.getErrormsg();
	    System.out.println("Error message captured: " + actualError);

	    Assert.assertEquals(actualError, "Incorrect email or password.");
	    System.out.println("errorvalidation");
	}

}

