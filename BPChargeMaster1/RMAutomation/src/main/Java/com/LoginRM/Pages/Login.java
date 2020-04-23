package com.LoginRM.Pages;

/*
*
*
* This Class will store all the locator and methods of login
*
*
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    WebDriver driver;
    By Username =By.id("email");
    By Password =By.id("password");
    By loginButton=By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1] ");

    public Login(WebDriver driver)

    {
        this.driver=driver;
    }

    //public void typeUserName() { driver.findElement(Username).sendKeys("raj.ghedia@rightmove.com"); }
    //public void typeUserPassword(){driver.findElement(Password).sendKeys("Liverpool1979");}

    public void typeUserName() { driver.findElement(Username).sendKeys("raj.leg@rm.com"); }
    public void typeUserPassword(){driver.findElement(Password).sendKeys("Password01");}
    public void ClickOnLogin()
    {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Show'])[1]/following::button[1]")).click();
    }

}