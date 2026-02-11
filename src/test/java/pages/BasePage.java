package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Wait;

import java.time.Duration;


public class BasePage {
    protected  WebDriver driver;
    protected Wait wait;
    private static final String BASE_URL = "http://localhost:8080/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait();
    }


    public  void open() {
        driver.get(BASE_URL);
    }



    public WebElement waitElementIsVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}