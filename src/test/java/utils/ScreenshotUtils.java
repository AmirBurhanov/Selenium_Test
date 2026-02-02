package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;

public class ScreenshotUtils {

    private static final String SCREENSHOTS_DIR = "screenshots";

    static {
        File dir = new File(SCREENSHOTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void capture(WebDriver driver, String screenshotName) {
        if (driver == null) {
            System.err.println("Driver is null, cannot take screenshot");
            return;
        }

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(SCREENSHOTS_DIR,
                    screenshotName + "_" + System.currentTimeMillis() + ".png");

            FileHandler.copy(srcFile, destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());

        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public static void capture(WebDriver driver, String testName, String description) {
        capture(driver, testName + "_" + description);
    }
}