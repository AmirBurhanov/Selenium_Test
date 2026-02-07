package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YouTrackLogin extends BasePage {

    public YouTrackLogin(WebDriver driver) {
        super(driver);
    }

    private final By loginInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[@data-test='login-button']");

    public YouTrackLogin enterUsername(String username) {
        driver.findElement(loginInput).sendKeys(username);
        return this;
    }

    public YouTrackLogin enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    public DashboardPage clickLogin() {
        driver.findElement(loginButton).click();
        return new DashboardPage(driver);
    }

    public DashboardPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }
}