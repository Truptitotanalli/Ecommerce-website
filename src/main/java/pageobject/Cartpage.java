package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ReuseableComponents.Baseclass;

public class Cartpage extends Baseclass
{


WebDriver driver;

public Cartpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

@FindBy(xpath="//button[contains(text(),'Buy Now')]")
WebElement buyNowBtn;


@FindBy(css=".cartSection h3")
List<WebElement> productlist;

@FindBy(css=".action__submit")
WebElement checkout;

@FindBy(css=".hero-primary")
WebElement msg;

@FindBy(css="button[type='button']:nth-of-type(2)")
WebElement selectIndia;


@FindBy(css = "[placeholder='Select Country']")
WebElement countryInput;


By waitToinVisible= By.cssSelector(".ngx-spinner-overlay");


public boolean verifyProductDisplayInCart(String productName) {
    for (WebElement prodElement : productlist) {
        String itemName = prodElement.getText();

        if (itemName.equalsIgnoreCase(productName)) {
            // Locate the "Buy Now" button INSIDE this product section
            //WebElement buyNowBtn = prodElement.findElement(By.xpath(".//button[contains(text(),'Buy Now')]"));

            // Wait for the button to be clickable
            waitToVisible(buyNowBtn);

            // Click on the button
            buyNowBtn.click();

            // Wait for spinner (loading overlay) to disappear
            waitForInvisibilityOfElement(waitToinVisible);

            // Return true = product found & clicked
            return true;
        }
    }

    // If product not found
    return false;
}

public pageobject.checkout submitcheckout() {
	checkout check = new checkout(driver);
	return check;
}

}