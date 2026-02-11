package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchResultsPage;

public class TestSearch extends BaseTest {
    @Test
    public void testSearch() throws Exception {
        final String SEARCH_QUERY = "Тестовая задача";

        try {
            dashboardPage
                    .clickTasksButton()
                    .inputSearch(SEARCH_QUERY + Keys.ENTER);

            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

            boolean found = searchResultsPage.searchResult();
            Assert.assertTrue(found, "Поиск не вернул результатов");

            int resultCount = searchResultsPage.getHighlightedResultsCount();

            if (resultCount > 0) {
                String firstResult = searchResultsPage.getFirstHighlightedText();
                Assert.assertTrue(firstResult.contains(SEARCH_QUERY),
                        "Результат не содержит искомый текст");
            }
            captureScreenshot("TestSearch", "Результат пойска");

        } catch (Exception e) {
            handleTestException("testSearch", e);
        }
    }
}