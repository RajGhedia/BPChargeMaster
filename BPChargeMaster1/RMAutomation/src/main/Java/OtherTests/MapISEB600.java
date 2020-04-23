package OtherTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;

public class MapISEB600 extends JPanel {


    public static void main(String args[]) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // driver.get("https://www.qa.rightmove.com/property-for-sale/map.html?locationIdentifier=POSTCODE%5E902806&numberOfPropertiesPerPage=499&radius=0.25&includeSSTC=false&viewType=MAP&viewport=-0.481253%2C-0.440055%2C51.4444%2C51.4567");
        //driver.get("http://h1-qpvw01.rightmove.com/property-for-sale/map.html?locationIdentifier=POSTCODE%5E902806&numberOfPropertiesPerPage=499&radius=0.25&includeSSTC=false&viewType=MAP&viewport=-0.481253%2C-0.440055%2C51.4444%2C51.4567");
        driver.get("http://www.rightmove.co.uk/property-for-sale/map.html?locationIdentifier=POSTCODE%5E902806&numberOfPropertiesPerPage=499&radius=0.25&includeSSTC=false&viewType=MAP&viewport=-0.481253%2C-0.440055%2C51.4444%2C51.4567");

        Thread.sleep(500);
        //ZOOM IN
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Report a map error'])[1]/following::button[1]")).click();
        Thread.sleep(10000);
       // driver.quit();

    }
}