package framework.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.ElementUtilTestNG;

import java.util.ArrayList;
import java.util.List;

public class HotelsPage {

    ElementUtilTestNG elUtil;
    public static String verifyHotel;
    WebDriver driver;

    public HotelsPage(WebDriver driver) {
        this.driver = driver;
        this.elUtil = new ElementUtilTestNG(driver);
    }

    private By destination = By.id("qf-0q-destination");
    private By checkInArea = By.cssSelector("input[id='qf-0q-localised-check-in']");
    private By checkInDate = By.cssSelector("td[data-date='2020-1-18']");
    private By checkOutArea = By.cssSelector("input[id='qf-0q-localised-check-out']");
    private By checkOutDate = By.cssSelector("td[data-date='2020-1-19']");
    private By childArea = By.id("qf-0q-room-0-children");
    private By firstChildAge = By.id("qf-0q-room-0-child-0-age");
    private By secondChildAge = By.id("qf-0q-room-0-child-1-age");
    private By searchBtn = By.cssSelector("button.cta.cta-strong");
    private By closePopUp = By.cssSelector("a.footer-continue-link.di-ib.fw-bold");
    private By firstDropDownCity = By.cssSelector("#citysqm-asi0-s0");
    private By threeStars = By.cssSelector("#f-star-rating-3");
    private By fourStars = By.cssSelector("#f-star-rating-4");
    private By fiveStars = By.cssSelector("#f-star-rating-7");
    private By hotelTitles = By.cssSelector("a.property-name-link");
    private By distance=By.cssSelector("ul.property-landmarks li:nth-child(1)");


    public void customerSelections() throws InterruptedException {
        //clickOn(closePopUp);
        elUtil.doSendKeys(destination,"New York, New York, United States of America");
        Thread.sleep(2000);
        elUtil.doClick(firstDropDownCity);
        Thread.sleep(1000);
        elUtil.doClick(checkInArea);
        Thread.sleep(1000);
        elUtil.doClick(checkInDate);
        elUtil.doClick(checkOutArea);
        elUtil.doClick(checkOutDate);
        Thread.sleep(1000);
/*      doClick(childArea);
        elementUtilTestNG.selectFromDropdown(childArea,"2");
        elementUtilTestNG.selectFromDropdown(firstChildAge,"9");
        elementUtilTestNG.selectFromDropdown(secondChildAge,"6");*/
        //Thread.sleep(1000);
        elUtil.doClick(searchBtn);
        Thread.sleep(1000);

    }
    public void selectHotelsStar(String star) throws InterruptedException {
        if (star.contains("3")) {
            verifyHotel = "3starred";
            //scrollBy(driver);
            elUtil.doClick(threeStars);
            Thread.sleep(1000);
            //JavaScriptUtil.scrollDownIterator(driver);
        }
        else if (star.contains("4")) {
            verifyHotel = "4starred";
            //scrollBy(driver);
            elUtil.doClick(fourStars);
            Thread.sleep(1000);
            //JavaScriptUtil.scrollDownIterator(driver);
        }

        else if (star.contains("7")) {
            verifyHotel = "7starred";
            //scrollBy(driver);
            elUtil.doClick(fiveStars);
            Thread.sleep(1000);
            //JavaScriptUtil.scrollDownIterator(driver);
        }
    }

    public List<String> printResult() throws InterruptedException {
        List<String> listHotels =  getTitle(hotelTitles,distance);
        return listHotels;
    }

    public List<String> getTitle(By locator1, By locator2) throws InterruptedException {
        List<WebElement> hotelTitleText = driver.findElements(locator1);
        List<WebElement> distanceText = driver.findElements(locator2);
        List<String> mileHotelName = new ArrayList<String>();
        int j = 1;
        for (int i = 0; i < distanceText.size(); i++) {
            String fullDistance = distanceText.get(i).getText();
            String hotelTitle = hotelTitleText.get(i).getText();
            String distance = fullDistance.split(" ")[0].trim();
            double dist = Double.parseDouble(distance);
            if (dist < 10 ) {
                mileHotelName.add(j + ". " + dist + " Miles " + hotelTitle);
                j++;
            }
        }
        return mileHotelName;
    }
}
