package ReuseableComponents;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class orderpage extends Baseclass {

    WebDriver driver;

    @FindBy(css = ".table-responsive")
    WebElement orderTable;

    // Locators using By for dynamic element finding inside methods
    By headers = By.xpath(".//th");
    By rows = By.xpath(".//tbody/tr");

    public orderpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrdersDisplayInOrder(String productName) {
        List<WebElement> headerElements = orderTable.findElements(headers);
        int nameColIndex = -1;

        for (int i = 0; i < headerElements.size(); i++) {
            if (headerElements.get(i).getText().trim().equalsIgnoreCase("Name")) {
                nameColIndex = i + 1;  // XPath is 1-based
                break;
            }
        }

        if (nameColIndex == -1) return false;

        List<WebElement> rowElements = orderTable.findElements(rows);
        for (WebElement row : rowElements) {
            String nameText = row.findElement(By.xpath(".//td[" + nameColIndex + "]")).getText().trim();
            if (nameText.equalsIgnoreCase(productName)) {
                System.out.println("Product found: " + nameText);
                return true;
            }
        }

        return false;
    }
}
