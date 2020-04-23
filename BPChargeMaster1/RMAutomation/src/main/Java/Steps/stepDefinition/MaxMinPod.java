package Steps.stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import org.openqa.selenium.chrome.ChromeOptions;


public class MaxMinPod {

    /*
    ChromeOptions options = new ChromeOptions();
     options.addArguments("--headless");
    WebDriver driver=new ChromeDriver(options);

*/


   WebDriver driver=new ChromeDriver();
    //WebDriver driver=new SafariDriver();
    //WebDriver driver=new FirefoxDriver();



    @Given("^I am on the No Results page$")
    public void i_am_on_the_no_results_page() throws Throwable {
      //  driver.manage().window().maximize();
    }

    @And("^The (.+) is$")
    public void the_is(String site) throws Throwable {

        driver.navigate().to(site);

    }

    @When("^I have checked that the property value allows only one price increment$")
    public void i_have_checked_that_the_property_value_allows_only_one_price_increment() throws Throwable {

    }

    @Then("^I should see any Min Pod displayed with a suggestion$")
    public void i_should_see_any_min_pod_displayed_with_a_suggestion() throws Throwable {


        Thread.sleep(1000);
        if (driver.getPageSource().contains("Go cheaper")) {
            System.out.println("Go cheaper - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Go cheaper - FAIL");

        }

        driver.quit();


    }

    @Then("^I should see any Max Pod displayed with a suggestion$")
    public void i_should_see_any_max_pod_displayed_with_a_suggestion() throws Throwable {
        Thread.sleep(1000);

      if (driver.getPageSource().contains("Increase my budget")) {
            System.out.println("Increase my budget - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Increase my budget - FAIL");



        }

        driver.quit();



    }


}
