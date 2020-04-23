package Steps.stepDefinition;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import static org.apache.commons.io.FileUtils.copyFile;

public class HideUnhide {

    //WebDriver driver=new ChromeDriver();
    WebDriver driver=new SafariDriver();


    @Given("^I am on the search results page$")
    public void i_am_on_the_search_results_page() {

        driver.get("https://www.rightmove.co.uk");
        driver.manage().window().maximize();
        driver.findElement(By.id("searchLocation")).click();
        //Thread.sleep(1000);
        driver.findElement(By.id("searchLocation")).clear();
        driver.findElement(By.id("searchLocation")).sendKeys("London");
        driver.findElement(By.id("buy")).click();
    }

    @When("^I select the X user can hide property$")
    public void i_select_the_X_user_can_hide_proprety() throws Throwable {

        driver.findElement(By.id("submit")).click();

        //scroll down
        ((JavascriptExecutor)driver).executeScript("scroll(0,600)");
        Thread.sleep(1500);

        // You will need to update the Property ID incase the data in the property list changes.

        driver.findElement(By.cssSelector("#property-70209058 > div.propertyCard > div.propertyCard-wrapper > div.property-hide-button.property-hide-button-enabled > div.property-hide-button-circle > div.no-svg-close.property-hide-button-x > svg")).click();

        TakesScreenshot Hide1=(TakesScreenshot)driver;
        File sra =Hide1.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(sra, new File("./target/Screenshots/Hide.png/")); // Read into Path
        System.out.println("HideProperty");
    }

    @Then("^User can not see the property and is face with undo link")
    public void user_can_not_see_the_property_and_is_face_with_undo_link() throws Throwable {
        Thread.sleep(5000);
        driver.quit();

    }
}
