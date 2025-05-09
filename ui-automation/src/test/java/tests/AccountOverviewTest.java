package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountOverviewPage;
import pages.LoginPage;

public class AccountOverviewTest extends BaseTest {
    private AccountOverviewPage accountOverviewPage;
    
    @BeforeMethod
    public void loginAndNavigateToOverview() {
        // Login first
        driver.get("https://parabank.parasoft.com");
        new LoginPage(driver).login(RegistrationTest.registeredName, "password123");
        
        // Initialize page object
        accountOverviewPage = new AccountOverviewPage(driver);
    }
    
    @Test(description = "TC09: Click account number and verify navigation")
    public void testAccountNavigation() {
        accountOverviewPage.clickFirstAccount();
        Assert.assertTrue(accountOverviewPage.isAccountDetailsDisplayed(),
            "Account details page did not load after clicking account link");
    }
}