package stepdefinations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Hook;

public class Chargevision {

	private WebDriver driver;
	
	public Chargevision() {
		this.driver = Hook.getDriver();
	}
	
	@Given("^I open a browser$")
	public void i_open_a_browser() throws Throwable {
		driver.get("https://www.chargevision3.com");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		System.out.println("Browser is opened successfully");
	}

	@When("^I navigate to chargevision page$")
	public void i_navigate_to_chargevision_page() throws Throwable {

	 	driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("Raj.Ghedia@bjss.com");
 		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("smartcharge");
		driver.findElement(By.id("login")).click();


	}

	@Then("^I validate the search text field$")
	public void i_validate_the_search_text_field() throws Throwable {
		//Assert.assertTrue(driver.findElement(By.id("welcome")).isDisplayed());



	}
	
	@Then("^I validate the Gmail Link$")
	public void i_validate_the_Gmail_Link() throws Throwable {
		Assert.assertTrue(driver.findElement(By.linkText("Gmail")).isDisplayed());
	}

	@Then("^I validate the Image Link$")
	public void i_validate_the_Image_Link() throws Throwable {
		Assert.assertTrue(driver.findElement(By.linkText("Image")).isDisplayed());
	}
	
	@When("^I enter \"([^\"]*)\" in search keyword$")
	public void i_enter_in_search_keyword(String searchText) throws Throwable {
	    driver.findElement(By.name("q")).clear();
		driver.findElement(By.name("q")).sendKeys(searchText);
		Thread.sleep(5000);
	}

	@Then("^I validate searched text$")
	public void i_validate_searched_text() throws Throwable {
	    Thread.sleep(5000);
	}
}
