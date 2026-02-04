package pages.youtrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;
import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    private final By POSITIV_SEARCH = By.xpath("//mark[@class='summaryMatch__d39b']");

    public boolean searchResult() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            return !results.isEmpty() && results.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickTask() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            if (!results.isEmpty()) {
                results.get(0).click();
            }
        } catch (Exception e) {
            System.err.println("Не удалось кликнуть по задаче: " + e.getMessage());
        }
    }

    public int getHighlightedResultsCount() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            return results.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFirstHighlightedText() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            if (!results.isEmpty()) {
                return results.get(0).getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
}