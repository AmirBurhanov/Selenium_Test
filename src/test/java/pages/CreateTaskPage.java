package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CreateTaskPage extends BasePage {

    private final By TITLE_INPUT = By.xpath("//textarea[@data-test='summary']");
    private final By WYSIWYG_EDITOR = By.xpath("//div[@data-test='wysiwyg-editor-content']");
    private final By CREATE_BUTTON = By.xpath("//button[@data-test='submit-button']");
    private final By CANCEL_BUTTON = By.xpath("//button[.//span[text()='Отмена']]");
    private final By CLOSE = By.xpath("//span[@class='ring-ui-icon_fbfc closeButtonIcon__a24f']");

    public CreateTaskPage(WebDriver driver) {
        super(driver);
    }

    public CreateTaskPage enterTitle(String title) {
        WebElement titleField = waitElementIsVisible(TITLE_INPUT);
        titleField.clear();
        titleField.sendKeys(title);
        return this;
    }

    public CreateTaskPage enterDescription(String description) {
        WebElement editor = waitElementIsVisible(WYSIWYG_EDITOR);

        editor.click();
        editor.sendKeys(description);
        return this;
    }

    public CreateTaskPage clickCreate() throws InterruptedException {
        WebElement webElement = waitElementIsVisible(CREATE_BUTTON);

        webElement.click();
        return new CreateTaskPage(driver);
    }

    public DashboardPage clickCancel() {
        waitElementIsVisible(CANCEL_BUTTON).click();
        return new DashboardPage(driver);
    }

    public CreateTaskPage createTask(String title, String description) throws InterruptedException {
        return enterTitle(title)
                .enterDescription(description)
                .clickCreate();
    }

    public void clickClose() {
WebElement element = waitElementIsVisible(CLOSE);
element.click();
    }
}