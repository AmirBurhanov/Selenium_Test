package tests;

import basic.BasicActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.SearchResultsPage;
import pages.YouTrackLogin;
import utils.ScreenshotUtils;
import utils.Wait;

import static pages.BasePage.open;
import static utils.CsvReader.getPassword;
import static utils.CsvReader.getUsername;

public class TestSearch {
    @Test
    public void testSearch() {
        final String SEARCH_QUERY = "Тестовая задача";
        WebDriver driver = BasicActions.createDriver();
        Wait wait = new Wait();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            open();

            ScreenshotUtils.capture(driver, "testSearch", "login_page");

            DashboardPage dashboard = loginPage.login(
                    getUsername(),
                    getPassword()
            );

            ScreenshotUtils.capture(driver, "testSearch", "dashboard");

            wait.waitSeconds(driver, 3);
            dashboard.clickTasksButton();

            ScreenshotUtils.capture(driver, "testSearch", "tasks_page");

            wait.waitSeconds(driver, 3);
            dashboard.inputSearch(SEARCH_QUERY + Keys.ENTER);


            ScreenshotUtils.capture(driver, "testSearch", "search_results");

            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

            boolean found = searchResultsPage.searchResult();
            Assert.assertTrue(found, "Поиск не вернул результатов");

            int resultCount = searchResultsPage.getHighlightedResultsCount();

            if (resultCount > 0) {
                String firstResult = searchResultsPage.getFirstHighlightedText();
                Assert.assertTrue(firstResult.contains(SEARCH_QUERY),
                        "Результат не содержит искомый текст");
            }


        } catch (InterruptedException e) {
            wait.waitSeconds(driver, 3);
            ScreenshotUtils.capture(driver, "testSearch", "INTERRUPTED");
            Assert.fail("Тест поиска прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testSearch", "ERROR");
            Assert.fail("Error");
        } finally {
            driver.quit();
        }
    }
}
