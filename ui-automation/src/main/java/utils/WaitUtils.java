package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
            .until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public static boolean isElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        try {
            waitForElementToBeVisible(driver, locator, timeoutInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void waitForEitherElementToBeVisible(WebDriver driver, By locator1, By locator2, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
            .until(d -> {
                boolean firstPresent = driver.findElements(locator1).size() > 0;
                boolean secondPresent = driver.findElements(locator2).size() > 0;
                return firstPresent || secondPresent;
            });
    }
}