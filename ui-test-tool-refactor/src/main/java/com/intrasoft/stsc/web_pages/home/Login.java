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
public class Login extends BaseWebPage {

    @FindBy(name="email")
    WebElement email;
    @FindBy(name="password")
    WebElement password;
    @FindBy(xpath="//button[contains(text(),'Login')]")
    WebElement loginBtn;
    @FindBy(xpath="//a[contains(text(),'Register')]")
    WebElement registerBtn;

    @FindBy(xpath="//label[contains(text(),'Email')]/following::div[contains(@class,'help-block')][1]")
    WebElement emailErrorMessage;
    @FindBy(xpath="//label[contains(text(),'Password')]/following::div[contains(@class,'help-block')][1]")
    WebElement passwordErrorMessage;

    protected WebDriver driver;

    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Login login(String emailValue, String passwordValue) {
        email.clear();
        email.sendKeys(emailValue);
        password.clear();
        password.sendKeys(passwordValue);
        clickLogin();
        Wait.forPageToLoad(driver);
        return this;
    }

    public Login clickLogin() {
        loginBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public Login clickRegister() {
        registerBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public Login verifyPageOpens() {
        // verify login page
        assertTrue(isElementPresent(By.xpath("//h2[contains(.,'Login')]")));
        return this;
    }

    public Login verifyInvalidLogin(){
        // verify that a marker is displayed under Email label with appropriate warning
        assertTrue(emailErrorMessage.getText().equals("Email is required"));
        // verify that a marker is displayed under Password label with appropriate warning
        assertTrue(passwordErrorMessage.getText().equals("Password is required"));
        return this;
    }



}
