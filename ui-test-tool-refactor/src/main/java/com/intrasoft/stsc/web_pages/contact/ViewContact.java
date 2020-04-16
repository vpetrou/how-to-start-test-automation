package com.intrasoft.stsc.web_pages.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.intrasoft.stsc.test_utils.BaseWebPage;
import com.intrasoft.stsc.test_utils.common.Wait;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by vpetrou on 1/3/2018.
 */
public class ViewContact extends BaseWebPage {

    @FindBy(xpath = "//a[contains(.,'Back')]")
    WebElement backBtn;
    @FindBy(xpath = "//a[contains(.,'Edit')]")
    WebElement editBtn;
    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    WebElement deleteBtn;

    protected WebDriver driver;

    public ViewContact(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ViewContact verifyViewMode() {
        // verify View Mode
        assertFalse(isElementPresent(By.xpath("//*[@class='list']//*[contains(@class,'form-control')]")));
        return this;
    }

    public ViewContact verifyViewListContains(String content) {
        assertTrue(isElementPresent(By.xpath("//*[@class='list']//*[contains(.,'" + content + "')]")));
        return this;
    }

    public ViewContact clickEdit() {
        editBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public ViewContact clickBack() {
        backBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public ViewContact clickDelete() {
        deleteBtn.click();
        driver.switchTo().alert().accept();
        // switch to the basic window
        driver.switchTo().defaultContent();
        Wait.forPageToLoad(driver);
        return this;
    }



}
