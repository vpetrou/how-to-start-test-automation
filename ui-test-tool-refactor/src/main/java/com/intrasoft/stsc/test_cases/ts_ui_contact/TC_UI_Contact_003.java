package com.intrasoft.stsc.test_cases.ts_ui_contact;

import org.testng.annotations.Test;
import com.intrasoft.stsc.test_utils.BaseTestCase;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class TC_UI_Contact_003 extends BaseTestCase {

    @Test
    public void TC_UI_Contact_003() {
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
                .verifyViewMode()
                .clickEdit();
        page.updateContactPage()
                .verifyPageOpens()
                .updateCity("Thessaloniki")
                .updateAddress("Makedonias 150")
                .clickUpdate();
        page.viewContactPage()
                .verifyViewMode()
                .verifyViewListContains("Thessaloniki")
                .verifyViewListContains("Makedonias 150")
                .clickBack();
        page.listOfContactsPage()
                .verifyTableContains("Thessaloniki");
        page.menu()
                .logout();
    }

}
