package Steps.stepDefinition;
//import MainObj.ConfigMain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class AddAnytime {

    WebDriver driver = new ChromeDriver();
   // WebDriver driver=new FirefoxDriver();
    //WebDriver driver=new SafariDriver();

    public static final String URL = ("https://www.qa.rightmove.com/");
    //public static final String URL = ("https://www.rightmove.co.uk");
    //public static final String URL = ("https://www.qa.rightmove.com/?fromMobile=true");


    @Given("^I have applied an ‘Added to site’ option to my Residential buy search in the last 24hour$")
    public void i_have_applied_an_added_to_site_option_to_my_residential_buy_search_in_the_last_24hour() throws Throwable {


            driver.get(URL);
            Thread.sleep(1000);
            driver.manage().window().maximize();
            driver.findElement(By.id("searchLocation")).click();
        //Thread.sleep(1000);
            driver.findElement(By.id("searchLocation")).clear();
            driver.findElement(By.id("searchLocation")).sendKeys("London");
        //Thread.sleep(1000);
            driver.findElement(By.id("buy")).click();
            driver.findElement(By.id("maxDaysSinceAdded")).click();
            driver.findElement(By.id("maxDaysSinceAdded")).click();


            new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 24 hours");
                //new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 3 days");
                //new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 7 days");
                //new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 14 days");


        //Thread.sleep(1000);
            driver.findElement(By.id("submit")).click();

        if (driver.getPageSource().contains("Show added anytime")) {
            System.out.println("Show added anytime pod is present - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show added anytime added - FAIL");

        }

            driver.quit();

    }

    @Given("^I have applied an ‘Added to site’ option to my Residential rent search in the last 24hour$")
    public void i_have_applied_an_added_to_site_option_to_my_residential_rent_search_in_the_last_24hour() throws Throwable {

            driver.get(URL);
            driver.manage().window().maximize();
            driver.findElement(By.id("searchLocation")).click();
            driver.findElement(By.id("searchLocation")).clear();
            driver.findElement(By.id("searchLocation")).sendKeys("London");
        //Thread.sleep(1000);
            driver.findElement(By.id("rent")).click();
        //Thread.sleep(1000);
            driver.findElement(By.id("maxDaysSinceAdded")).click();
            driver.findElement(By.id("maxDaysSinceAdded")).click();
            new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 24 hours");
        //Thread.sleep(1000);
            driver.findElement(By.id("submit")).click();


        if (driver.getPageSource().contains("Show added anytime")) {
            System.out.println("Show added anytime pod is present - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show added anytime added - FAIL");

        }
            driver.quit();
    }

    @Given("^I have applied can ‘Added to site’ option to my Oversea Buy search in the last 24hour$")
    public void i_have_applied_an_added_to_site_option_to_my_oversea_buy_search_in_the_last_24hour() throws Throwable {
        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Overseas")).click();
        driver.navigate().to("https://www.qa.rightmove.com/overseas-property.html");
        driver.findElement(By.linkText("Albania")).click();
        driver.findElement(By.id("maxDaysSinceAdded")).click();
        new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 24 hours");
        driver.findElement(By.id("submit")).click();

        if (driver.getPageSource().contains("Show added anytime")) {
            System.out.println("Show added anytime pod is present - PASS");
            // Thread.sleep(10000);

        } else {
            System.out.println("Show added anytime added - FAIL");

        }
        driver.quit();

    }

    @Given("^I have applied an ‘Added to site’ option to my Commercial Buy search in the last 24hour$")
    public void i_have_applied_an_added_to_site_option_to_my_commercial_buy_search_in_the_last_24hour() throws Throwable {

            driver.get(URL);
            driver.manage().window().maximize();
            driver.findElement(By.linkText("Commercial")).click();
            driver.navigate().to("https://www.qa.rightmove.com/commercial-property-for-sale.html");
            driver.findElement(By.id("searchLocation")).click();
            driver.findElement(By.id("searchLocation")).clear();
            driver.findElement(By.id("searchLocation")).sendKeys("London");
            driver.findElement(By.id("search")).click();
            driver.findElement(By.id("maxDaysSinceAdded")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Size:'])[3]/following::div[3]")).click();
            driver.findElement(By.id("maxDaysSinceAdded")).click();
            new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 24 hours");
            driver.findElement(By.id("submit")).click();

            if (driver.getPageSource().contains("Show added anytime")) {
                System.out.println("Show added anytime pod is present - PASS");
                // Thread.sleep(10000);

            } else {
                System.out.println("Show added anytime added - FAIL");

            }

            driver.quit();
    }

    @Given("^I have applied an ‘Added to site’ option to my Commercial rent search in the last 24hour$")
    public void i_have_applied_an_added_to_site_option_to_my_commercial_rent_search_in_the_last_24hour() throws Throwable {
        {
            driver.get(URL);
            driver.manage().window().maximize();
            driver.findElement(By.linkText("Commercial")).click();
            driver.navigate().to("https://www.qa.rightmove.com/commercial-property-to-let.html");
            driver.findElement(By.id("searchLocation")).click();
            driver.findElement(By.id("searchLocation")).clear();
            driver.findElement(By.id("searchLocation")).sendKeys("London");
            driver.findElement(By.id("initialSearch")).submit();
            driver.findElement(By.id("maxDaysSinceAdded")).click();
            new Select(driver.findElement(By.id("maxDaysSinceAdded"))).selectByVisibleText("Last 24 hours");
            driver.findElement(By.id("submit")).click();

            if (driver.getPageSource().contains("Show added anytime")) {
                System.out.println("Show added anytime pod is present - PASS");
                // Thread.sleep(10000);

            } else {
                System.out.println("Show added anytime added - FAIL");

            }

            driver.quit();
        }
    }

    @When("^I land on the No Results page$")
    public void i_land_on_the_no_results_page() throws Throwable {
        System.out.println("Actioned");
    }

    @Then("^I should see the ‘Remove Added to Site’ pod$")
    public void i_should_see_the_remove_added_to_site_pod() throws Throwable {
        System.out.println("Executed");


    }

}