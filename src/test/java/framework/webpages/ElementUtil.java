package framework.webpages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static stepdefinition.SharedSD.getDriver;

public class ElementUtil {

    WebDriver driver = null;

    // This is the most common wait function used in selenium
    public static WebElement webAction(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .withMessage(
                        "WebDriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown");
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
    public void clickOn(By locator) {
        webAction(locator).click();
    }
    public void setValue(By locator, String value) {
        webAction(locator).sendKeys(value);
    }
    public String getTextFromElement(By locator) {
        return webAction(locator).getText();
    }
    public boolean isElementDisplayed(By locator) {
        return webAction(locator).isDisplayed();
    }
    public boolean isElementSelected(By locator) {
        return webAction(locator).isSelected();
    }
    public void selectFromDropdown(By locator, String dropdownText) {
        WebElement month = webAction(locator);
        Select selectMonth = new Select(month);
        //select element by visible text
        selectMonth.selectByVisibleText(dropdownText);
    }
    public void selectFromDropdown(By locator, int index) {
        WebElement month = webAction(locator);
        Select selectMonth = new Select(month);
        //select element by index
        selectMonth.selectByIndex(index);
    }
    public boolean isElementEnabled(By locator) {
        return webAction(locator).isEnabled();
    }
    public List<WebElement> webElements(By locator){
        return getDriver().findElements(locator);
    }
    public void scrollByElement(By locator){
        WebElement scrollElement = webAction(locator);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", scrollElement);
    }
    public void scrollByPixel(String xPixel, String yPixel){
        String string = "window.scrollBy(" + xPixel +"," + yPixel + ")";
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(string);
    }
    public void clear(By locator){
        webAction(locator).clear();
    }
    public void windowMaximize(){
        getDriver().manage().window().maximize();
    }
    public void moveToElement(By locator){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(webAction(locator)).build().perform();
    }

    public static void scrollDownIterator() throws InterruptedException {
        // TODO Auto-generated method stub
        //This is to scroll the page
        int y = 1000;
        for (int i = 0; i < 10; i++) {
            JavascriptExecutor jse = (JavascriptExecutor)getDriver();
            jse.executeScript("window.scrollBy(0,"+Integer.toString(y)+")");
            y += 100;
            Thread.sleep(500);
        }
    }
}
