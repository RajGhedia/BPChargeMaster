package com.LoginRM.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccount {


    WebDriver driver;

    {
        this.driver=driver;
    }

    public void FirstName()
    {
        driver.findElement(By.id("firstName")).sendKeys("Raj");

    }

    public void LastName()
    {
        driver.findElement(By.id("lastName")).sendKeys("Raj");

    }

    public void Email()
    {   driver.findElement(By.id("email")).sendKeys("same.smith@RM.com");

    }

    public void Password()
    {

        driver.findElement(By.id("password")).sendKeys("Password0001!");
    }


}
