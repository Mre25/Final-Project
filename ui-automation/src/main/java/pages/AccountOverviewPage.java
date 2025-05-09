package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class AccountOverviewPage {
    private final WebDriver driver;
    
    // Locators
    private final By accountsOverviewHeader = By.xpath("//h1[contains(text(),'Accounts Overview')]");
    private final By firstAccountLink = By.xpath("//a[contains(@href,'activity.htm?id')][1]");
    private final By accountDetailsTable = By.id("accountTable");
    
    public AccountOverviewPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickFirstAccount() {
        WaitUtils.waitForElementToBeVisible(driver, firstAccountLink, 5).click();
    }
    
    public boolean isAccountDetailsDisplayed() {
        return WaitUtils.isElementVisible(driver, accountDetailsTable, 5);
    }
}