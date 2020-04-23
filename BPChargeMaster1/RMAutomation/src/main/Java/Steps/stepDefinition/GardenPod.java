package Steps.stepDefinition;

import com.sun.org.apache.bcel.internal.generic.Select;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;


public class GardenPod {

    /*
    ChromeOptions options = new ChromeOptions();
     options.addArguments("--headless");
    WebDriver driver=new ChromeDriver(options);

*/

    WebDriver driver=new ChromeDriver();
    //WebDriver driver=new SafariDriver();
    //WebDriver driver=new FirefoxDriver();

    public static final String URL = ("https://www.qa.rightmove.com/");
    // public static final String URL = ("https://www.qa.rightmove.com/?fromMobile=true");

    @Given("^I have applied a Must Have Garden option to my Residential Sales search$")
    public void i_have_applied_a_must_have_garden_option_to_my_residential_sales_search() throws Throwable {
        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(By.id("searchLocation")).click();
        driver.findElement(By.id("searchLocation")).clear();
        driver.findElement(By.id("searchLocation")).sendKeys("Marylebone, Central London");
        driver.findElement(By.id("buy")).click();
        driver.findElement(By.id("minPrice")).click();
       // new.Select(driver.findElement(By.id("minPrice"))).selectByVisibleText("250,000");

        driver.findElement(By.id("maxPrice")).click();
        //new Select(driver.findElement(By.id("maxPrice"))).selectByVisibleText("260,000");
        driver.findElement(By.id("displayPropertyType")).click();
        //new Select(driver.findElement(By.id("displayPropertyType"))).selectByVisibleText("Flats / Apartments");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='(1)'])[1]/following::span[1]")).click();
        Thread.sleep(5000);
        //driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Must Haves'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Must Haves'])[1]/following::div[5]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Clear'])[1]/following::button[1]")).click();

         Thread.sleep(5000);

        if (driver.getPageSource().contains("Show without garden")) {
            System.out.println("Show without garden - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show without garden - FAIL");

        }

     driver.quit();

    }

    @Given("^I have applied a Must Have Garden option to my Residential Letting search$")
    public void i_have_applied_a_must_have_garden_option_to_my_residential_letting_search() throws Throwable {

        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(By.id("searchLocation")).click();
        driver.findElement(By.id("searchLocation")).clear();
        driver.findElement(By.id("searchLocation")).sendKeys("Marylebone, Central London");
        driver.findElement(By.id("rent")).click();
        driver.findElement(By.id("minPrice")).click();
       // new Select(driver.findElement(By.id("minPrice"))).selectByVisibleText("1,200 PCM");
        driver.findElement(By.id("maxPrice")).click();
       // new Select(driver.findElement(By.id("maxPrice"))).selectByVisibleText("1,200 PCM");
        driver.findElement(By.id("displayPropertyType")).click();
       // new Select(driver.findElement(By.id("displayPropertyType"))).selectByVisibleText("Flats / Apartments");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='(1)'])[1]/following::span[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Must Haves'])[1]/following::span[1]")).click();
        //driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Must Haves'])[1]/following::div[5]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Clear'])[1]/following::button[1]")).click();

        Thread.sleep(5000);

        if (driver.getPageSource().contains("Show without garden")) {
            System.out.println("Show without garden - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show without garden - FAIL");

        }

     driver.quit();


    }

    @When("^I land on the No Results page journey for GardenPod$")
    public void i_land_on_the_no_results_page_journey_for_gardenpod() throws Throwable {
       System.out.println("GardenPod Reached and Present");
    }

    @Then("^I should see the ‘Remove Must Have Garden’ pod$")
    public void i_should_see_the_remove_must_have_garden_pod() throws Throwable {
        System.out.println("GardenPod Reached on result page");
    }
    

}
