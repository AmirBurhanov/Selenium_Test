package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.IssuesPage;
import pages.SearchResultsPage;

public class TestDelTask extends BaseTest {
    @Test
    public void deleteTask() throws Exception {
        final String DEL = "Тестовая задача";

        try {
            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
            IssuesPage issuesPage = new IssuesPage(driver);

            dashboardPage
                    .clickTasksButton()
                    .inputSearch(DEL + Keys.ENTER);

            searchResultsPage.clickTask();

            issuesPage.deleteTask();
            captureScreenshot("TestDelTask", "Задача удалена");
        } catch (InterruptedException e) {
            handleTestException("deleteTask", e);
        }
    }
}