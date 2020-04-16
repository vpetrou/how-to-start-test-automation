package com.intrasoft.stsc.web_pages.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.intrasoft.stsc.test_utils.BaseWebPage;

/**
 * Created by vpetrou on 1/3/2018.
 */
public class Contact extends BaseWebPage{

    @FindBy(name = "name")
    static WebElement name;
    @FindBy(name = "address")
    static WebElement address;
    @FindBy(name = "city")
    static WebElement city;
    @FindBy(name = "phone")
    static WebElement phone;
    @FindBy(name = "email")
    static WebElement email;

    public Contact(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
