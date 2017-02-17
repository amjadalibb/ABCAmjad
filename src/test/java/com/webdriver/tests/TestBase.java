package com.webdriver.tests;

// *********************************************************

// *********************************************************

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class TestBase {

    public WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        //String siteUrl = "http://www.abc.net.au/";
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1150, 650));
        //driver.navigate().to(siteUrl);
    }

    @After
    public void tearDownTest() {
        driver.quit();
    }

}
