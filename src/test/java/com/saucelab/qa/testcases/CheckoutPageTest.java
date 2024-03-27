package com.saucelab.qa.testcases;

import com.saucelab.qa.base.BaseTest;
import com.saucelab.qa.pages.*;
import com.saucelab.qa.util.TestStrings;
import com.saucelab.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutPageTest extends BaseTest {


    LoginPage login;
    HomePage home;
    CartPage cart;
    CheckoutInformationPage checkoutInfo;
    CheckoutOverviewPage checkoutOverview;
    OrderCompletedPage orderCompleted;


    String sheetName = "checkoutdata";

    public CheckoutPageTest() {

        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        login = new LoginPage();
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        home = new HomePage();
        cart = new CartPage();
        checkoutInfo = new CheckoutInformationPage();
        checkoutOverview = new CheckoutOverviewPage();
        orderCompleted = new OrderCompletedPage();
    }


    @DataProvider
    public Object[][] getData() {
        Object data[][] = TestUtil.getTestData(sheetName);
        return data;
    }


    @Test
    public void checkoutInformationPageTitleTest() {
        home.addFirstProductToCartAndOpenCart();
        cart.clickCheckout();
        String title = checkoutInfo.getcheckoutInfoPageTitle();
        Assert.assertEquals(title, TestStrings.CHECKOUT_INFO_TITLE, "Title Mismatch");
        Reporter.log("Verified checkout page title", true);
    }

    @Test(dataProvider = "getData")
    public void enterCheckoutInformationAndClickContinue(String name, String surname, String zipcode) {
        home.addFirstProductToCartAndOpenCart();
        cart.clickCheckout();
        checkoutInfo.enterInfoAndClickContinueButton(name, surname, zipcode);
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.CHECKOUT_OVERVIEW_URL, "Url mismatch");
        Reporter.log("Verified Checkout Overview page displays", true);

    }

    @Test(dataProvider = "getData")
    public void clickCancelOnCheckoutOverviewPage(String name, String lastName, String zipcode) {
        home.addFirstProductToCartAndOpenCart();
        cart.clickCheckout();
        checkoutInfo.enterInfoAndClickContinueButton(name, lastName, zipcode);
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.CHECKOUT_OVERVIEW_URL, "Url mismatch");
        checkoutOverview.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.HOME_URL, "Url mismatch");
        Reporter.log("Verified canceled from Checkout Overview page", true);

    }

    @Test(dataProvider = "getData")
    public void clickFinishOnCheckoutOverviewPage(String name, String lastName, String zipcode) {
        home.addFirstProductToCartAndOpenCart();
        cart.clickCheckout();
        checkoutInfo.enterInfoAndClickContinueButton(name, lastName, zipcode);
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.CHECKOUT_OVERVIEW_URL, "Url mismatch");
        checkoutOverview.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.CHECKOUT_COMPLETE_URL, "Url mismatch");
        Assert.assertTrue(orderCompleted.verifyOrderCompletedMessageIsDisplayed());
        Reporter.log("Verified checkout completed sucessfully", true);
    }

    @AfterMethod
    public void teardown() {

        driver.quit();
    }

}
