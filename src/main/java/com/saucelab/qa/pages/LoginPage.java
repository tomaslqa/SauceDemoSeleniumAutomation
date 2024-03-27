package com.saucelab.qa.pages;

import com.saucelab.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    //Page Factory
    @FindBy(name = "user-name")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(name = "login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement loginErrorMessage;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    public void enterUserName(String userName) {
        username.sendKeys(userName);
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickOnLoginButton() {
        loginBtn.click();
    }

    public void login(String userName, String pwd) {
        enterUserName(userName);
        enterPassword(pwd);
        clickOnLoginButton();
    }

    public boolean isErrorTextDisplayed(String error) {
        String actualErrorMessage = loginErrorMessage.getText();
        if (error.equalsIgnoreCase(actualErrorMessage)) {
            return true;
        } else {
            return false;
        }
    }

    public String getErrorText() {
        String errorMessage = loginErrorMessage.getText();
        return errorMessage;
    }

}
