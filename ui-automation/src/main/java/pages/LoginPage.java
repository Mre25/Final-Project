package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {
    private WebDriver driver;
    
    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//input[@value='Log In']");
    private By errorMessage = By.className("error");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void enterUsername(String username) {
        WaitUtils.waitForElementToBeVisible(driver, usernameField, 5).sendKeys(username);
    }
    
    public void enterPassword(String password) {
        WaitUtils.waitForElementToBeVisible(driver, passwordField, 5).sendKeys(password);
    }
    
    public void clickLogin() {
        WaitUtils.waitForElementToBeVisible(driver, loginButton, 5).click();
    }
    
    public boolean isErrorMessageDisplayed() {
        return WaitUtils.isElementVisible(driver, errorMessage, 5);
    }
    
    public String getErrorMessageText() {
        return WaitUtils.waitForElementToBeVisible(driver, errorMessage, 5).getText();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}