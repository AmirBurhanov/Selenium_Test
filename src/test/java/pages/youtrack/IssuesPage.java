package pages.youtrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

public class IssuesPage extends BasePage {

    private final By POINTS = By.xpath("//button[contains(@class, 'ring-ui-button') and @aria-label='Показать больше']");
    private final By DEL_TASK = By.xpath("//span[contains(@class, 'dangerText') and text()='Удалить задачу']");
    private final By DELETE = By.xpath("//button[@data-test='confirm-ok-button']");

    public IssuesPage(WebDriver driver) {
        super(driver);
    }

    public void clickPoints() {
        WebElement pointsButton = waitElementIsVisible(POINTS);
        pointsButton.click();
    }

    public void clickDel() {
        WebElement element = driver.findElement(DEL_TASK);
        element.click();
    }

    public void buttonDel() {
        WebElement element = driver.findElement(DELETE);
        element.click();
    }


}
