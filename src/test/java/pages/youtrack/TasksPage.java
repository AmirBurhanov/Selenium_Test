package pages.youtrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class TasksPage extends BasePage {

    private final By NEW_TASK_BUTTON = By.xpath("//span[@class='text__df15' and text()='Новая задача']");

    public TasksPage(WebDriver driver) {
        super(driver);
    }

    public CreateTaskPage clickNewTask()  {
        waitElementIsVisible(NEW_TASK_BUTTON).click();
        return new CreateTaskPage(driver);
    }

}