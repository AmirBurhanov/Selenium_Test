package pages.youtrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class TaskDetailsPage extends BasePage {

    private final By TASK_TITLE = By.cssSelector("[data-test='issue-title']");
    private final By TASK_DESCRIPTION = By.cssSelector("[data-test='issue-description']");



    public TaskDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getTaskTitle() {
        return waitElementIsVisible(TASK_TITLE).getText();
    }

    public String getTaskDescription() {
        return waitElementIsVisible(TASK_DESCRIPTION).getText();
    }

    public boolean isTaskCreated(String expectedTitle) {
        return getTaskTitle().contains(expectedTitle);
    }


}