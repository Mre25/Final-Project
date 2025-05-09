package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(String browser) {
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver","C:/selenium webdriver/ChromeDriver/chromedriver-win32/chromedriver-win32/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                driver.get("https://parabank.parasoft.com");
            }
        } catch (Exception e) {
            System.err.println("Error during setup: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                Thread.sleep(3000); 
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
    }
}