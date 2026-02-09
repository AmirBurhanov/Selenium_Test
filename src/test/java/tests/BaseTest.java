package tests;

import basic.BasicActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.YouTrackLogin;
import utils.ScreenshotUtils;
import utils.Wait;

import static pages.BasePage.open;
import static utils.CsvReader.getPassword;
import static utils.CsvReader.getUsername;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Wait wait;
    protected DashboardPage dashboardPage;
    protected YouTrackLogin youTrackLogin;

    @BeforeMethod
    public void setUp() {
        driver = BasicActions.createDriver();
        wait = new Wait();
        login();
    }

    private void login() {
        youTrackLogin = new YouTrackLogin(driver);
        open();
        dashboardPage = youTrackLogin.login(getUsername(), getPassword());
        wait.waitSeconds(driver, 2);
    }

    protected void captureScreenshot(String testName, String description) {
        ScreenshotUtils.capture(driver, testName, description);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void handleTestException(String testName, Exception e) throws Exception {
        if (e instanceof InterruptedException) {
            wait.waitSeconds(driver, 2);
            captureScreenshot(testName, "INTERRUPTED");
            throw new RuntimeException("Тест прерван", e);
        } else {
            captureScreenshot(testName, "ERROR");
            throw e;
        }
    }
}