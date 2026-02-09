package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCreateTask extends BaseTest {

    @Test
    public void testFullTaskCreationFlow() throws Exception {
        try {
            captureScreenshot("testFullTaskCreationFlow", "after login");

            wait.waitSeconds(driver, 2);
            dashboardPage.clickTasksButton();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            captureScreenshot("testFullTaskCreationFlow", "tasks_page");

            dashboardPage
                    .clickNewTask()
                    .enterTitle("Тестовая задача")
                    .enterDescription("Описание")
                    .clickCreate();

            captureScreenshot("testFullTaskCreationFlow", "after_create");

            String currentUrl = driver.getCurrentUrl();

            Assert.assertNotNull(currentUrl);
            Assert.assertFalse(currentUrl.contains("create") || currentUrl.contains("new"),
                    "Все еще на странице создания задачи");

            Assert.assertTrue(currentUrl.contains("issue") || currentUrl.contains("task") ||
                            currentUrl.contains("/issues/") || currentUrl.contains("/agiles/"),
                    "Не перешли на страницу созданной задачи");

        } catch (Exception e) {
            handleTestException("testFullTaskCreationFlow", e);
        }
    }
}