package com.saucelab.qa.pages;

import com.saucelab.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletedPage extends BaseTest {

    @FindBy( xpath = "//*[@class = 'title']")
    WebElement orderCompletedlabel;

    @FindBy( xpath = "//*[@class = 'pony_express']")
    WebElement successIcon;

    @FindBy( xpath = "//*[@class = 'complete-header']")
    WebElement successLbl;

    @FindBy( xpath = "//*[@class = 'complete-text']")
    WebElement successMsg;

    @FindBy( xpath = "//button[@class = 'btn btn_primary btn_small')]")
    WebElement backToHomeBtn;


    LoginPage login;
    HomePage home;
    CartPage cart;
    CheckoutInformationPage checkoutInfo;

    public OrderCompletedPage() {
        PageFactory.initElements(driver, this);
    }

    public String getCheckoutInfoPageTitle() {
        return orderCompletedlabel.getText();
    }

    public boolean verifyOrderCompletedMessageIsDisplayed() {
        if (successLbl.isDisplayed() && successIcon.isDisplayed() && successMsg.isDisplayed()) {

            return true;
        } else {
            return false;
        }
    }


}
