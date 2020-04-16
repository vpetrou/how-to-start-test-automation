package com.intrasoft.stsc.test_utils;

import org.openqa.selenium.WebDriver;
import com.intrasoft.stsc.web_pages.common.Menu;
import com.intrasoft.stsc.web_pages.contact.CreateContact;
import com.intrasoft.stsc.web_pages.contact.ListOfContacts;
import com.intrasoft.stsc.web_pages.contact.UpdateContact;
import com.intrasoft.stsc.web_pages.contact.ViewContact;
import com.intrasoft.stsc.web_pages.home.Home;
import com.intrasoft.stsc.web_pages.home.Login;
import com.intrasoft.stsc.web_pages.home.Register;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class Page {

    WebDriver driver;

    private Menu menu;
    private Register registerPage;
    private Login loginPage;
    private Home homePage;
    private CreateContact createContactPage;
    private UpdateContact updateContactPage;
    private ViewContact viewContactPage;
    private ListOfContacts listOfContactsPage;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public Menu menu() {
        menu = new Menu(driver);
        return menu;
    }

    public Register registerPage() {
        registerPage = new Register(driver);
        return registerPage;
    }

    public Login loginPage() {
        loginPage = new Login(driver);
        return loginPage;
    }

    public Home homePage() {
        homePage = new Home(driver);
        return homePage;
    }

    public CreateContact createContactPage() {
        createContactPage = new CreateContact(driver);
        return createContactPage;
    }

    public UpdateContact updateContactPage() {
        updateContactPage = new UpdateContact(driver);
        return updateContactPage;
    }

    public ViewContact viewContactPage() {
        viewContactPage = new ViewContact(driver);
        return viewContactPage;
    }

    public ListOfContacts listOfContactsPage() {
        listOfContactsPage = new ListOfContacts(driver);
        return listOfContactsPage;
    }

}
