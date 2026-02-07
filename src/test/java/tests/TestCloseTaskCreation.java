package tests;

import basic.BasicActions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateTaskPage;
import pages.DashboardPage;
import pages.YouTrackLogin;
import utils.ScreenshotUtils;
import utils.Wait;
import static pages.BasePage.open;
import static utils.CsvReader.getPassword;
import static utils.CsvReader.getUsername;

public class TestCloseTaskCreation {

    @Test
    public void testCloseTaskCreation() {
        WebDriver driver = BasicActions.createDriver();
        Wait wait = new Wait();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            open();

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "login_page");

            DashboardPage dashboard = loginPage.login(
                    getUsername(),
                    getPassword()
            );

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "dashboard");

            wait.waitSeconds(driver, 3);
            dashboard.clickTasksButton();


            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "tasks_page");

            CreateTaskPage createTaskPage = dashboard.clickNewTask();
            wait.waitSeconds(driver, 2);

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "create_form_opened");

            createTaskPage.enterTitle("Задача для теста закрытия");
            createTaskPage.enterDescription("Описание");
            wait.waitSeconds(driver, 2);

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "form_filled");

            createTaskPage.clickClose();
            wait.waitSeconds(driver, 2);

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "form_closed");

            String currentUrl = driver.getCurrentUrl();
            Assert.assertNotNull(currentUrl);
            Assert.assertTrue(currentUrl.contains("issues") || currentUrl.contains("dashboard"),
                    "Не вернулись на предыдущую страницу после закрытия");

        } catch (InterruptedException e) {
            wait.waitSeconds(driver, 2);
            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "INTERRUPTED");
            Assert.fail("Тест прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "ERROR");
            Assert.fail("Error");
        } finally {
            driver.quit();
        }
    }
}
