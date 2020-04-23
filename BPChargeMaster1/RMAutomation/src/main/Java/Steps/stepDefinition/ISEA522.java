package Steps.stepDefinition;

        import org.openqa.selenium.JavascriptExecutor;
        import cucumber.api.PendingException;
        import cucumber.api.java.en.Given;
        import cucumber.api.java.en.When;
        import cucumber.api.java.en.Then;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;
        import org.openqa.selenium.JavascriptException;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
public class ISEA522 {

    WebDriver driver = new ChromeDriver();
    public static final String URL = ("https://www.qa.rightmove.com/");

    @Given("^I am on the footer link$")
    public void i_am_on_the_footer_link() throws Throwable {
        driver.get(URL);
        Thread.sleep(1000);
        driver.manage().window().maximize();
    }

    @When("^I selected a footer link$")
    public void i_selected_a_footer_link() throws Throwable {
        //scroll page
        ((JavascriptExecutor)driver).executeScript("scroll(0,600)");
    }

    @Then("^Link should be collapsable$")
    public void link_should_be_collapsable() throws Throwable {

        /*
        links-group-chevron-icon
        links-group-chevron-open
        links-group-chevron-clappsed
        */

    }

    @Then("^Hyperlinks should remain as hyperlinks$")
    public void hyperlinks_should_remain_as_hyperlinks() throws Throwable {

    }

    @Then("^Plain text should remain as Plain text$")
    public void plain_text_should_remain_as_plain_text() throws Throwable {

    }

}