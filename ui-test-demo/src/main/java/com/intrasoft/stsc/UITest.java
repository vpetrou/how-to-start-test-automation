package com.intrasoft.stsc;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class UITest {

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

        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("password")).clear();

        driver.findElement(By.name("email")).sendKeys("vasilios.petrou@intrasoft-intl.com");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        Thread.sleep(1000);

        Assert.assertEquals("Welcome to UI Test Application", driver.findElement(By.xpath("//h3")).getText());

        driver.quit();
    }

}
