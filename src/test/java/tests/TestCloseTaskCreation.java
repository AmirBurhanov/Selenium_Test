package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateTaskPage;


public class TestCloseTaskCreation extends BaseTest {

    @Test
    public void testCloseTaskCreation() {
        try {
            captureScreenshot("testCloseTaskCreation", "dashboard");

            wait.waitSeconds(driver, 2);
            dashboardPage.clickTasksButton();

            captureScreenshot("testCloseTaskCreation", "after_click_tasks");

            CreateTaskPage createTaskPage = dashboardPage.clickNewTask();
            wait.waitSeconds(driver, 2);

            captureScreenshot("testCloseTaskCreation", "create_form_opened");

            createTaskPage.enterTitle("Задача для теста закрытия");
            createTaskPage.enterDescription("Описание");
            wait.waitSeconds(driver, 2);

            captureScreenshot("testCloseTaskCreation", "form_filled");

            createTaskPage.clickClose();
            wait.waitSeconds(driver, 2);

            captureScreenshot("testCloseTaskCreation", "form_closed");

            String currentUrl = driver.getCurrentUrl();
            Assert.assertNotNull(currentUrl);
            Assert.assertTrue(currentUrl.contains("issues") || currentUrl.contains("dashboard"),
                    "Не вернулись на предыдущую страницу после закрытия");

        } catch (InterruptedException e) {
            wait.waitSeconds(driver, 2);
            captureScreenshot("testCloseTaskCreation", "INTERRUPTED");
            Assert.fail("Тест прерван");
        } catch (Exception e) {
            captureScreenshot("testCloseTaskCreation", "ERROR");
            Assert.fail("Error: " + e.getMessage());
        }
    }
}