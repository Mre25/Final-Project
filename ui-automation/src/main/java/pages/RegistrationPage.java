package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class RegistrationPage {
    private WebDriver driver;
    
    // Locators
    private By registerLink = By.linkText("Register");
    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirmPassword = By.id("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");
    private By successMessage = By.xpath("//h1[contains(.,'Welcome')]");
    private By errorMessages = By.className("error");
    
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void navigateToRegistrationPage() {
        WaitUtils.waitForElementToBeVisible(driver, registerLink, 10).click();
    }
    
    public void registerUser(
        String fName, String lName, String addr, 
        String cityName, String stateName, String zip, 
        String phoneNum, String ssnNum, String user, 
        String pwd, String confirmPwd
    ) {
        if (fName != null) WaitUtils.waitForElementToBeVisible(driver, firstName, 5).sendKeys(fName);
        if (lName != null) WaitUtils.waitForElementToBeVisible(driver, lastName, 5).sendKeys(lName);
        if (addr != null) WaitUtils.waitForElementToBeVisible(driver, address, 5).sendKeys(addr);
        if (cityName != null) WaitUtils.waitForElementToBeVisible(driver, city, 5).sendKeys(cityName);
        if (stateName != null) WaitUtils.waitForElementToBeVisible(driver, state, 5).sendKeys(stateName);
        if (zip != null) WaitUtils.waitForElementToBeVisible(driver, zipCode, 5).sendKeys(zip);
        if (phoneNum != null) WaitUtils.waitForElementToBeVisible(driver, phone, 5).sendKeys(phoneNum);
        if (ssnNum != null) WaitUtils.waitForElementToBeVisible(driver, ssn, 5).sendKeys(ssnNum);
        if (user != null) WaitUtils.waitForElementToBeVisible(driver, username, 5).sendKeys(user);
        if (pwd != null) WaitUtils.waitForElementToBeVisible(driver, password, 5).sendKeys(pwd);
        if (confirmPwd != null) WaitUtils.waitForElementToBeVisible(driver, confirmPassword, 5).sendKeys(confirmPwd);
        
        WaitUtils.waitForElementToBeVisible(driver, registerButton, 5).click();
    }
    
    public boolean isRegistrationPageDisplayed() {
        return WaitUtils.isElementVisible(driver, firstName, 5);
    }
    
    public boolean isSuccessMessageDisplayed() {
        return WaitUtils.isElementVisible(driver, successMessage, 5);
    }
    
    public boolean isErrorMessageDisplayed() {
        return WaitUtils.isElementVisible(driver, errorMessages, 5);
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}