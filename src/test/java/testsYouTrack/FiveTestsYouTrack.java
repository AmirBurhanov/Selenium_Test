package testsYouTrack;

import basic.BasicActions;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.youtrack.*;
import utils.ScreenshotUtils;


public class FiveTestsYouTrack {

    @Test
    public void testLogin() {
        WebDriver driver = BasicActions.createDriver();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            loginPage.open("http://localhost:8080/");

            ScreenshotUtils.capture(driver, "testLogin", "login_page");

            DashboardPage dashboard = loginPage.login("Admin", "1234");

            ScreenshotUtils.capture(driver, "testLogin", "after_login");

            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("login"),
                    "Логин не удался - все еще на странице логина");

            System.out.println("Логин успешен");

        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testLogin", "ERROR");
            Assert.fail("Тест логина упал с ошибкой: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testFullTaskCreationFlow() throws InterruptedException {
        WebDriver driver = BasicActions.createDriver();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            loginPage.open("http://localhost:8080/");
            DashboardPage dashboard = loginPage.login("Admin", "1234");

            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "after_login");
            Thread.sleep(1000);

            dashboard.clickTasksButton();
            Thread.sleep(1000);
            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "tasks_page");

            TaskDetailsPage taskDetails = dashboard
                    .clickNewTask()
                    .enterTitle("Тестовая задача")
                    .enterDescription("Описание")
                    .clickCreate();

            Thread.sleep(3000);
            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "after_create");

            String currentUrl = driver.getCurrentUrl();
            System.out.println("Текущий URL: " + currentUrl);

            Assert.assertFalse(currentUrl.contains("create") || currentUrl.contains("new"),
                    "Все еще на странице создания задачи");

            Assert.assertTrue(currentUrl.contains("issue") || currentUrl.contains("task") ||
                            currentUrl.contains("/issues/") || currentUrl.contains("/agiles/"),
                    "Не перешли на страницу созданной задачи");

            System.out.println("Задача успешно создана");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "INTERRUPTED");
            Assert.fail("Тест создания задачи прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testFullTaskCreationFlow", "ERROR");
            Assert.fail("Тест создания задачи упал: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testSearch() throws InterruptedException {
        final String SEARCH_QUERY = "Тестовая задача";
        WebDriver driver = BasicActions.createDriver();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            loginPage.open("http://localhost:8080/");

            ScreenshotUtils.capture(driver, "testSearch", "login_page");

            DashboardPage dashboard = loginPage.login("Admin", "1234");

            ScreenshotUtils.capture(driver, "testSearch", "dashboard");

            dashboard.clickTasksButton();
            Thread.sleep(1000);

            ScreenshotUtils.capture(driver, "testSearch", "tasks_page");

            dashboard.inputSearch(SEARCH_QUERY + Keys.ENTER);
            Thread.sleep(2000);

            ScreenshotUtils.capture(driver, "testSearch", "search_results");

            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

            boolean found = searchResultsPage.searchResult();
            Assert.assertTrue(found, "Поиск не вернул результатов");

            int resultCount = searchResultsPage.getHighlightedResultsCount();
            System.out.println("Найдено результатов: " + resultCount);

            if (resultCount > 0) {
                String firstResult = searchResultsPage.getFirstHighlightedText();
                System.out.println("Первый результат: " + firstResult);
                Assert.assertTrue(firstResult.contains(SEARCH_QUERY),
                        "Результат не содержит искомый текст");
            }

            System.out.println("Поиск выполнен успешно");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ScreenshotUtils.capture(driver, "testSearch", "INTERRUPTED");
            Assert.fail("Тест поиска прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testSearch", "ERROR");
            Assert.fail("Тест поиска упал: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void deleteTask() {
        final String DEL = "Тестовая задача";
        WebDriver driver = BasicActions.createDriver();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            loginPage.open("http://localhost:8080/");
            DashboardPage dashboard = loginPage.login("Admin", "1234");
            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
            IssuesPage issuesPage = new IssuesPage(driver);

            ScreenshotUtils.capture(driver, "deleteTask", "after_login");

            dashboard.clickTasksButton();
            Thread.sleep(1000);

            dashboard.inputSearch(DEL + Keys.ENTER);
            Thread.sleep(2000);

            ScreenshotUtils.capture(driver, "deleteTask", "search_results");

            boolean found = searchResultsPage.searchResult();
            Assert.assertTrue(found, "Задача не найдена для удаления");

            searchResultsPage.clickTask();
            Thread.sleep(2000);

            ScreenshotUtils.capture(driver, "deleteTask", "task_page");

            issuesPage.clickPoints();
            Thread.sleep(1000);

            ScreenshotUtils.capture(driver, "deleteTask", "menu_opened");

            issuesPage.clickDel();
            Thread.sleep(1000);

            ScreenshotUtils.capture(driver, "deleteTask", "confirmation_dialog");

            issuesPage.buttonDel();
            Thread.sleep(1000);

            ScreenshotUtils.capture(driver, "deleteTask", "after_deletion");

            System.out.println("Задача успешно удалена");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ScreenshotUtils.capture(driver, "deleteTask", "INTERRUPTED");
            Assert.fail("Тест был прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "deleteTask", "ERROR");
            Assert.fail("Тест упал с ошибкой: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testCloseTaskCreation() throws InterruptedException {
        WebDriver driver = BasicActions.createDriver();

        try {
            YouTrackLogin loginPage = new YouTrackLogin(driver);
            loginPage.open("http://localhost:8080/");

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "login_page");

            DashboardPage dashboard = loginPage.login("Admin", "1234");

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "dashboard");

            dashboard.clickTasksButton();
            Thread.sleep(1000);

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "tasks_page");

            CreateTaskPage createTaskPage = dashboard.clickNewTask();
            Thread.sleep(1000);

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "create_form_opened");

            createTaskPage.enterTitle("Задача для теста закрытия");
            createTaskPage.enterDescription("Описание");
            Thread.sleep(500);

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "form_filled");

            createTaskPage.clickClose();
            Thread.sleep(1000);

            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "form_closed");

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("issues") || currentUrl.contains("dashboard"),
                    "Не вернулись на предыдущую страницу после закрытия");

            System.out.println("Форма создания задачи успешно закрыта");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "INTERRUPTED");
            Assert.fail("Тест прерван");
        } catch (Exception e) {
            ScreenshotUtils.capture(driver, "testCloseTaskCreation", "ERROR");
            Assert.fail("Тест закрытия формы упал: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}



