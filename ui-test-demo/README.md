# ui-test-demo
How to start with Test Automation

Steps:

1. Download Content from Github https://github.com/billpetrou/ui-test-app

2. Download and Install Docker from https://www.docker.com/get-started

3. Download and Install JDK 1.8 from https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

4. Start ui-test-app (playground application) as described in Readme.md 

5. Download and Install IntelliJ IDEA CE (Community Edition) from https://www.jetbrains.com/idea/download

6. Create a folder (e.g. ui-test-demo) 

7. Open IntelliJ IDEA CE and select option "Create New Project"

8. Select Maven and choose Project SDK 1.8 and click "Next"

9. Insert a GroupId (e.g. com.intrasoft.stsc), an ArtifactId (e.g. ui-test-demo) and a Version (e.g. 1.0.0) and click "Next"

10. Insert a project name (e.g. ui-test-demo) and select a folder where it will be saved (e.g. C:/Users/vpetrou/Desktop/test-automation/ui-test-demo) and click "Finish" 

11. Add in your pom.xml the dependencies for Selenium WebDriver and TestNG test framework. Selenium WebDriver contains all the appropriate commands to communicate with elements that are displayed in a web application via a web browser. TestNG Test Framework contains all the commands and annotations that a class in java relates with a test behavior (output of a test case class is PASS or FAIL or SKIP).

<pre><code>
    &lt;dependencies&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;org.seleniumhq.selenium&lt;/groupId&gt;
            &lt;artifactId&gt;selenium-java&lt;/artifactId&gt;
            &lt;version&gt;3.4.0&lt;/version&gt;
        &lt;/dependency&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;org.testng&lt;/groupId&gt;
            &lt;artifactId&gt;testng&lt;/artifactId&gt;
            &lt;version&gt;6.9.10&lt;/version&gt;
            &lt;scope&gt;compile&lt;/scope&gt;
        &lt;/dependency&gt;
    &lt;/dependencies&gt;
</code></pre>

12. create a new package under src/main/java > com.intrasoft.stsc (the GroupId). We are not creating our test under test package because at the end we will have a standalone project to be executed via command line as a java project. If we put this under test package, we will only be able to execute it via maven execution test phase.

13. Download the latest chrome drivers from http://chromedriver.chromium.org/downloads and put it in resources folder (src/main/resources).

14. Create a class under src/main/java/com/intrasoft/stsc with name UITest

15. Inside this class insert a method with @Test annotation (this annotation is part of TestNG Test Framework)

import org.testng.annotations.Test;

    @Test
    public void TestCase() {

    }

The latest releases of IntelliJ IDEA have bundled the TestNG plugin. If you have an older version, then you have 2 options. To update it with the latest or to download and install the TestNG Plugin. Guidelines here: http://testng.org/doc/idea.html

16. put a temporary code just to see that the TestNG runs properly. 
	
    @Test
    public void TestCase() {
        Assert.assertTrue(10 == 10);
    }

17. put a number of examples to explain what we mean by positive/negative and how is displayed a failed test.

    @Test
    public void TestCasePositive1() {
        Assert.assertTrue(10 == 10);
    }

    @Test
    public void TestCasePositive2() {
        Assert.assertTrue("test".equals("test"));
    }

    @Test
    public void TestCaseNegative1() {
        Assert.assertFalse(20 == 10);
    }

    @Test
    public void TestCaseNegative2() {
        Assert.assertFalse("test1".equals("test2"));
    }

    @Test
    public void TestCaseFailed() {
        Assert.assertTrue(20 == 10);
    }

18. write a simple test to open browser in login page of ui-test-app and verify that it opens. Here we integrate TestNG with Selenium WebDriver

    @Test
    public void TestCasePositive1() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vpetrou\\Desktop\\test-automation\\ui-test-demo\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:7101");

        driver.quit();
    }

The speed is fast so we place a delay just to see that it real opens automatically. 

    @Test
    public void TestCasePositive1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vpetrou\\Desktop\\test-automation\\ui-test-demo\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:7101");

        Thread.sleep(5000);

        driver.quit();
    }

19. Above code is testing nothing. it just visits a web base with chrome and browser closes. So above example must be enriched with a command that verifies something. For example, "verify that login page opens"
So we will use Assert from TestNG and we will try to find an elemnent in the login page that verifies that this page is opened. 

So we open browser, we manually visit webpage and start inspecting elements (click F12) 

we found that there is an h2 element with title "Login" so let's try to locate it using XPATH

    @Test
    public void TestCasePositive1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vpetrou\\Desktop\\test-automation\\ui-test-demo\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:7101");

        Thread.sleep(1000);

        System.out.println("Page Heading: " + driver.findElement(By.xpath("//h2")).getText());
        Assert.assertEquals("Login", driver.findElement(By.xpath("//h2")).getText());

        driver.quit();
    }

There are multiple ways to find an element (by id, name, className, linkName, xpath, cssLocator etc). It is very significant to apply the lesson learnt and best practices in element locating because it is part of a smart implementation to have better maintenance.

20. let's add also the steps to write credentials and click login (incorrect credentials)

    @Test
    public void TestCasePositive1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vpetrou\\Desktop\\test-automation\\ui-test-demo\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:7101");

        Thread.sleep(1000);

        System.out.println("Page Heading: " + driver.findElement(By.xpath("//h2")).getText());
        Assert.assertEquals("Login", driver.findElement(By.xpath("//h2")).getText());

        driver.findElement(By.name("email")).sendKeys("asassasasas");
        driver.findElement(By.name("password")).sendKeys("asassasasas");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        Assert.assertEquals("Login", driver.findElement(By.xpath("//h2")).getText());

        driver.quit();
    }

21. let's enrich script with correct credentials

    @Test
    public void TestCasePositive1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vpetrou\\Desktop\\test-automation\\ui-test-demo\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:7101");

        Thread.sleep(1000);

        System.out.println("Page Heading: " + driver.findElement(By.xpath("//h2")).getText());
        Assert.assertEquals("Login", driver.findElement(By.xpath("//h2")).getText());

        driver.findElement(By.name("email")).sendKeys("asassasasas");
        driver.findElement(By.name("password")).sendKeys("asassasasas");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        Assert.assertEquals("Login", driver.findElement(By.xpath("//h2")).getText());

        driver.findElement(By.name("email")).sendKeys("vasilios.petrou@intrasoft-intl.com");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        Thread.sleep(1000);

        Assert.assertEquals("Welcome to UI Test Application", driver.findElement(By.xpath("//h3")).getText());

        driver.quit();
    }

BUT we have to clean the INPUTS before re-inserting content. So the last version contains also lines: 

        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("password")).clear();

22. To maximize window use command (after creating webdriver)

        driver.manage().window().maximize();