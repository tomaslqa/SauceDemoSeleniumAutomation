package com.saucelab.qa.pages;

import com.saucelab.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage extends BaseTest {

    @FindBy(xpath = "//*[@class = 'title']")
    WebElement checkoutInfolabel;

    @FindBy(xpath = "//*[@id='first-name']")
    WebElement firstNameTextBox;
    @FindBy(xpath = "//*[@id='last-name']")
    WebElement lastNameTextBox;
    @FindBy(xpath = "//*[@id='postal-code']")
    WebElement postalCodeTextBox;
    @FindBy(xpath = "//*[@id='continue']")
    WebElement continueButton;

    public CheckoutInformationPage() {
        PageFactory.initElements(driver, this);
    }

    public String getcheckoutInfoPageTitle() {
        return checkoutInfolabel.getText();
    }

    public void enterFirstName(String firstName) {
        firstNameTextBox.sendKeys(firstName);
    }

    public void enterLastName(String surname) {
        lastNameTextBox.sendKeys(surname);
    }

    public void enterPostalCode(String zipcode) {
        postalCodeTextBox.sendKeys(zipcode);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void enterInfoAndClickContinueButton(String name, String surname, String zipcode) {
        enterFirstName(name);
        enterLastName(surname);
        enterPostalCode(zipcode);
        continueButton.click();
    }


}
