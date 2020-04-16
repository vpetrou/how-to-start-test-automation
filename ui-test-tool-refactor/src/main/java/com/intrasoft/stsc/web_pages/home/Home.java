package com.intrasoft.stsc.web_pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.intrasoft.stsc.test_utils.BaseWebPage;

import static org.testng.Assert.assertTrue;

/**
 * Created by vpetrou on 1/3/2018.
 */
public class Home extends BaseWebPage{

    protected WebDriver driver;

    public Home(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Home verifyPageOpens() {
        // verify home page
        assertTrue(isElementPresent(By.xpath("//h1[@class='home']")));
        // verify welcome message
        assertTrue(isElementPresent(By.xpath("//*[contains(.,'Welcome to UI Test Application')]")));
        return this;
    }

    public Home verifyLoggedUser(String userEmail) {
        // verify logged user
        assertTrue(isElementPresent(By.xpath("//*[contains(.,'" + userEmail + "')]")));
        return this;
    }


}
