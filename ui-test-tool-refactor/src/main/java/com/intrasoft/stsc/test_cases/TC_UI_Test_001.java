package com.intrasoft.stsc.test_cases;

import org.testng.annotations.Test;
import com.intrasoft.stsc.test_utils.BaseTestCase;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class TC_UI_Test_001 extends BaseTestCase {

    @Test
    public void TC_UI_Test_001() {
        page.loginPage()
                .clickRegister();
        page.registerPage()
                .verifyPageOpens()
                .register("vasilios.petrou@intrasoft-intl.com", "1234", "Bill", "Petrou");
        page.menu()
                .verifyMenuContentWhenNotLogin();
        page.loginPage()
                .clickLogin()
                .verifyInvalidLogin()
                .login("vasilios.petrou@intrasoft-intl.com", "1234");
        page.homePage()
                .verifyPageOpens()
                .verifyLoggedUser("vasilios.petrou@intrasoft-intl.com");
        page.menu()
                .verifyMenuContentWhenLogin();
        page.menu()
                .goToCreateNewContact();
        page.createContactPage()
                .verifyPageOpens()
                .create("Bill", "Makedonias 120", "Thes/niki", "2310123456", "testflockorg@gmail.com");
        page.listOfContactsPage()
                .verifyPageOpens()
                .verifyTableContains("Bill")
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
                .verifyTableContains("Thessaloniki")
                .clickDetailsOf("Bill");
        page.viewContactPage()
                .clickDelete();
        page.listOfContactsPage()
                .verifyTableNotContains("Bill");
        page.menu()
                .logout();
//                .verifyMenuContentWhenNotLogin();
//        page.loginPage()
//                .verifyPageOpens();
    }

}
