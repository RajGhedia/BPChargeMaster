package OtherTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.Urls;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class MOBILETEST {


    WebDriver driver;
    DesiredCapabilities caps = new DesiredCapabilities();

    @BeforeTest

    public void InitConfig()

    {
        caps.setCapability("deviceName", "TA391019B7");
        caps.setCapability("platformName", "Android");
        caps.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability(CapabilityType.VERSION, "7.0");
        caps.setCapability(CapabilityType.APPLICATION_NAME,"");
    }

    @Test
    public void testApp() throws MalformedURLException{

        driver = new RemoteWebDriver  (new URL ("http://127.0.0.1:4723/wd/hub"),caps);
        //driver.get("http://www.rightmove.co.uk");
        driver.get("https://www.amazon.com");

    }
}
