package utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {

    public void waitSeconds(WebDriver driver, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        try {
            wait.until(driver1 -> false);
        } catch (TimeoutException ignored) {
        }
    }
}
