package tests;

import basic.BasicActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.IssuesPage;
import pages.SearchResultsPage;
import pages.YouTrackLogin;
import utils.ScreenshotUtils;
import utils.Wait;

import static pages.BasePage.open;
import static utils.CsvReader.getPassword;
import static utils.CsvReader.getUsername;

public class TestDelTask {
    @Test
    public void deleteTask() {
        final String DEL = "Тестовая задача";
        WebDriver driver = BasicActions.createDriver();
        Wait wait = new Wait();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            open();

            DashboardPage dashboard = loginPage.login(
                    getUsername(),
                    getPassword()
            );

            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
            IssuesPage issuesPage = new IssuesPage(driver);

            ScreenshotUtils.capture(driver, "deleteTask", "after_login");

            wait.waitSeconds(driver, 3);
            dashboard.clickTasksButton();
            wait.waitSeconds(driver, 3);

            dashboard.inputSearch(DEL + Keys.ENTER);
            wait.waitSeconds(driver, 3);

            ScreenshotUtils.capture(driver, "deleteTask", "search_results");

            boolean found = searchResultsPage.searchResult();
            Assert.assertTrue(found, "Задача не найдена для удаления");

            searchResultsPage.clickTask();
            wait.waitSeconds(driver, 3);

            ScreenshotUtils.capture(driver, "deleteTask", "task_page");

            issuesPage.clickPoints();
            wait.waitSeconds(driver, 3);

            ScreenshotUtils.capture(driver, "deleteTask", "menu_opened");

            issuesPage.clickDel();
            wait.waitSeconds(driver, 3);

            ScreenshotUtils.capture(driver, "deleteTask", "confirmation_dialog");

            issuesPage.buttonDel();
            wait.waitSeconds(driver, 3);

            ScreenshotUtils.capture(driver, "deleteTask", "after_deletion");

        } catch (InterruptedException e) {
            wait.waitSeconds(driver, 3);
            ScreenshotUtils.capture(driver, "deleteTask", "INTERRUPTED");
            Assert.fail("Тест был прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "deleteTask", "ERROR");
            Assert.fail("Error");
        } finally {
            driver.quit();
        }
    }
}
