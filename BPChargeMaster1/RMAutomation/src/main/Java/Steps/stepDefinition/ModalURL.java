package Steps.stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class ModalURL {

        WebDriver driver=new ChromeDriver();

        @Given("^The agent is on the main RM home page and I have inputted a new URL$")
        public void the_agent_is_on_the_main_rm_home_page_and_i_have_inputted_a_new_url () throws Throwable {
            driver.manage().window().maximize();
        }

        @When("^The agent select url (.+)$")
        public void the_agent_select_url (String newurl) throws Throwable {
            driver.navigate().to(newurl);
            Thread.sleep(3000);
        }

        @Then("^site should not crash$")
        public void site_should_not_crash () throws Throwable {
            System.out.println("URl Checked");
            driver.quit();
        }


    }


