package com.intrasoft.stsc.web_pages.contact;

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
public class UpdateContact extends BaseWebPage{

    Contact contact;

    @FindBy(xpath = "//button[contains(text(),'Update')]")
    WebElement updateBtn;

    protected WebDriver driver;

    public UpdateContact(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        contact = new Contact(driver);
    }

    public UpdateContact updateName(String nameValue) {
        Contact.name.clear();
        Contact.name.sendKeys(nameValue);
        return this;
    }

    public UpdateContact updateAddress(String addressValue) {
        Contact.address.clear();
        Contact.address.sendKeys(addressValue);
        return this;
    }

    public UpdateContact updateCity(String cityValue) {
        Contact.city.clear();
        Contact.city.sendKeys(cityValue);
        return this;
    }

    public UpdateContact updatePhone(String phoneValue) {
        Contact.phone.clear();
        Contact.phone.sendKeys(phoneValue);
        return this;
    }

    public UpdateContact updateEmail(String emailValue) {
        Contact.email.clear();
        Contact.email.sendKeys(emailValue);
        return this;
    }

    public UpdateContact clickUpdate() {
        updateBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public UpdateContact verifyPageOpens() {
        // verify Edit Mode
        assertTrue(isElementPresent(By.xpath("//div[@class='row']//*[contains(@class,'form-control')]")));
        return this;
    }

}
