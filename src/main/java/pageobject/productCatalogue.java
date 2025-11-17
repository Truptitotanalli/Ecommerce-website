package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ReuseableComponents.Baseclass;

public class productCatalogue extends Baseclass {
	
	WebDriver driver;
	
	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	
@FindBy(css=".col-lg-4")
List<WebElement> products;




By item1= By.cssSelector(".col-lg-4");

public List<WebElement> getProductslist() {
	waitForElementToAppear(item1);
	return products;
}

public WebElement selectproductfromList(String productz) {
	for (WebElement productItem : products) {
	    List<WebElement> nameElements = productItem.findElements(By.tagName("b"));
	   
	    for (WebElement nameElement : nameElements) {
	    	String text = nameElement.getText();   // get visible text of element
	    	if (text.equals(productz)) {
	            productItem.findElement(By.cssSelector("button:last-of-type")).click();
	           return productItem;
	        }

	    }
	
	}
	return null;
}
	
	By waitToinVisible= By.cssSelector(".ngx-spinner-overlay");
	

			// now safely click Cart button
			
	

	public Cartpage itemsInCart() {
		
		super.gotocartpage();
		//return new Cartpage(driver);
		Cartpage ct = new Cartpage(driver);
		return ct;
		
	}

	 

}
