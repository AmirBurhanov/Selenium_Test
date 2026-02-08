package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.IssuesPage;
import pages.SearchResultsPage;
import tests.base.BaseTest;

public class TestDelTask extends BaseTest {
    @Test
    public void deleteTask() {
        final String DEL = "Тестовая задача";

        try {
            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
            IssuesPage issuesPage = new IssuesPage(driver);

            captureScreenshot("deleteTask", "after_login");

            wait.waitSeconds(driver, 3);
            dashboardPage.clickTasksButton();
            wait.waitSeconds(driver, 3);

            dashboardPage.inputSearch(DEL + Keys.ENTER);
            wait.waitSeconds(driver, 3);

            captureScreenshot("deleteTask", "search_results");

            boolean found = searchResultsPage.searchResult();
            Assert.assertTrue(found, "Задача не найдена для удаления");

            searchResultsPage.clickTask();
            wait.waitSeconds(driver, 3);

            captureScreenshot("deleteTask", "task_page");

            issuesPage.clickPoints();
            wait.waitSeconds(driver, 3);

            captureScreenshot("deleteTask", "menu_opened");

            issuesPage.clickDel();
            wait.waitSeconds(driver, 3);

            captureScreenshot("deleteTask", "confirmation_dialog");

            issuesPage.buttonDel();
            wait.waitSeconds(driver, 3);

            captureScreenshot("deleteTask", "after_deletion");

        } catch (InterruptedException e) {
            wait.waitSeconds(driver, 3);
            captureScreenshot("deleteTask", "INTERRUPTED");
            Assert.fail("Тест был прерван");
        } catch (Exception e) {
            captureScreenshot("deleteTask", "ERROR");
            Assert.fail("Error: " + e.getMessage());
        }
    }
}