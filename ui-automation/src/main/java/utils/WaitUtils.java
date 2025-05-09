package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean isElementVisible(WebDriver driver, By locator, int seconds) {
        try {
            waitForElementToBeVisible(driver, locator, seconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}