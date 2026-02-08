package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DashboardPage extends BasePage {

    private final By TASKS_BUTTON = By.xpath("//a[@data-test='ring-link issues-button']");
    private final By NEW_TASK_BUTTON = By.xpath("//span[@class='text__df15' and text()='Новая задача']");
    private final By SEARCH = By.xpath("//input[@data-test='ring-select__focus']");
    private final By USER_BUTTON = By.xpath("//*[@id=\"menu-react-root\"]/div[2]/div[2]/div[5]/span/div");
    private final By EXIT_BUTTON = By.xpath("//button[text()='Выйти']");

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

    public DashboardPage clickUserButton() {
        driver.findElement(USER_BUTTON).click();
        return this;
    }

    public void clickExit() {
        driver.findElement(EXIT_BUTTON).click();
    }


}