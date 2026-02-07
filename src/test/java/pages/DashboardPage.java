package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DashboardPage extends BasePage {

    private final By TASKS_BUTTON = By.xpath("//a[@data-test='ring-link issues-button']");
    private final By NEW_TASK_BUTTON = By.xpath("//span[@class='text__df15' and text()='Новая задача']");
    private final By SEARCH = By.xpath("//input[@data-test='ring-select__focus']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage clickTasksButton() throws InterruptedException {
        WebElement tasksButton = waitElementIsVisible(TASKS_BUTTON);

        tasksButton.click();
        return new DashboardPage(driver);
    }

    public CreateTaskPage clickNewTask() {
        WebElement newTask = waitElementIsVisible(NEW_TASK_BUTTON);
        newTask.click();
       return new CreateTaskPage(driver);
    }

    public DashboardPage clickSearch() {
        WebElement search = waitElementIsVisible(SEARCH);
        search.click();
        return new DashboardPage(driver);
    }

    public DashboardPage inputSearch(String search) {
        driver.findElement(SEARCH).sendKeys(search);
        return this;
    }


}