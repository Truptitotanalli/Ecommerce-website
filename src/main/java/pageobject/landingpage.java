package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ReuseableComponents.Baseclass;

public class landingpage extends Baseclass{
	
	WebDriver driver;
	
	public landingpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
@FindBy(xpath="//input[@id='userEmail']")
WebElement email;

@FindBy(xpath="//input[@id='userPassword']")
WebElement pass;

@FindBy(xpath="//input[@type='submit']")
WebElement submit;

@FindBy(css = "[class*='flyInOut']")
WebElement loginerror;


public productCatalogue loginpage(String mail,String password) {
	email.sendKeys(mail);
	pass.sendKeys(password);
	submit.click();
	productCatalogue productCatalogue = new productCatalogue(driver);
	return productCatalogue;
	
}


public String getErrormsg() {
    
        waitForwebElementToAppear(loginerror);
        return loginerror.getText();
    
}

public void navigateToUrl() {
	driver.get("https://rahulshettyacademy.com/client");
	
}

}