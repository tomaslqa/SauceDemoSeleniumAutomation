package com.saucelab.qa.testcases;

import com.saucelab.qa.base.BaseTest;
import com.saucelab.qa.pages.HomePage;
import com.saucelab.qa.pages.LoginPage;
import com.saucelab.qa.util.TestStrings;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    LoginPage login;
    HomePage home;


    public HomePageTest(){

        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        login = new LoginPage();
        login.login(prop.getProperty("username"),prop.getProperty("password" ));
        home = new HomePage();
    }

    @Test
    public void homePageTitleTest(){
        String actualTitle = home.pageTitle();
        Assert.assertEquals(actualTitle, TestStrings.HOME_TITLE,"Home page Title mismatch");
        Reporter.log("Verified login page title", true);
    }

    @Test
    public void verifyProductsSortingByLowToHighPrices(){
        home.sortProductsByCriteria(TestStrings.PRICE_LOWHIGH);
        Assert.assertEquals(home.verifyPricesSortingLowToHigh(), true,"Prices not sorted low to high");
        Reporter.log("Verified prices sorting low to high", true);
    }

    @Test
    public void verifyOneProductAddedToCart() {
        home.addFirstProductToCart();
        Assert.assertEquals(home.verifyCartBadgeValue("1"), true,"Cart badge value mismatch");
        Reporter.log("Verified one product added displays on cart badge", true);
    }
    @Test
    public void verifyOneProductRemovedFromCart() {
        home.addFirstProductToCart();
        Assert.assertEquals(home.verifyCartBadgeValue("1"), true,"Cart badge value mismatch");
        home.removeFirstProductFromCart();
        Assert.assertEquals(home.verifyNoCartBadge(),false,"Cart Badge is displayed");
        Reporter.log("Verified cart badge s not displaying when product is removed from Cart", true);
    }
    @Test
    public void logOutFromHome() {
        home.logOut();
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.LOGIN_URL,"Url mismatch");
        Reporter.log("Verified logout from Home page", true);
    }


    @AfterMethod
    public void teardown (){

        driver.quit();
    }
}
