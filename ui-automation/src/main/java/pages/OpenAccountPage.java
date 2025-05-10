package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

public class OpenAccountPage {
    private WebDriver driver;
    
    // Locators
    private By openAccountLink = By.xpath("//a[contains(text(),'Open New Account')]");
    private By accountTypeDropdown = By.id("type");
    private By existingAccountDropdown = By.id("fromAccountId");
    private By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private By successMessage = By.xpath("//h1[contains(text(),'Account Opened!')]");
    private By newAccountId = By.id("newAccountId");
    
    // Transfer Funds locators
    private By transferFundsLink = By.xpath("//a[contains(text(),'Transfer Funds')]");
    private By amountInput = By.id("amount");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferButton = By.xpath("//input[@value='Transfer']");
    private By transferResultMessage = By.xpath("//h1[contains(text(),'Transfer Complete!')]");
    private By transferError = By.className("error");
    
    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void navigateToOpenAccountPage() {
        WebElement link = WaitUtils.waitForElementToBeClickable(driver, openAccountLink, 10);
        link.click();
        WaitUtils.waitForElementToBeVisible(driver, accountTypeDropdown, 5);
    }
    
    public void navigateToTransferFundsPage() {
        WebElement link = WaitUtils.waitForElementToBeClickable(driver, transferFundsLink, 10);
        link.click();
        WaitUtils.waitForElementToBeVisible(driver, amountInput, 5);
    }
    
    public void openNewAccount(String accountType, String fromAccountId) {
        if (accountType != null) {
            WebElement typeDropdown = WaitUtils.waitForElementToBeClickable(driver, accountTypeDropdown, 5);
            new Select(typeDropdown).selectByVisibleText(accountType);
        }
        
        if (fromAccountId != null) {
            WebElement accountDropdown = WaitUtils.waitForElementToBeClickable(driver, existingAccountDropdown, 5);
            new Select(accountDropdown).selectByValue(fromAccountId);
        }
        
        WebElement button = WaitUtils.waitForElementToBeClickable(driver, openAccountButton, 5);
        button.click();
        WaitUtils.waitForElementToBeVisible(driver, successMessage, 10);
    }
    
    public void transferFunds(String amount, String fromAccount, String toAccount) {
        if (amount != null) {
            WebElement amountField = WaitUtils.waitForElementToBeClickable(driver, amountInput, 5);
            amountField.clear();
            amountField.sendKeys(amount);
        }
        
        if (fromAccount != null && !fromAccount.equals(toAccount)) {
            WebElement fromDropdown = WaitUtils.waitForElementToBeClickable(driver, fromAccountDropdown, 5);
            new Select(fromDropdown).selectByValue(fromAccount);
        }
        
        if (toAccount != null && !toAccount.equals(fromAccount)) {
            WebElement toDropdown = WaitUtils.waitForElementToBeClickable(driver, toAccountDropdown, 5);
            new Select(toDropdown).selectByValue(toAccount);
        }
        
        WebElement button = WaitUtils.waitForElementToBeClickable(driver, transferButton, 5);
        button.click();
        
        // Wait for either success or error
        WaitUtils.waitForEitherElementToBeVisible(
            driver, 
            transferResultMessage, 
            transferError, 
            10
        );
    }
    
    public boolean isOpenAccountPageDisplayed() {
        return WaitUtils.isElementVisible(driver, accountTypeDropdown, 5);
    }
    
    public boolean isTransferFundsPageDisplayed() {
        return WaitUtils.isElementVisible(driver, amountInput, 5);
    }
    
    public boolean isAccountOpenedSuccessfully() {
        return WaitUtils.isElementVisible(driver, successMessage, 5);
    }
    
    public boolean isTransferSuccessful() {
        return WaitUtils.isElementVisible(driver, transferResultMessage, 5);
    }
    
    public boolean isTransferErrorDisplayed() {
        return WaitUtils.isElementVisible(driver, transferError, 5);
    }
    
    public String getNewAccountId() {
        return WaitUtils.waitForElementToBeVisible(driver, newAccountId, 5).getText();
    }
    
    public String getTransferResultText() {
        return WaitUtils.waitForElementToBeVisible(driver, transferResultMessage, 5).getText();
    }
    
    public String getTransferErrorText() {
        return WaitUtils.waitForElementToBeVisible(driver, transferError, 5).getText();
    }
}