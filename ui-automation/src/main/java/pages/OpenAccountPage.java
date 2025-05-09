package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage {
    private final WebDriver driver;

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Login
    public void login(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
    }

    // Account Opening
    public void clickOpenNewAccountLink() {
        driver.findElement(By.linkText("Open New Account")).click();
    }

    public void selectAccountType(String type) {
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(type);
    }

    public void selectFirstAccount() {
        new Select(driver.findElement(By.id("fromAccountId"))).selectByIndex(0);
    }

    public void clickOpenAccountButton() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/form/div/input")).click();
    }

    public String getNewAccountId() {
        return driver.findElement(By.id("newAccountId")).getText();
    }

    // Transfer Funds
    public void clickTransferFundsLink() {
        driver.findElement(By.linkText("Transfer Funds")).click();
    }

    public void enterTransferAmount(String amount) {
        driver.findElement(By.xpath("//input[@id='amount']")).clear(); // Clear existing value
        driver.findElement(By.xpath("//input[@id='amount']")).sendKeys(amount);
    }

    public void clickTransferButton() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/form/div[2]/input")).click();
    }
}