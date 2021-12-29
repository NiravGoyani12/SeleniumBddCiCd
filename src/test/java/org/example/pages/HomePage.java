package org.example.pages;

import org.example.driver.DriverManager;
import org.openqa.selenium.By;

public class HomePage extends DriverManager {

     public String getTextFromHomePage(){

      return   driver.findElement(By.xpath("//div[@class='topic-block-title']/h2")).getText();
     }

     public void clickOnBooks(){
         waitForElementVisibility(driver.findElement(By.xpath("//*[@class='header-menu']/ul[1]/li[5]")), 40, "web element is not visible");
         driver.findElement(By.xpath("//*[@class='header-menu']/ul[1]/li[5]")).click();
     }

    public void clickOnJewelry(){
        driver.findElement(By.xpath("//*[@class='header-menu']/ul[1]/li[6]")).click();
    }

}
