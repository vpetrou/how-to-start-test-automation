package com.intrasoft.stsc.web_pages.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.intrasoft.stsc.test_utils.BaseWebPage;
import com.intrasoft.stsc.test_utils.common.Wait;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by vpetrou on 1/3/2018.
 */
public class ListOfContacts extends BaseWebPage {

    protected WebDriver driver;

    public ListOfContacts(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ListOfContacts verifyPageOpens() {
        // verify "List of Contacts" page
        assertTrue(isElementPresent(By.xpath("//h3[contains(.,'Contact List')]")));
        return this;
    }

    public ListOfContacts verifyTableContains(String content) {
        // verify that table contains the appropriate content
        assertTrue(isElementPresent(By.xpath("//table[contains(.,'" + content + "')]")));
        return this;
    }

    public ListOfContacts verifyTableNotContains(String content) {
        // verify that table contains the appropriate content
        assertFalse(isElementPresent(By.xpath("//table[contains(.,'" + content + "')]")));
        return this;
    }

    public ListOfContacts clickDetailsOf(String content) {
        // click on link DETAILS of line with text of content
        driver.findElement(By.xpath("//table[contains(.,'" + content + "')]//a[contains(.,'Details')][1]")).click();
        Wait.forPageToLoad(driver);
        return this;
    }




}
