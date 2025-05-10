package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.OpenAccountPage;
import utils.WaitUtils;

public class OpenAccountTest extends BaseTest {
    private static final String VALID_USERNAME = "user_20250510_0924";
    private static final String VALID_PASSWORD = "password123";
    private OpenAccountPage openAccountPage;
    private String newAccountId;
    
    @BeforeMethod
    public void setUpTest() {
        // Login
        driver.get("https://parabank.parasoft.com");
        driver.findElement(By.name("username")).sendKeys(VALID_USERNAME);
        driver.findElement(By.name("password")).sendKeys(VALID_PASSWORD);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        
        openAccountPage = new OpenAccountPage(driver);
        Assert.assertTrue(driver.getPageSource().contains("Accounts Overview"), 
            "Login failed - not on accounts page");
    }
    
    @Test(priority = 1)
    public void TC12_navigateToOpenAccountPage() {
        openAccountPage.navigateToOpenAccountPage();
        Assert.assertTrue(openAccountPage.isOpenAccountPageDisplayed());
    }
    
    @Test(priority = 2)
    public void TC13_openNewSavingsAccount() {
        openAccountPage.navigateToOpenAccountPage();
        openAccountPage.openNewAccount("SAVINGS", null);
        Assert.assertTrue(openAccountPage.isAccountOpenedSuccessfully());
    }
    
    @Test(priority = 3)
    public void TC14_verifyNewAccountCreated() {
        // Navigate to Accounts Overview first
        driver.findElement(By.linkText("Accounts Overview")).click();
        WaitUtils.waitForElementToBeVisible(driver, By.id("accountTable"), 5);
    }
    
    @Test(priority = 4)
    public void TC15_navigateToTransferFundsPage() {
        openAccountPage.navigateToTransferFundsPage();
        Assert.assertTrue(openAccountPage.isTransferFundsPageDisplayed());
    }
    
    @Test(priority = 5)
    public void TC16_transferFundsBetweenAccounts() {
        openAccountPage.navigateToTransferFundsPage();
        openAccountPage.transferFunds("100", "33213", "33768");
        Assert.assertTrue(openAccountPage.isTransferSuccessful());
    }
    
    @Test(priority = 6)
    public void TC17_attemptInvalidTransfer() {
        openAccountPage.navigateToTransferFundsPage();
        openAccountPage.transferFunds("50", null, null);
        Assert.assertTrue(openAccountPage.isTransferErrorDisplayed());
    }
}