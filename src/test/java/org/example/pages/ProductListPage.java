package org.example.pages;

import org.example.driver.DriverManager;
import org.openqa.selenium.By;

public class ProductListPage extends DriverManager {

    public String getTextFromListPage(){
      return   driver.findElement(By.cssSelector(".page-title")).getText();
    }
}
