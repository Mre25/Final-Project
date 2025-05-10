package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import java.text.SimpleDateFormat;
import java.util.Date;



public class RegistrationTest extends BaseTest {
    private RegistrationPage registrationPage;
    private String uniqueUsername;
    
    @BeforeMethod
    public void navigateToRegistration() {
        try {
            registrationPage = new RegistrationPage(driver);
            registrationPage.navigateToRegistrationPage();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
            uniqueUsername = "user_" + timestamp;       
            } catch (Exception e) {
            Assert.fail("Pre-test setup failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 1, description = "TC05: Navigate to registration page")
    public void verifyRegistrationPageLoads() {
        Assert.assertTrue(registrationPage.isRegistrationPageDisplayed(), 
            "Registration form fields not visible");
        Assert.assertTrue(driver.getCurrentUrl().contains("register.htm"),
            "Not on registration page");
    }
    
    @Test(priority = 2, description = "TC06: Submit form with valid details")
    public void testValidRegistration() {
        registrationPage.registerUser(
            "John", "Doe", "123 Main St", 
            "Austin", "TX", "73301", 
            "5125551234", "123456789", 
            uniqueUsername, 
            "password123", "password123"
        );
        Assert.assertTrue(registrationPage.isSuccessMessageDisplayed(),
            "Registration success message not shown");
        

    }
    
    @Test(priority = 3, description = "TC07: Submit with missing fields")
    public void testMissingMandatoryFields() {
        registrationPage.registerUser(
        		   "John", "Doe", "", 
                   "Austin", "", "73301", 
                   "5125551234", "123456789", 
                   "user_20250510_0924", 
                   "password123", "password123"
        );
        Assert.assertTrue(registrationPage.isErrorMessageDisplayed(),
            "Error message not shown for missing fields");
    }
    
    @Test(priority = 4, description = "TC08: Register with taken username",
          dependsOnMethods = "testValidRegistration")
    public void testDuplicateUsername() {
        registrationPage.registerUser(
            "Jane", "Smith", "456 Oak Ave", 
            "Dallas", "TX", "75201", 
            "2145556789", "987654321", 
            "user_20250510_0924", 
            "password456", "password456"
        );
        Assert.assertTrue(registrationPage.isErrorMessageDisplayed(),
            "Error message not shown for duplicate username");
    }
}