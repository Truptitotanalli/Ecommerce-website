package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ReuseableComponents.Baseclass;

public class checkout extends Baseclass{


WebDriver driver;

public checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}



@FindBy(css=".action__submit")
WebElement checkout;

@FindBy(css=".hero-primary")
WebElement msg;

@FindBy(css="button[type='button']:nth-of-type(2)")
WebElement selectIndia;


@FindBy(css = "[placeholder='Select Country']")
WebElement countryInput;


By waitToinVisible= By.cssSelector(".ngx-spinner-overlay");


public boolean checkoutpage(String countryName) {
    try {
        Actions a = new Actions(driver);
        a.sendKeys(countryInput, countryName).build().perform();

        waitToVisible(selectIndia);
        selectIndia.click();

        checkout.click();

        String message = msg.getText().trim();
        System.out.println("Checkout message: " + message);

        // Instead of assert (POM should not assert), return validation result
        return message.equalsIgnoreCase("Thankyou for the order.");
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}
