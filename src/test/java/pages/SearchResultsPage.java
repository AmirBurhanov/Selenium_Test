package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    private final By POSITIV_SEARCH = By.xpath("//mark[@class='summaryMatch__d39b']");

    public boolean searchResult() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            boolean hasResults = !results.isEmpty() && results.getFirst().isDisplayed();

            Allure.addAttachment("Результаты поиска",
                    STR."""
Найдено элементов: \{results.size()}
Есть результаты: \{hasResults}""");

            return hasResults;
        } catch (Exception e) {
            Allure.addAttachment("Ошибка поиска", STR."Исключение: \{e.getMessage()}");
            return false;
        }
    }

    public void clickTask() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            if (!results.isEmpty()) {
                Allure.addAttachment("Клик по задаче", "Кликаем по первому результату поиска");
                results.getFirst().click();
            } else {
                Allure.addAttachment("Нет результатов", "Нечего кликать - список результатов пуст");
            }
        } catch (Exception e) {
            Allure.addAttachment("Ошибка клика по задаче", STR."Исключение: \{e.getMessage()}");
            throw new RuntimeException("Не удалось кликнуть по задаче", e);
        }
    }

    public int getHighlightedResultsCount() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            int count = results.size();
            Allure.addAttachment("Количество результатов", String.valueOf(count));
            return count;
        } catch (Exception e) {
            Allure.addAttachment("Ошибка подсчета результатов", STR."Исключение: \{e.getMessage()}");
            return 0;
        }
    }

    public String getFirstHighlightedText() {
        try {
            List<WebElement> results = driver.findElements(POSITIV_SEARCH);
            if (!results.isEmpty()) {
                String text = results.getFirst().getText();
                Allure.addAttachment("Текст первого результата", text);
                return text;
            }
            Allure.addAttachment("Пустые результаты", "Список результатов пуст");
            return "";
        } catch (Exception e) {
            Allure.addAttachment("Ошибка получения текста", STR."Исключение: \{e.getMessage()}");
            return "";
        }
    }


}