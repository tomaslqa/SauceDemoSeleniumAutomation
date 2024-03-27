package com.saucelab.qa.pages;

import com.saucelab.qa.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BaseTest {


    @FindBy(id = "react-burger-menu-btn")
    WebElement HamburgerMenu;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;
    @FindBy(xpath = "//*[@class = 'title']")
    WebElement homeTitle;

    @FindBy(xpath = "//*[@class = 'product_sort_container']")
    WebElement sortComboBox;

    @FindBy(xpath = "//a[@class = 'shopping_cart_link']")
    WebElement shoppingCartButton;

    @FindBy(xpath = "//*[@class = 'shopping_cart_badge']")
    WebElement shoppingCartBadge;

    @FindBy(xpath = "//div[@class = 'inventory_item']")
    List<WebElement> products;
    @FindBy(xpath = "//div[@class = 'inventory_item_name']")
    WebElement productName;

    @FindBy(xpath = "//div[@class = 'inventory_item_price']")
    WebElement productPrice;

    @FindBy(xpath = "//button[@class = 'btn btn_primary btn_small btn_inventory '][1]")
    WebElement addToCartFirstItem;

    @FindBy(xpath = "//button[@class = 'btn btn_secondary btn_small btn_inventory ']")
    WebElement removeFromCart;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public String pageTitle() {
        return homeTitle.getText();
    }

    public void addFirstProductToCart() {
        addToCartFirstItem.click();
    }

    public boolean verifyCartBadgeValue(String expected) {
        boolean value = false;
        shoppingCartBadge.isDisplayed();
        String badgeValue = shoppingCartBadge.getText();
        if (badgeValue.equals(expected)) {
            value = true;
        }
        return value;

    }

    public boolean verifyNoCartBadge() {
        try {
            return shoppingCartBadge.isDisplayed();
        } catch (Exception ignored) {
            return false;
        }

    }

    public void removeFirstProductFromCart() {
        removeFromCart.click();
    }

    public void openCartFromHome() {
        shoppingCartButton.click();
    }

    public void addFirstProductToCartAndOpenCart() {
        addToCartFirstItem.click();
        openCartFromHome();
    }

    public void sortProductsByCriteria(String criteria) {
        Select select = new Select(sortComboBox);
        select.selectByVisibleText(criteria);
    }

    public List getAllPrices() {
        List<WebElement> elements = products;
        List<Double> prices = new ArrayList<Double>();
        for (WebElement element : elements) {
            String price = element.findElement(By.xpath("//div[@class = 'inventory_item_price']")).getText();
            Double value = Double.valueOf(price.substring(1));
            prices.add(value);

        }
        return prices;
    }

    public String getProductName(int order) {
        List<WebElement> elements = products;
        List<String> productNames = new ArrayList<String>();
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath("//div[@class = 'inventory_item_name ']")).getText();
            productNames.add(name);
        }
        return productNames.get(order - 1).toString();
    }

    public String getProductPrice(int order) {
        List<Double> prices = getAllPrices();
        return prices.get(order - 1).toString();
    }

    public boolean verifyPricesSortingLowToHigh() {
        List<Double> prices = getAllPrices();
        boolean sorted = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                sorted = false;
            }
        }
        return sorted;
    }

    public void logOut() {
        HamburgerMenu.click();
        logoutLink.click();

    }

}
