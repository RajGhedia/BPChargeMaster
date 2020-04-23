package OtherTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyFile;

public class FeatureSwitches {

   @Test

    public static void main(String[] args) throws InterruptedException, IOException {

     ChromeOptions options = new ChromeOptions();
     options.addArguments("--headless");
     WebDriver driver=new ChromeDriver(options);

     // WebDriver driver=new ChromeDriver();
     // WebDriver driver=new FirefoxDriver();
     // WebDriver driver=new SafariDriver();

        driver.get("http://fsw.qa.rightmove.com/#/");
        Thread.sleep(5000);

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Feature Switch Directory'])[1]/following::input[1]")).sendKeys("suggestions");
        Thread.sleep(5000);
        TakesScreenshot FW=(TakesScreenshot)driver;
        File sra =FW.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(sra, new File("./target/Screenshots/Suggestion.png/")); // Read into Path
        System.out.println("Suggestion");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=SuggestionsSwitches.GARDEN_FILTER_ENABLED");
        Thread.sleep(5000);
        TakesScreenshot FW1=(TakesScreenshot)driver;
        File srb =FW1.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(srb, new File("./target/Screenshots/GardenFS.png/")); // Read into Path
        System.out.println("Garden");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=SuggestionsSwitches.PARKING_FILTER_ENABLED");
        Thread.sleep(5000);
        TakesScreenshot FW2=(TakesScreenshot)driver;
        File src =FW2.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(src, new File("./target/Screenshots/ParkingFS.png/")); // Read into Path
        System.out.println("Parking");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=SuggestionsSwitches.MAX_PRICE_ENABLED");
        Thread.sleep(5000);
        TakesScreenshot FW3=(TakesScreenshot)driver;
        File srd =FW3.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(srd, new File("./target/Screenshots/MaxFS.png/")); // Read into Path
        System.out.println("Max");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=SuggestionsSwitches.MIN_PRICE_ENABLED");
        Thread.sleep(5000);
        TakesScreenshot FW4=(TakesScreenshot)driver;
        File sre =FW4.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(sre, new File("./target/Screenshots/MinFS.png/")); // Read into Path
        System.out.println("Min");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=SuggestionsSwitches.PROPERTY_TYPE_ENABLED");
        Thread.sleep(5000);
        TakesScreenshot FW6=(TakesScreenshot)driver;
        File srg =FW6.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(srg, new File("./target/Screenshots/PropertyFS.png/")); // Read into Path
        System.out.println("Property");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=SuggestionsSwitches.MAX_BEDS_ENABLED");
        Thread.sleep(5000);
        TakesScreenshot FW7=(TakesScreenshot)driver;
        File srh =FW7.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(srh, new File("./target/Screenshots/MaxBedFS.png/")); // Read into Path
        System.out.println("Maxbed");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=SuggestionsSwitches.RADIUS_ENABLED");
        Thread.sleep(5000);
        TakesScreenshot FW8=(TakesScreenshot)driver;
        File srj =FW8.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(srj, new File("./target/Screenshots/RadiusFS.png/")); // Read into Path
        System.out.println("Radius");

        driver.navigate().to("http://fsw.qa.rightmove.com/#/feature?id=GDPRSwitches.SHOW_RECONSENT");
        Thread.sleep(5000);
        TakesScreenshot FW9=(TakesScreenshot)driver;
        File srk =FW9.getScreenshotAs(OutputType.FILE); //Reads into memory
        copyFile(srk, new File("./target/Screenshots/ReconsentFS.png/")); // Read into Path
        System.out.println("Reconsent");

        driver.quit();

    }
}