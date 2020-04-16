package com.intrasoft.stsc.test_cases.ts_ui_n_login;

import org.testng.annotations.Test;
import com.intrasoft.stsc.test_utils.BaseTestCase;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class TC_UI_N_Login_001 extends BaseTestCase {

    @Test
    public void TC_UI_N_Login_001() {
        page.loginPage()
                .clickLogin()
                .verifyInvalidLogin();
    }

}
