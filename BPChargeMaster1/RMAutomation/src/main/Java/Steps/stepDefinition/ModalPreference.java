package Steps.stepDefinition;

import com.LoginRM.Pages.Login;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static org.apache.commons.io.FileUtils.copyFile;

public class ModalPreference {

    WebDriver driver=new ChromeDriver();

    public static final String URL = ("https://www.qa.rightmove.com/login.html");

    @Given("^:  That user has already login$")
    public void _that_user_has_already_login() throws Throwable {

        driver.get(URL);
        driver.manage().window().maximize();
        Login login = new Login(driver);
        login.typeUserName();
        login.typeUserPassword();
        login.ClickOnLogin();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Testing Overseas

        driver.get("https://www.qa.rightmove.com/overseas-property/property-49524623.html?utm_content=v2-ealertspropertyimage&utm_medium=email&utm_source=emailupdates&utm_campaign=emailupdates1day&utm_term=buying&sc_id=10001&onetime_FromEmail=true&cid=d39b3a07-11cd-4ad8-8f9b-ac1bcdf2cfbb&csg=75c0596c541ae5d37f9715eaeab7b9c7e40ea278ac24e48691bf9002b3bf0509");

    }

    @When("^:  Have navigated to the my Details and Preference Tab$")
    public void have_navigated_to_the_my_Details_and_Preference_Tab() throws Throwable {

        // mrm-content mrm-preferences mrm-preferences--popup-new-design mrm-preferences--v4consent

        if (driver.getPageSource().contains("10 bedroom")) {
            System.out.println("Your email preferences - PASS");

        } else {
            System.out.println("Your email preferences not present- FAIL");

        }

    }

    @Then("^:  Then I can see user selections on Tabs$")
    public void then_I_can_see_user_selections_on_Tabs() throws Throwable {

        TakesScreenshot Modal1=(TakesScreenshot)driver;
        File sra =Modal1.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(sra, new File("./target/Screenshots/Modal.png/")); // Read into Path
        System.out.println("ModalProperty");
        driver.quit();

    }

}
