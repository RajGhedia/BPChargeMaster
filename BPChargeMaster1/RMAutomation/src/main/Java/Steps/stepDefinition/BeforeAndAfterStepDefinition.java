package Steps.stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.management.remote.JMXConnector;

// This logic is for all feature files


public class BeforeAndAfterStepDefinition {



    @Before("@WebTest")
    public void precondition(){
           /*
    ChromeOptions options = new ChromeOptions();
     options.addArguments("--headless");
    WebDriver driver=new ChromeDriver(options);

        */

        WebDriver driver=new ChromeDriver();
        //WebDriver driver=new SafariDriver();
        //WebDriver driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.qa.rightmove.com");
        System.out.println("precondition");
        // We open browser


    }

    @After("@WebTest")
    public void postCondition(){
        System.out.println("post condition");

        //driver.quit();

        // we close all

    }

    WebDriver driver;
    DesiredCapabilities caps =new DesiredCapabilities();

    @Before("@MobileTest")
    public void preconditionmobile(){
        System.out.println("precondition");
        caps.setCapability("deviceName","TA391019B7");
        caps.setCapability("platformName","Android");
        caps.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
        caps.setCapability(CapabilityType.VERSION,"7.0");


        // We open browser

    }
    @After("@MobileTest")
    public void postConditionmobile(){
        System.out.println("post condition");

        // Close Devices and get the hell out of here :-)

    }

}


