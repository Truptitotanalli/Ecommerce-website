package ReuseableComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Baseclass {

    // 🔸 Thread-safe driver handling
    private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public Baseclass(WebDriver driver) {
        if (driver != null) {
            tdriver.set(driver);
            PageFactory.initElements(driver, this);
        }
    }

    public static WebDriver getDriver() {
        return tdriver.get();
    }

    @FindBy(css = "button[routerlink='/dashboard/cart']")
    WebElement clickcart;

    @FindBy(css = "[routerlink='/dashboard/myorders']")
    WebElement ordersheader;

    By waitToinVisible = By.cssSelector(".ngx-spinner-overlay");

    public void waitForElementToAppear(By findBy) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForwebElementToAppear(WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInvisibilityOfElement(By findBy) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void waitToVisible(WebElement visibleelement) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(visibleelement));
    }

    public void gotocartpage() {
        waitForInvisibilityOfElement(waitToinVisible);
        waitToVisible(clickcart);
        clickcart.click();
    }

    public orderpage gotoorderspage() {
        ordersheader.click();
        waitForInvisibilityOfElement(waitToinVisible);
        orderpage orderpage = new orderpage(getDriver());
        return orderpage;
    }

    public List<HashMap<String, String>> getjsondataToMap() throws IOException {
        String jsondata = FileUtils.readFileToString(
                new File(System.getProperty("user.dir") + "//src//test//java//testdatapackage//testdata.json"),
                StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsondata,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    public String getscreenshot(String testCaseName, WebDriver driver) throws IOException {
        if (driver == null) {
            driver = getDriver();
        }

        if (driver == null) {
            throw new IOException("❌ WebDriver is null while capturing screenshot!");
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        File reportsDir = new File(System.getProperty("user.dir") + "\\reports\\");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }

        String destinationPath = reportsDir + "\\" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationPath));
        System.out.println("📸 Screenshot saved at: " + destinationPath);
        return destinationPath;
    }
}
