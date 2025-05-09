package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    
    @BeforeMethod
    public void navigateToLogin() {
        driver.get("https://parabank.parasoft.com");
        loginPage = new LoginPage(driver);
    }
    
    @Test(priority = 1, description = "TC01: Verify valid login with correct credentials")
    public void testValidLogin() {
        driver.findElement(By.name("username")).sendKeys(RegistrationTest.registeredName);
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("welcome"), 
            "Login failed or success message not found");
    }
    
    @Test(priority = 2, description = "TC02: Verify login fails with incorrect credentials")
    public void testInvalidLogin() {
        loginPage.login("wronguser", "wrongpass");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
            "Error message not displayed for invalid login");
    }
    
    @Test(priority = 3, description = "TC03: Verify login fails with blank credentials")
    public void testBlankCredentials() {
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
            "Error message not displayed for blank credentials");
    }
    
    @Test(priority = 4, description = "TC04: Validate error message on failed login")
    public void testErrorMessageContent() {
        loginPage.login("wronguser", "wrongpass");
        String errorText = loginPage.getErrorMessageText();
        Assert.assertTrue(errorText.contains("Error") || errorText.contains("Invalid"),
            "Error message text not as expected. Actual: " + errorText);
    }
}