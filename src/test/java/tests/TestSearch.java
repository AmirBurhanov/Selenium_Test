package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchResultsPage;
import tests.base.BaseTest;

public class TestSearch extends BaseTest {
    @Test
    public void testSearch() throws Exception {
        final String SEARCH_QUERY = "Тестовая задача";

        try {
            captureScreenshot("testSearch", "dashboard");

            wait.waitSeconds(driver, 3);
            dashboardPage.clickTasksButton();

            captureScreenshot("testSearch", "tasks_page");

            wait.waitSeconds(driver, 3);
            dashboardPage.inputSearch(SEARCH_QUERY + Keys.ENTER);

            captureScreenshot("testSearch", "search_results");

            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

            boolean found = searchResultsPage.searchResult();
            Assert.assertTrue(found, "Поиск не вернул результатов");

            int resultCount = searchResultsPage.getHighlightedResultsCount();

            if (resultCount > 0) {
                String firstResult = searchResultsPage.getFirstHighlightedText();
                Assert.assertTrue(firstResult.contains(SEARCH_QUERY),
                        "Результат не содержит искомый текст");
            }

        } catch (Exception e) {
            handleTestException("testSearch", e);
        }
    }
}