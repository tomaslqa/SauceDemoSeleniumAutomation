package com.saucelab.qa.pages;

import com.saucelab.qa.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    @FindBy(xpath = "//*[@class = 'title']")
    WebElement cartLabel;


    @FindBy(xpath = "//*[@class = 'inventory_item_name']")
    List<WebElement> productNames;

    @FindBy(xpath = "//*[@class = 'cart_item']")
    List<WebElement> cartItems;

    @FindBy(xpath = "//*[@class = 'inventory_item_price']")
    List<WebElement> productPrices;

    @FindBy(xpath = "//*[@class = 'cart_quantity']")
    WebElement quantity;

    @FindBy(xpath = "//button[@class = 'btn btn_secondary btn_small cart_button']")
    WebElement removeButton;
    @FindBy(xpath = "//*[@id='checkout']")
    WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    WebElement continueShopping;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    public boolean isProductDisplayed(String product) {
        for (WebElement element : productNames) {
            if (element.getText().equalsIgnoreCase(product)) {
                return true;
            }
        }
        return false;
    }


    public double totalPrice() {
        double total = 0.0;
        for (WebElement element : productPrices) {
            total = total + Double.parseDouble(element.getText().substring(1));
        }
        return total;
    }

    public boolean isProductPriceRight(String price) {
        Boolean match = false;
        for (WebElement element : cartItems) {
            WebElement productPriceElement = element.findElement(By.className("inventory_item_price"));
            String productPrice = productPriceElement.getText();
            if (productPrice.equals(price)) {
                match = true;
            } else {
                match = false;
            }
        }
        return match;
    }

    public boolean isQuantityCorrect(String expected) {
        Boolean quant = false;
        String actual = quantity.getText();
        if (actual.equals(expected)) {
            quant = true;
        }
        return quant;
    }

    public void clickRemoveBtn() {
        removeButton.click();
    }


    public void clickCheckout() {
        checkoutButton.click();
    }

    public void clickOnContinueShoppingButton() {
        continueShopping.click();
    }
}
