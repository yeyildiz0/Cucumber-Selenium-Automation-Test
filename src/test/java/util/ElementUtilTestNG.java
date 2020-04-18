package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtilTestNG {

    WebDriver driver;
    public ElementUtilTestNG(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By locator){
        waitForElementPresent(locator);
        WebElement element = null;
        element = driver.findElement(locator);
        return element;
    }

    public void waitForElementPresent(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void doClick(By locator){
        try{
            getElement(locator).click();
        }
        catch(Exception e){
            System.out.println("Some exception occured while click on  webelement " +locator);
        }
    }

    public void doSendKeys(By locator, String value){
        try{
            getElement(locator).clear();
            getElement(locator).sendKeys(value);
        }
        catch(Exception e){
            System.out.println("Some exception occured while sending to  webelement " + locator);
        }
    }

    public String doGetText(By locator){
        String text = null;
        try{
            text = getElement(locator).getText();
        }
        catch(Exception e){
            System.out.println("Some exception occured while sending to  webelement " +locator);
        }
        return text;
    }

    public String waitForGetPageTitle(String title){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.titleContains(title));
        return driver.getTitle();
    }

    public boolean isElementDisplayed(By locator){
        try{
            return getElement(locator).isDisplayed();
        }catch(Exception e){
            System.out.println("Some exception occured while checking webelement displayed "+ locator);
            return false;
        }
    }
}


