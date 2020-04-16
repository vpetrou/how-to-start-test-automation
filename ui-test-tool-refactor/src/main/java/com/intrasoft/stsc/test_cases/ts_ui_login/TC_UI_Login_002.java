package com.intrasoft.stsc.test_cases.ts_ui_login;

import org.testng.annotations.Test;
import com.intrasoft.stsc.test_utils.BaseTestCase;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class TC_UI_Login_002 extends BaseTestCase {

    @Test
    public void TC_UI_Login_002() {
        page.loginPage()
                .login("vasilios.petrou@intrasoft-intl.com", "1234");
        page.homePage()
                .verifyPageOpens()
                .verifyLoggedUser("vasilios.petrou@intrasoft-intl.com");
        page.menu()
                .verifyMenuContentWhenLogin();
        page.menu()
                .logout()
                .verifyMenuContentWhenNotLogin();
        page.loginPage()
                .verifyPageOpens();
    }
}
