package com.saucelab.qa.pages;

import com.saucelab.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage extends BaseTest {

    @FindBy(xpath = "//*[@class = 'title']")
    WebElement checkoutOverviewlabel;

    @FindBy(xpath = "//*[@id='checkout_summary_container']/div/div[2]/div[6]")
    WebElement subtotal;


    @FindBy(xpath = "(//*[@class = 'summary_info_label'])[1]")
    WebElement paymentInfoLbl;

    @FindBy(xpath = "(//*[@class = 'summary_value_label'])[1]")
    WebElement paymentInfo;

    @FindBy(xpath = "(//*[@class = 'summary_info_label'])[2]")
    WebElement shippingInfoLbl;
    @FindBy(xpath = "(//*[@class = 'summary_value_label'])[2]")
    WebElement shippingInfo;

    @FindBy(xpath = "(//*[@class = 'summary_info_label'])[3]")
    WebElement priceTotalLbl;
    @FindBy(xpath = "//*[@class = 'summary_subtotal_label']")
    WebElement itemTotal;

    @FindBy(xpath = "//*[@class = 'summary_tax_label']")
    WebElement taxTotal;

    @FindBy(xpath = "//*[@class = 'summary_info_label summary_total_label']")
    WebElement totalWithTax;


    @FindBy(xpath = "//button[@class = 'btn btn_secondary back btn_medium cart_cancel_link']")
    WebElement cancelBtn;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement completeHeaderLabel;

    LoginPage login;
    HomePage home;
    CartPage cart;
    CheckoutInformationPage checkoutInfo;

    public CheckoutOverviewPage() {
        PageFactory.initElements(driver, this);
    }

    public String getCheckoutInfoPageTitle() {
        return checkoutOverviewlabel.getText();
    }

    public double getTotal() {
        return Double.parseDouble(totalWithTax.getText().substring(13));
    }

    public void clickOnFinishButton() {
        finishButton.click();
    }

    public void clickOnCancelButton() {
        cancelBtn.click();
    }
}
