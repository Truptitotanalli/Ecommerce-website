package com.example.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Testcomponents.Project_Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.Cartpage;
import pageobject.checkout;
import pageobject.landingpage;
import pageobject.productCatalogue;

public class puchageorder extends Project_Utilities{

	landingpage lp;
	 productCatalogue catalogue;
	 Cartpage ct;
	 checkout check;

@Given("i landed on ecommers page")
public void i_landed_on_ecommers_page() throws IOException {
   
	 landingpage lp = launchApplication();
}

@Given("loged in with username {string} and password {string}")
public void loged_in_with_username_abc_gamil_and_password_fhvkhb(String username, String password) {
    catalogue = lp.loginpage(username, password);

    List<WebElement> products = catalogue.getProductslist();
 catalogue.selectproductfromList("product name"); // Or parameterize as needed
}

@When("i add product app to cart")
public void i_add_product_app_to_cart(String productName) {
    // Use the productName passed from the feature file step
    Cartpage ct = catalogue.itemsInCart();
    Assert.assertTrue(ct.verifyProductDisplayInCart(productName));
}


@When("checkout app and submit the order")
public void checkout_app_and_submit_the_order() {
	 checkout check = ct.submitcheckout();
     check.checkoutpage("India");
}

 
   
}



