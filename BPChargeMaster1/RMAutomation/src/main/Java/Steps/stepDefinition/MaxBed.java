package Steps.stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//import org.openqa.selenium.firefox.FirefoxDriver;

public class MaxBed {
/*

    ChromeOptions options = new ChromeOptions();
     options.addArguments("--headless");
    WebDriver driver=new ChromeDriver(options);

*/

    WebDriver driver = new ChromeDriver();
    //WebDriver driver=new SafariDriver();
   // WebDriver driver=new FirefoxDriver();

    public static final String URL = ("https://www.qa.rightmove.com/");
    // public static final String URL = ("https://www.qa.rightmove.com/?fromMobile=true");


    @Given("^I have applied a Max Beds option to my Residential Sales search$")
    public void i_have_applied_a_max_beds_option_to_my_residential_sales_search() throws Throwable {

        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(By.id("searchLocation")).sendKeys("Liverpool, Merseyside");
       //driver.findElement(By.linkText("Liverpool, Merseyside")).click();
        driver.findElement(By.id("buy")).click();
        driver.findElement(By.id("maxBedrooms")).click();
        new Select(driver.findElement(By.id("maxBedrooms"))).selectByVisibleText("Studio");
        new Select(driver.findElement(By.id("maxPrice"))).selectByVisibleText("80,000");
        new Select(driver.findElement(By.id("displayPropertyType"))).selectByVisibleText("Houses");
        driver.findElement(By.id("submit")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Studio'])[2]/following::span[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Detached'])[1]/following::div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Semi-detached'])[1]/following::div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"searchFilters\"]/div[1]/div[2]/div/div[2]/button")).click();
        Thread.sleep(1000);

        if (driver.getPageSource().contains("Show more beds")) {
            System.out.println("Show more beds pod is present - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show Show more beds - FAIL");
        }

        driver.quit();

    }

    @And("^where there would be results for no max beds option$")
    public void where_there_would_be_results_for_no_max_beds_option() throws Throwable {

    }


    @When("^I land on the No Results page for Max Bed$")
    public void i_land_on_the_No_Results_page_for_Max_Bed() throws Throwable {

    }

    @Then("^I should see the Max Beds pod$")
    public void i_should_see_the_max_beds_pod() throws Throwable {

    }

}