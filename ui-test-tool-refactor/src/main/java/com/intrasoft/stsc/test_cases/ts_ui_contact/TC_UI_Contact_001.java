package com.intrasoft.stsc.test_cases.ts_ui_contact;

import org.testng.annotations.Test;
import com.intrasoft.stsc.test_utils.BaseTestCase;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class TC_UI_Contact_001 extends BaseTestCase {

    @Test
    public void TC_UI_Contact_001() {
        page.loginPage()
                .clickRegister();
        page.registerPage()
                .verifyPageOpens()
                .register("vasilios.petrou@intrasoft-intl.com", "1234", "Bill", "Petrou");
        page.menu()
                .verifyMenuContentWhenNotLogin();
        page.loginPage()
                .login("vasilios.petrou@intrasoft-intl.com", "1234");
        page.menu()
                .goToCreateNewContact();
        page.createContactPage()
                .verifyPageOpens()
                .create("Bill", "Makedonias 120", "Thes/niki", "2310123456", "testflockorg@gmail.com");
        page.listOfContactsPage()
                .verifyPageOpens()
                .verifyTableContains("Bill");
        page.menu()
                .logout();
    }

}
