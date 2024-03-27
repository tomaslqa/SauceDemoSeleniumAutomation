package com.saucelab.qa.testcases;

import com.saucelab.qa.base.BaseTest;
import com.saucelab.qa.pages.CartPage;
import com.saucelab.qa.pages.HomePage;
import com.saucelab.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    LoginPage login;
    HomePage home;
    CartPage cart;

    public CartPageTest(){

        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        login = new LoginPage();
        login.login(prop.getProperty("username"),prop.getProperty("password" ));
        home = new HomePage();
        cart = new CartPage();
    }

    @Test
    public void verifyCartProductNameMatchesProductAdded() {
        String firstProductName = home.getProductName(1);
      home.addFirstProductToCartAndOpenCart();
        Assert.assertTrue(cart.isProductDisplayed(firstProductName));
        Reporter.log("Verified product in Cart", true);

    }

    @Test
    public void verifyProductIsRemovedFromCart()    {
        String firstProductName = home.getProductName(1);
        home.addFirstProductToCartAndOpenCart();
        cart.clickRemoveBtn();
        Assert.assertFalse(cart.isProductDisplayed(firstProductName));
        Reporter.log("Verified product removed from Cart", true);
    }

    @Test
    public void verifyCartQuantity()
    {home.addFirstProductToCartAndOpenCart();
        Assert.assertTrue(cart.isQuantityCorrect("1"));
        Reporter.log("Verified Cart quantity", true);
    }

    @Test
    public void verifyProductPriceInCart()    {
        String firstProductPrice = home.getProductPrice(1);
        home.addFirstProductToCartAndOpenCart();
        Assert.assertTrue(cart.isProductPriceRight("$"+firstProductPrice));
        Reporter.log("Verified product price in Cart", true);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
