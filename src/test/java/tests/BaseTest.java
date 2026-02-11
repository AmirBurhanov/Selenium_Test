package tests;

import basic.BasicActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.DashboardPage;
import pages.YouTrackLogin;
import utils.ScreenshotUtils;
import utils.Wait;
import static utils.CsvReader.getPassword;
import static utils.CsvReader.getUsername;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Wait wait;
    protected BasePage basePage;
    protected DashboardPage dashboardPage;
    protected YouTrackLogin youTrackLogin;

    @BeforeMethod
    public void setUp() {
        driver = BasicActions.createDriver();
        wait = new Wait();
        login();
    }

    private void login() {
        basePage = new BasePage(driver);
        youTrackLogin = new YouTrackLogin(driver);
        basePage.open();
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

        captureScreenshot(testName, "ERROR_" + e.getClass().getSimpleName());

        if (e instanceof InterruptedException) {
            throw new RuntimeException("Тест прерван", e);
        } else {
            throw e;
        }
    }
}