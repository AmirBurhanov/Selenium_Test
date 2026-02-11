package tests;

import org.testng.annotations.Test;
import pages.CreateTaskPage;

public class TestCloseTaskCreation extends BaseTest {

    @Test
    public void testCloseTaskCreation() throws Exception {
        try {
            dashboardPage.clickTasksButton();

            CreateTaskPage createTaskPage = dashboardPage.clickNewTask();
            createTaskPage.enterTitle("Задача для теста закрытия");
            createTaskPage.enterDescription("Описание");
            createTaskPage.clickClose();

            captureScreenshot("testCloseTaskCreation", "Окно создания Задачи закрылось");
        } catch (Exception e) {
            handleTestException("testCloseTaskCreation", e);
        }
    }
}