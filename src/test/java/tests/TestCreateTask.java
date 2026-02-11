package tests;

import org.testng.annotations.Test;


public class TestCreateTask extends BaseTest {

    @Test
    public void testFullTaskCreationFlow() throws Exception {
        try {
            dashboardPage
                    .clickTasksButton()
                    .clickNewTask()
                    .enterTitle("Тестовая задача")
                    .enterDescription("Описание")
                    .clickCreate();

            captureScreenshot("testFullTaskCreationFlow", "after_create");
        } catch (Exception e) {
            handleTestException("testFullTaskCreationFlow", e);
        }
    }
}