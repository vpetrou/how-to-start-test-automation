package com.intrasoft.stsc.test_utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.intrasoft.stsc.test_utils.common.Property;
import com.intrasoft.stsc.test_utils.common.Wait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.intrasoft.stsc.test_utils.common.Property.getApplicationPath;

/**
 * Created by vpetrou on 1/2/2019.
 */
public class BaseTestCase {

    public WebDriver driver;
    public Page page;

    public static final Map<String, String> TC_Desc;

    static {
        final Map<String, String> tc_desc = new HashMap<>();
        tc_desc.put("TC_UI_Login_001", "Login with Correct Credentials");
        tc_desc.put("TC_UI_Login_002", "Logout after correct login");
        tc_desc.put("TC_UI_N_Login_001", "Login with Null Credentials");
        tc_desc.put("TC_UI_Contact_001", "Add a new contact");
        tc_desc.put("TC_UI_Contact_002", "View a contact");
        tc_desc.put("TC_UI_Contact_003", "Edit contact");
        tc_desc.put("TC_UI_Contact_004", "Delete a contact");
        tc_desc.put("TC_UI_Test_001", "Full Test");
        TC_Desc = Collections.unmodifiableMap(tc_desc);
    }

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    @BeforeTest
    public void beforeTest() {
        //OPEN BROWSER AND APPLICATION URL
        System.out.println("browser:" + Property.getBrowser());
        if (Property.getBrowser().equalsIgnoreCase("chrome")) {

            //get file according to OS
            String webdriverFileName = "";
            if (Property.getApplicationOS().equalsIgnoreCase("mac")) {
                webdriverFileName = "chromedriver_mac";
            } else {
                webdriverFileName = "chromedriver.exe";
            }

            // get driverPath. path is different when running from IntelliJ and different when running from deployed package
            String driverPath = getApplicationPath() + "/src/main/resources/drivers/" + webdriverFileName;
            File f = new File(driverPath);
            if (!f.exists()) {
                driverPath = getApplicationPath() + "/drivers/" + webdriverFileName;
            }

            // set the location of chrome driver file (resources)
            System.setProperty("webdriver.chrome.driver", driverPath);
            // create a new driver (chrome driver)
            driver = new ChromeDriver();
        }
        //only for mac that supports many Desktops
        if (Property.getApplicationOS().equalsIgnoreCase("mac")) {
            focusBrowser(driver);
        }
        // open web application
        driver.get(Property.getURL());
        //maximize window
        driver.manage().window().maximize();

        page = new Page(driver);

        // wait for page to load (because sometime we put the correct element locators but unexpectedly script fails.)
        Wait.forPageToLoad(driver);
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void focusBrowser(WebDriver driver) {
        String currentWindowHandle;
        currentWindowHandle = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript("alert('testFlock.org:7101')");
        driver.switchTo().alert().accept();

        driver.switchTo().window(currentWindowHandle);

    }

    protected String getTestCaseId() {
        return this.getClass().getSimpleName();
    }

    @AfterMethod(alwaysRun = true)
    public void catchExceptions(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String className = this.getClass().getSimpleName();
        if (result.isSuccess()) {
            Reporter.log("<p>&nbsp;</p>");
            Reporter.log("<p><font size=\"2\" face=\"Arial\">&nbsp;Test Case: </font><font size=\"2\" face=\"Arial\" color=\"green\"><b>"+TC_Desc.get(className)+"</b></font></p>");
        } else {
            Reporter.log("<p>&nbsp;</p>");
            Reporter.log("<p><font size=\"2\" face=\"Arial\">&nbsp;Test Case: </font><font size=\"2\" face=\"Arial\" color=\"red\"><b>"+TC_Desc.get(className)+"</b></font></p>");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String failureScreenshotsPath = getApplicationPath() + "/reports/failure_screenshots/";
                Reporter.log("failureScreenshotsPath: " + failureScreenshotsPath);
                failureScreenshotsPath.replace('\\', '/');
                String failureImageFileName = className + "_" + formater.format(calendar.getTime()) + ".png";
                FileUtils.copyFile(scrFile, new File((failureScreenshotsPath + failureImageFileName)));
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String relativeScreenshotPath = "../../failure_screenshots/";
                Reporter.log("<p align=\"left\"><font size=\"2\" face=\"Arial\" color=\"blue\"><b>&nbsp;<a href=\"" + relativeScreenshotPath + failureImageFileName + "\">Error screenshot at " + new Date() + "</b></font></p>");
                Reporter.log("<p>&nbsp;<img width=\"300\" src=\"" + relativeScreenshotPath + failureImageFileName + "\" alt=\"screenshot at " + new Date() + "\"/></p></a><br />");
                Reporter.log("<p align=\"left\"><font size=\"2\" face=\"Arial\" color=\"red\">" + getStuckTraceMessage(result) + "</b></font></p>");
                Reporter.log("<p>&nbsp;</p>");
                Reporter.log("<p align=\"left\"><font size=\"2\" face=\"Arial\" color=\"blue\">Full Stacktrace:</font></p>");
                Reporter.log("<p align=\"left\"><font size=\"2\" face=\"Arial\">" + getStuckTrace(result) + "</font></p>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String getStuckTrace(ITestResult result) {
        return ExceptionUtils.getStackTrace(result.getThrowable());
    }

    private String getStuckTraceMessage(ITestResult result) {
        return result.getThrowable().getMessage();
    }

    private String getAbsolutePath() {
        return System.getProperty("user.dir");
    }
}
