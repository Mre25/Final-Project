package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.OpenAccountPage;

public class OpenAccountTest extends BaseTest {
    private OpenAccountPage page;

    @BeforeMethod
    public void login() {
        page = new OpenAccountPage(driver);
        page.login(RegistrationTest.registeredName, "password123");
    }

    @Test(priority = 1)
    public void TC12_NavigateToOpenAccountPage() {
        page.clickOpenNewAccountLink();
        Assert.assertTrue(driver.getCurrentUrl().contains("openaccount.htm"),
            "Not on Open Account page");
    }

    @Test(priority = 2)
    public void TC13_OpenCheckingAccount() {
        page.clickOpenNewAccountLink();
        page.selectAccountType("CHECKING");
        page.selectFirstAccount();
        page.clickOpenAccountButton();
        Assert.assertTrue(page.getNewAccountId().length() > 0,
            "No account ID generated");
    }

    @Test(priority = 3)
    public void TC14_VerifyAccountInOverview() {
        page.clickOpenNewAccountLink();
        page.selectAccountType("SAVINGS");
        page.selectFirstAccount();
        page.clickOpenAccountButton();
        String newAccountId = page.getNewAccountId();

        driver.findElement(By.linkText("Accounts Overview")).click();
        Assert.assertTrue(driver.getPageSource().contains(newAccountId),
            "Account not in overview");
    }

    @Test(priority = 4)
    public void TC15_NavigateToTransferFunds() {
        page.clickTransferFundsLink();
        Assert.assertTrue(driver.getCurrentUrl().contains("transfer.htm"),
            "Not on Transfer page");
    }

    @Test(priority = 5)
    public void TC16_TransferFunds() {
        page.clickTransferFundsLink();
        page.enterTransferAmount("100");
        page.clickTransferButton();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(., 'Transfer Complete!')]")).isDisplayed(),
            "Transfer failed");
    }

    @Test(priority = 6)
    public void TC17_TransferValidation() {
        page.clickTransferFundsLink();
        page.clickTransferButton();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='amount.errors']")).isDisplayed(),
            "No validation error");
    }
}