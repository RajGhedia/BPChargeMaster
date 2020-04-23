package Steps.stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class SearchMain {

/*
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    WebDriver driver=new ChromeDriver(options);

*/

  WebDriver driver=new ChromeDriver();
    // WebDriver driver=new FirefoxDriver();
    //WebDriver driver=new SafariDriver();


    @Given("^I am on the main RM home page$")
    public void i_am_on_the_main_rm_home_page() throws Throwable{
        driver.get("https://www.qa.rightmove.com/");
       driver.manage().window().maximize();

    }

    @When("^I select a search (.+)$")
    public void i_select_a_search(String Area) throws Throwable{

        driver.findElement(By.id("searchLocation")).click();
        //Thread.sleep(1000);
        driver.findElement(By.id("searchLocation")).clear();
        driver.findElement(By.id("searchLocation")).sendKeys(Area);
        Thread.sleep(3000);
        driver.findElement(By.id("buy")).click();

    }

    @Then("^I enter data$")
    public void i_enter_data() throws Throwable  {
        System.out.println("Reached Area");

        //clean up and close browers
        driver.quit();
    }


}
