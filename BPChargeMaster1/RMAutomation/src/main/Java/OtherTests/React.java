package OtherTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class React {

    @Test
    public void actionskeysDownAndkeysUp() throws InterruptedException
    {
       // WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa.rightmove.com");
        Actions actions=new Actions(driver);
  actions.keyDown(Keys.CONTROL)
  .click(driver.findElement(By.name("command")))
  .click(driver.findElement(By.name("shift")))
  .click(driver.findElement(By.name("i")))
 .keyUp(Keys.CONTROL);

      //  actions.keyDown(driver.findElement(By.name("command")), Keys.CONTROL)
        //        .keyDown(driver.findElement(By.name("shift")), Keys.CONTROL)
        //        .keyUp(driver.findElement(By.name("i")), Keys.CONTROL)
          //      .build()
           //     .perform();
        Thread.sleep(2000);
        driver.quit();
    }
}
