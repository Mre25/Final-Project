package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    private WebDriver driver;
    
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickLogout() {
        driver.findElement(By.linkText("Log Out")).click();
    }
    
    public boolean isLoginPageDisplayed() {
        return driver.findElement(By.id("loginForm")).isDisplayed();
    }
}