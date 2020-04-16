package com.intrasoft.stsc.web_pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.intrasoft.stsc.test_utils.BaseWebPage;
import com.intrasoft.stsc.test_utils.common.Wait;

import static org.testng.Assert.assertTrue;

/**
 * Created by vpetrou on 1/3/2018.
 */
public class Register extends BaseWebPage{

    @FindBy(name="email")
    WebElement email;
    @FindBy(name="password")
    WebElement password;
    @FindBy(name="firstname")
    WebElement firstname;
    @FindBy(name="lastname")
    WebElement lastname;
    @FindBy(xpath="//*[contains(text(),'Register')]")
    WebElement registerBtn;
    @FindBy(xpath="//a[contains(text(),'Cancel')]")
    WebElement cancelBtn;

    WebDriver driver;

    public Register(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Register register(String emailValue, String passwordValue, String firstnameValue, String lastnameValue) {
        email.clear();
        email.sendKeys(emailValue);
        password.clear();
        password.sendKeys(passwordValue);
        firstname.clear();
        firstname.sendKeys(firstnameValue);
        lastname.clear();
        lastname.sendKeys(lastnameValue);
        clickRegister();
        return this;
    }

    public Register clickRegister() {
        registerBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public Register clickCancel() {
        cancelBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public Register verifyPageOpens() {
        // verify Registration Page
        assertTrue(isElementPresent(By.xpath("//h2[contains(.,'Register')]")));
        return this;
    }

}
