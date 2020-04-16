package com.intrasoft.stsc.test_utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class BaseWebPage {

    WebDriver driver;

    public BaseWebPage(WebDriver driver) {
        this.driver = driver;
    }

    protected Boolean isElementPresent(By by) {
        if (driver.findElements(by).size() > 0)
            return true;
        else
            return false;
    }

}
