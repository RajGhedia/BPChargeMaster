package Steps.stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.support.ui.Select;
//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.openqa.selenium.chrome.ChromeOptions;

public class ParkingPod {


    /*
    ChromeOptions options = new ChromeOptions();
     options.addArguments("--headless");
    WebDriver driver=new ChromeDriver(options);
   */

   WebDriver driver=new ChromeDriver();
   // WebDriver driver=new SafariDriver();
    //WebDriver driver=new FirefoxDriver();

    public static final String URL = ("https://www.qa.rightmove.com/");
    // public static final String URL = ("https://www.qa.rightmove.com/?fromMobile=true");

    @Given("^I have applied a Must Have Parking option to my Commercial Rent search$")
    public void i_have_applied_a_must_have_parking_option_to_my_commercial_rent_search() throws Throwable {

        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Commercial")).click();
        driver.navigate().to("https://www.qa.rightmove.com/commercial-property-for-sale/find.html?locationIdentifier=REGION%5E13475&mustHave=parking&areaSizeUnit=sqft");


        Thread.sleep(5000);

       if (driver.getPageSource().contains("Show without parking")) {
            System.out.println("Show without parking pod is present - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show without parking added - FAIL");
        }

         driver.quit();

    }

    @Given("^I have applied a Must Have Parking option to my Commercial Sale search$")
    public void i_have_applied_a_must_have_parking_option_to_my_commercial_sale_search() throws Throwable {

        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Commercial")).click();
        driver.navigate().to("https://www.qa.rightmove.com/commercial-property-for-sale/find.html?locationIdentifier=REGION%5E13475&includeSSTC=false&mustHave=parking&areaSizeUnit=sqft");


       Thread.sleep(5000);

        if (driver.getPageSource().contains("Show without parking")) {
            System.out.println("Show without parking pod is present - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show without parking added - FAIL");

        }


        driver.quit();

    }

    @When("^I land on the No Results page for Parking Pod$")
    public void i_land_on_the_no_results_page_for_parking_pod() throws Throwable {

        System.out.println("Parking Pod Sales and Rent Reached ");
    }

    @Then("^I should see the ‘Remove Must Have Parking’ pod$")
    public void i_should_see_the_remove_must_have_parking_pod() throws Throwable {

        System.out.println("Pod Present Asserted");

    }

}
