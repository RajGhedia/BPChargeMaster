package com.LoginRM.Testcases;
import com.LoginRM.Pages.Login;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerfiyLogin
{
    @Test
    public void verifyValidLogin()
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.qa.rightmove.com/login.html");
        Login login = new Login(driver);
        login.typeUserName();
        login.typeUserPassword();
        login.ClickOnLogin();

        //driver.quit();
    }

}
