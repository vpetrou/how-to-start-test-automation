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
public class CreateContact extends BaseWebPage {

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveBtn;

    protected WebDriver driver;

    public CreateContact(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CreateContact create(String nameValue, String addressValue, String cityValue, String phoneValue, String emailValue) {
        Contact contact = new Contact(driver);
        contact.name.sendKeys(nameValue);
        contact.address.sendKeys(addressValue);
        contact.city.sendKeys(cityValue);
        contact.phone.sendKeys(phoneValue);
        contact.email.sendKeys(emailValue);
        clickSave();
        return this;
    }

    public CreateContact clickSave() {
        saveBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

    public CreateContact verifyPageOpens() {
        // verify Create new Contact page
        assertTrue(isElementPresent(By.xpath("//h3[contains(.,'Add New Contact')]")));
        return this;
    }


}
