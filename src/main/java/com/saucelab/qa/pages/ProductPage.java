package com.saucelab.qa.pages;

import com.saucelab.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BaseTest {
    @FindBy(xpath = "//div[@class = 'inventory_details_name']")
    WebElement productName;

    @FindBy(xpath = "//div[@class = 'inventory_details_desc large_size']")
    List<WebElement> productDescr;

    @FindBy (xpath = "//div[@class = 'inventory_details_price']")
    WebElement productPrice;


    @FindBy(name ="back-to-products")
    WebElement backToProducts;
}
