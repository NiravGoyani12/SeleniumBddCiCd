package org.example.step_def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.example.driver.DriverManager;
import org.example.pages.HomePage;
import org.example.pages.ProductListPage;
import org.hamcrest.core.StringEndsWith;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddToBasket extends DriverManager {
     DriverManager driverManager = new DriverManager();
     HomePage homePage = new HomePage();
     ProductListPage productListPage = new ProductListPage();

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Throwable {
//        validation through url
       String actualUrl = driverManager.getUrl();
        System.out.println(actualUrl);
        assertThat(actualUrl,is(endsWith("nopcommerce.com/")));

//        validation through title
       String actualTitle=driverManager.getTitle();
        System.out.println(actualTitle);
        assertThat(actualTitle,is(equalToIgnoringCase("nopCommerce demo store")));

//        validation through text from home page

       String actualTextOnHomePage= homePage.getTextFromHomePage();
       System.out.println(actualTextOnHomePage);
       assertThat(actualTextOnHomePage,is(equalToIgnoringCase("Welcome to our store")));



    }

    @When("^I click on the \"([^\"]*)\"$")
    public void i_click_on_the(String headerName) throws Throwable {
        homePage.clickOnBooks();
        switch (headerName){
            case "Books":
                homePage.clickOnBooks();
            break;
            case "Jewelry":
                homePage.clickOnJewelry();
                break;
        }

    }

    @Then("^I should see result page$")
    public void i_should_see_result_page() throws Throwable {
        String actualTextOnPlp=productListPage.getTextFromListPage();
        System.out.println(actualTextOnPlp);
        assertThat(actualTextOnPlp, is(equalToIgnoringCase("Books")));

    }

}
