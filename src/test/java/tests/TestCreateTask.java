package tests;

import basic.BasicActions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.YouTrackLogin;
import utils.ScreenshotUtils;
import utils.Wait;

import java.time.Duration;

import static pages.BasePage.open;
import static utils.CsvReader.getPassword;
import static utils.CsvReader.getUsername;

public class TestCreateTask {

    @Test
    public void testFullTaskCreationFlow() {
        WebDriver driver = BasicActions.createDriver();
        Wait wait = new Wait();
        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            open();
            DashboardPage dashboard = loginPage.login(
                    getUsername(),
                    getPassword()
            );

            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "after_login");

            wait.waitSeconds(driver, 3);
            dashboard.clickTasksButton();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "tasks_page");

            dashboard
                    .clickNewTask()
                    .enterTitle("Тестовая задача")
                    .enterDescription("Описание")
                    .clickCreate();

            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "after_create");

            String currentUrl = driver.getCurrentUrl();

            Assert.assertNotNull(currentUrl);
            Assert.assertFalse(currentUrl.contains("create") || currentUrl.contains("new"),
                    "Все еще на странице создания задачи");

            Assert.assertTrue(currentUrl.contains("issue") || currentUrl.contains("task") ||
                            currentUrl.contains("/issues/") || currentUrl.contains("/agiles/"),
                    "Не перешли на страницу созданной задачи");


        } catch (InterruptedException e) {
            wait.waitSeconds(driver, 3);
            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "INTERRUPTED");
            Assert.fail("Тест создания задачи прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "ERROR");
            Assert.fail("Error");
        } finally {
            driver.quit();
        }
    }
}
