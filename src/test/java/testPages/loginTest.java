package testPages;

import framework.webpages.HotelsPage;
//Following two are the libraries of log4j
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//////////////////////////////////////
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.BasePage;

import java.io.IOException;

public class loginTest {

    private static Logger logger = LogManager.getLogger(loginTest.class.getName());

    WebDriver driver = null;
    HotelsPage hotelsPage;
    BasePage basePage;

    @BeforeMethod
    public void setUp() throws IOException {
        basePage = new BasePage();
        driver = basePage.initDriver();
        logger.info("WebDriver is loaded now!");
        hotelsPage = new HotelsPage(driver);
    }

    @Test(priority = 1)
    public void verifyUrl() {
        String getUrl = driver.getCurrentUrl();
        Assert.assertEquals(getUrl, "https://www.hotels.com/");
        logger.info("The web site is validated : ", getUrl);
    }

    @Test(enabled = false)
    public void customerSelect() throws InterruptedException {
        hotelsPage.customerSelections();
    }
    @Test
    public void star3Select() throws InterruptedException {
        hotelsPage.customerSelections();
        hotelsPage.selectHotelsStar("7");
        for (String s: hotelsPage.printResult()) {
            System.out.println(s);
        }
    }
    @Test(enabled = true)
    public void star4Select() throws InterruptedException {
        hotelsPage.customerSelections();
        hotelsPage.selectHotelsStar("4");
        for (String s: hotelsPage.printResult()) {
            System.out.println(s);
        }
    }

    @Test(enabled = true)
    public void star5Select() throws InterruptedException {
        hotelsPage.customerSelections();
        hotelsPage.selectHotelsStar("5");
        for (String s: hotelsPage.printResult()) {
            System.out.println(s);
        }
    }
    @AfterMethod
    public void tearDown () {
        driver.quit();
        driver = null;
    }
}
