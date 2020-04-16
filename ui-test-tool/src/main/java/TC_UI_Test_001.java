import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TC_UI_Test_001 {

    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        //OPEN BROWSER AND APPLICATION URL
        // set the location of chrome driver file (resources)
        String appOS = "win";

        if (appOS.equalsIgnoreCase("mac")) {
            System.setProperty("webdriver.chrome.driver", "/Users/vpetrou/apps/test-automation/ui-test-tool/src/main/resources/chromedriver_mac");
        } else {
            System.setProperty("webdriver.chrome.driver", "C:/Users/vpetrou/Desktop/test-automation/ui-test-tool/src/main/resources/chromedriver.exe");
        }

        // create a new driver (chrome driver)
        driver = new ChromeDriver();

//        //only for mac
        if (appOS.equalsIgnoreCase("mac")) {
            focusBrowser(driver);
        }

        // open web application
        driver.get("http://localhost:7101");
        //maximize window
        driver.manage().window().maximize();
        // wait for page to load (because sometime we put the correct element locators but unexpectedly script fails.)
        waitForLoad(driver);
    }

    @Test
    public void test() throws InterruptedException {
        // TC01.Register new user
        // click Register
        driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
        // verify Registration Page
        assertTrue(isElementPresent(By.xpath("//h2[contains(.,'Register')]")));
        // insert values in registration page
        driver.findElement(By.name("email")).sendKeys("vasilios.petrou@intrasoft-intl.com");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.name("firstname")).sendKeys("Bill");
        driver.findElement(By.name("lastname")).sendKeys("Petrou");
        // click register
        driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
        // wait for page to load
        waitForLoad(driver);

        // TC02. Verify Login Page Content
        // verify that menu contains a logo link that redirects to the login page
        assertTrue(isElementPresent(By.xpath("//a[contains(.,'UI Test App') and contains(@class,'navbar-brand')]")));
        // click on Logo Link to redirect to the same page (login page)
        driver.findElement(By.xpath("//a[contains(.,'UI Test App') and contains(@class,'navbar-brand')]")).click();
        // verify login page
        assertTrue(isElementPresent(By.xpath("//h2[contains(.,'Login')]")));

        // TC03. Invalid Login (negative)
        // find an element with xpath (a button that contains text "Login") and click it.
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        // verify that a marker is displayed under Email label with appropriate warning
        assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::div[contains(@class,'help-block')][1]")).getText().equals("Email is required"));
        // or xpath (using for) //label[@*[name()='for']='email']/following::div[contains(@class,'help-block')][1]
        // verify that a marker is displayed under Password label with appropriate warning
        assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Password')]/following::div[contains(@class,'help-block')][1]")).getText().equals("Password is required"));

        // TC04. Valid Login
        // find an element with name email and insert appropriate value
        driver.findElement(By.name("email")).sendKeys("vasilios.petrou@intrasoft-intl.com");
        // find an element with name password and insert appropriate value
        driver.findElement(By.name("password")).sendKeys("1234");
        // find an element with xpath (a button that contains text "Login") and click it.
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        // wait for page to load (because sometime we put the correct element locators but unexpectedly script fails.)
        waitForLoad(driver);
        // verify home page
        assertTrue(isElementPresent(By.xpath("//h1[@class='home']")));
        // verify welcome message
        assertTrue(isElementPresent(By.xpath("//*[contains(.,'Welcome to UI Test Application')]")));
        // verify logged user
        assertTrue(isElementPresent(By.xpath("//*[contains(.,'vasilios.petrou@intrasoft-intl.com')]")));

        // TC05. Verify Home Page Content
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

        // TC06. Create new Contact
        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@class='dropdown-toggle' and contains(.,'Contacts')]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@class='dropdown-toggle' and contains(.,'Contacts')]")).click();
            driver.findElement(By.xpath("//*[@class='dropdown-menu']//a[contains(.,'Create new Contact')]")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // wait for page to load
        waitForLoad(driver);
        // verify Create new Contact page
        assertTrue(isElementPresent(By.xpath("//h3[contains(.,'Add New Contact')]")));
        // insert value in all fields
        driver.findElement(By.name("name")).sendKeys("Bill");
        driver.findElement(By.name("address")).sendKeys("Makedonias 120");
        driver.findElement(By.name("city")).sendKeys("Thes/niki");
        driver.findElement(By.name("phone")).sendKeys("2310123456");
        driver.findElement(By.name("email")).sendKeys("testflockorg@gmail.com");
        // click on a button "Save"
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        // wait for page to load
        waitForLoad(driver);
        // verify auto-redirect to "List of Contacts" page
        assertTrue(isElementPresent(By.xpath("//h3[contains(.,'Contact List')]")));
        // verify new contact
        assertTrue(isElementPresent(By.xpath("//table[contains(.,'Bill')]")));

        // TC07. Show Contact Details
        // click on link DETAILS of line with text "Bill"
        driver.findElement(By.xpath("//table[contains(.,'Bill')]//a[contains(.,'Details')][1]")).click();
        // verify View Mode
        assertFalse(isElementPresent(By.xpath("//*[@class='list']//*[contains(@class,'form-control')]")));

        // TC08. Edit Contact
        waitForLoad(driver);
        // click on EDIT button
        driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();
        // wait for page to load
        waitForLoad(driver);
        // verify Edit Mode
        assertTrue(isElementPresent(By.xpath("//div[@class='row']//*[contains(@class,'form-control')]")));
        // update some values (clear and sendkeys)
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Thessaloniki");
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys("Makedonias 150");
        // click on button "Update"
        driver.findElement(By.xpath("//button[contains(text(),'Update')]")).click();
        // wait for page to load
        waitForLoad(driver);
        // verify that after update it redirects to "view page"
        assertFalse(isElementPresent(By.xpath("//*[@class='list']//*[contains(@class,'form-control')]")));
        // verify the modification
        assertTrue(isElementPresent(By.xpath("//*[@class='list']//*[contains(.,'Thessaloniki')]")));
        assertTrue(isElementPresent(By.xpath("//*[@class='list']//*[contains(.,'Makedonias 150')]")));
        // click on Back button
        driver.findElement(By.xpath("//a[contains(.,'Back')]")).click();
        // wait for page to load
        waitForLoad(driver);
        // verify the updated value of the element that is displayed in list of contacts
        assertTrue(isElementPresent(By.xpath("//table[contains(.,'Thessaloniki')]")));

        // TC09. Delete Contact
        // click on link DETAILS of line with text "Bill"
        driver.findElement(By.xpath("//table[contains(.,'Bill')]//a[contains(.,'Details')][1]")).click();
        // wait for page to load
        waitForLoad(driver);
        // click on button "Delete"
        driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
        // switch the driver to "concentrate" on alert window and click yes to "accept" the deletion
        driver.switchTo().alert().accept();
        // switch to the basic window
        driver.switchTo().defaultContent();
        // verify the deletion
        assertFalse(isElementPresent(By.xpath("//table[contains(.,'Bill')]")));

        // TC10 Verify Logout
        // click on bu  tton "Logout"
        driver.findElement(By.id("logoutLink")).click();
        // verify login page
        assertTrue(isElementPresent(By.xpath("//h2[contains(.,'Login')]")));
        // verify menu content. links "contacts" and "logout" must become invisible
//            assertFalse(isElementPresent(By.xpath("//a[@class='dropdown-toggle' and contains(.,'Contacts')]")));
//            assertFalse(isElementPresent(By.id("logoutLink")));

        //Unregister
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(500);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public Boolean isElementPresent(By by) {
        if (driver.findElements(by).size() > 0)
            return true;
        else
            return false;
    }

    private void focusBrowser(WebDriver driver) {
        String currentWindowHandle;
        currentWindowHandle = driver.getWindowHandle();

        ((JavascriptExecutor)driver).executeScript("alert('testFlock.org:7101')");
        driver.switchTo().alert().accept();

        driver.switchTo().window(currentWindowHandle);

    }
}
