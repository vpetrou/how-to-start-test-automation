package com.intrasoft.stsc.web_pages.common;

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
public class Menu extends BaseWebPage {

    @FindBy(xpath="//a[@class='dropdown-toggle' and contains(.,'Contacts')]")
    WebElement contactsLink;
    @FindBy(xpath="//*[@class='dropdown-menu']//a[contains(.,'Create new Contact')]")
    WebElement createNewContactLink;
    @FindBy(xpath="//*[@class='dropdown-menu']//a[contains(.,'List of Contacts')]")
    WebElement listOfContactsLink;

    @FindBy(id="logoutLink")
    WebElement logoutBtn;

    WebDriver driver;

    public Menu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Menu verifyMenuContentWhenLogin() {
        // verify that menu contains a logo link that redirects to the home page
        assertTrue(isElementPresent(By.xpath("//a[contains(.,'UI Test App') and contains(@class,'navbar-brand')]")));
        // click on Logo Link to redirect to the same page (login page)
        driver.findElement(By.xpath("//a[contains(.,'UI Test App') and contains(@class,'navbar-brand')]")).click();
        // verify home page
        assertTrue(isElementPresent(By.xpath("//h1[@class='home']")));
        // verify menu content. contact dropdown link with 2 options (List of Contacts, Create New Contact)
        assertTrue(isElementPresent(By.xpath("//a[@class='dropdown-toggle' and contains(.,'Contacts')]")));
        driver.findElement(By.xpath("//a[@class='dropdown-toggle' and contains(.,'Contacts')]")).click();
        assertTrue(isElementPresent(By.xpath("//*[@class='dropdown-menu' and contains(.,'List of Contacts')]")));
        assertTrue(isElementPresent(By.xpath("//*[@class='dropdown-menu' and contains(.,'Create new Contact')]")));
        // verify menu content. Logout link
        assertTrue(isElementPresent(By.id("logoutLink")));
        return this;
    }

    public Menu verifyMenuContentWhenNotLogin() {
        // verify that menu contains a logo link that redirects to the login page
        assertTrue(isElementPresent(By.xpath("//a[contains(.,'UI Test App') and contains(@class,'navbar-brand')]")));
        // click on Logo Link to redirect to the same page (login page)
        driver.findElement(By.xpath("//a[contains(.,'UI Test App') and contains(@class,'navbar-brand')]")).click();
        Wait.forPageToLoad(driver);
        // verify login page
        assertTrue(isElementPresent(By.xpath("//h2[contains(.,'Login')]")));
        // verify menu content. links "contacts" and "logout" must become invisible
//            assertFalse(isElementPresent(By.xpath("//a[@class='dropdown-toggle' and contains(.,'Contacts')]")));
//            assertFalse(isElementPresent(By.id("logoutLink")));
        return this;
    }

    public Menu goToCreateNewContact(){
        try {
            Thread.sleep(3000);
            contactsLink.click();
            Thread.sleep(3000);
            //contactsLink.click();
            createNewContactLink.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // wait for page to load
        Wait.forPageToLoad(driver);
        return this;
    }

    public Menu goToListOfContacts() {
        try {
            Thread.sleep(1000);
            contactsLink.click();
            Thread.sleep(1000);
            listOfContactsLink.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // wait for page to load
        Wait.forPageToLoad(driver);
        return this;
    }

    public Menu logout() {
        logoutBtn.click();
        Wait.forPageToLoad(driver);
        return this;
    }

}
