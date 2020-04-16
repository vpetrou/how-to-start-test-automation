package com.intrasoft.stsc.test_cases.ts_ui_contact;

import org.testng.annotations.Test;
import com.intrasoft.stsc.test_utils.BaseTestCase;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class TC_UI_Contact_002 extends BaseTestCase {

    @Test
    public void TC_UI_Contact_002() {
        page.loginPage()
                .login("vasilios.petrou@intrasoft-intl.com", "1234");
        page.homePage()
                .verifyPageOpens()
                .verifyLoggedUser("vasilios.petrou@intrasoft-intl.com");
        page.menu()
                .goToListOfContacts();
        page.listOfContactsPage()
                .clickDetailsOf("Bill");
        page.viewContactPage()
                .verifyViewMode();
        page.menu()
                .logout();
    }

}
