package tests;

import basic.BasicActions;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.YouTrackLogin;
import utils.ScreenshotUtils;
import static pages.BasePage.open;
import static utils.CsvReader.getPassword;
import static utils.CsvReader.getUsername;

@Epic("YouTrack")
@Feature("Аутентификация")
@Owner("Амир")
public class TestLoginInput {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Пользователь успешно входит в систему")
    public void testLogin() {
        WebDriver driver = BasicActions.createDriver();

        try {
            YouTrackLogin youTrackLogin = new YouTrackLogin(driver);

            open();
            ScreenshotUtils.capture(driver, "testLogin", "login_page");


            youTrackLogin.enterUsername(getUsername())
                    .enterPassword(getPassword())
                    .clickLogin();


            ScreenshotUtils.capture(driver, "testLogin", "after_login");

            String currentUrl = driver.getCurrentUrl();
            assert currentUrl != null;
            Assert.assertFalse(currentUrl.contains("login"),
                    "Логин не удался - все еще на странице логина");


        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testLogin", "ERROR");
            Assert.fail("Error");
        } finally {
            driver.quit();
        }
    }
}


