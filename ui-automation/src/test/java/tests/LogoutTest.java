package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;

public class LogoutTest extends BaseTest {
    private LogoutPage logoutPage;

    @BeforeMethod
    public void loginFirst() {
        // Perform login (same as AccountOverviewTest)
        driver.get("https://parabank.parasoft.com");
        new LoginPage(driver).login(RegistrationTest.registeredName,"password123");
        logoutPage = new LogoutPage(driver);
    }

    @Test(priority = 1, description = "TC24: Click logout → verify login page")
    public void testLogoutRedirect() {
        logoutPage.clickLogout();
        Assert.assertTrue(logoutPage.isLoginPageDisplayed(), 
            "Did not redirect to login page after logout");
    }

    @Test(priority = 2, description = "TC25: Block access after logout",
          dependsOnMethods = "testLogoutRedirect")
    public void testBlockAccessAfterLogout() {
        // Attempt to access protected page
        driver.get("https://parabank.parasoft.com/parabank/transfer.htm");
        Assert.assertTrue(logoutPage.isLoginPageDisplayed(),
            "Protected page was accessible after logout");
    }
}