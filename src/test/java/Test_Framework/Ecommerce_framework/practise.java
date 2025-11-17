package Test_Framework.Ecommerce_framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practise {



	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		
		driver.findElement(By.id("userEmail")).sendKeys("Ramesh1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ramesh123@");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> prod= driver.findElements(By.cssSelector(".mb-3"));
	
		for(WebElement listitems : prod) {
		List<WebElement> items= listitems.findElements(By.tagName("b"));
				
					
					for(WebElement products : items) {
					String secondprod = products.getText();
					if (secondprod.equals("ADIDAS ORIGINAL")) {
						WebElement cart= listitems.findElement(By.cssSelector("button:last-of-type"));
						cart.click();
						break;
						
					}
					
		}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

		// now safely click Cart button
		 wait.until(ExpectedConditions.elementToBeClickable(
		        By.cssSelector("button[routerlink='/dashboard/cart']"))).click();
		 
		 //cart buy page
		  
		  
		  List<WebElement> productlist = driver.findElements(By.cssSelector(".cartSection h3"));

		  for (WebElement prodElement : productlist) {
		      String itemName = prodElement.getText();
		      
		      if (itemName.equals("ADIDAS ORIGINAL")) {
		    	    // Locate "Buy Now" button inside the same product section
		    	    WebElement buyNowBtn = prodElement.findElement(By.xpath("//button[contains(text(),'Buy Now')]"));
		    	  
		    	    wait.until(ExpectedConditions.visibilityOf(buyNowBtn));

		            Assert.assertTrue(buyNowBtn.isDisplayed(), "Buy Now button not visible!");
		            buyNowBtn.click();

		            // Handle spinner if present
		            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

		            break;
		    	}
		  	}
		  
		  Thread.sleep(5000);
		 
		Actions a = new Actions(driver);
		 a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(
			        By.cssSelector("button[type='button']:nth-of-type(2)"))).click();
		  
		 WebElement checkout= driver.findElement(By.cssSelector(".action__submit"));
		 checkout.click();
		  
		  Thread.sleep(5000);
		  WebElement msg= driver.findElement(By.cssSelector(".hero-primary"));
			String message=	msg  .getText();
		  
		  Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		 driver.close();

}

}	