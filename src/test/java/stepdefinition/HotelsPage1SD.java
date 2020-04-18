package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webpages.HotelsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import util.BasePage;

import java.io.IOException;
import java.util.List;

public class HotelsPage1SD {

    //private HotelsPage hotelsPage = new HotelsPage(SharedSD.getDriver());
    BasePage basePage;
    WebDriver driver;
    HotelsPage hotelsPage;

    @Given("^Initialize the browser$")
    public void initializeTheBrowser() throws IOException {
        basePage = new BasePage();
        driver = basePage.initDriver();
        hotelsPage = new HotelsPage(driver);
    }

    @Given("^I am on Hotels Home Page$")
    public void iAmOnHotelsPage(){
        Assert.assertEquals(SharedSD.getDriver().getCurrentUrl(),
                "https://www.hotels.com/");
    }
    @Given("^I am on default locations search result screen$")
    public void iAmOnLocationSearch() throws InterruptedException {
        hotelsPage.customerSelections();
    }
    @When("^I select property class (.*) hotels$")
    public void iSelectHotelStar(String star) throws InterruptedException {
        hotelsPage.selectHotelsStar(star);
        List<String> titles = hotelsPage.printResult();
        for(String element : titles){
            System.out.println(element);
        }
    }
    @Then("^I verify system displays only (.*) hotels on search result$")
    public void iVerifySystemDisplaysHotelsOnSearchResult(String star){
        Assert.assertEquals(star, HotelsPage.verifyHotel);
    }
}
