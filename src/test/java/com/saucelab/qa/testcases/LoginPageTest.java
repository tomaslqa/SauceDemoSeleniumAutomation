package com.saucelab.qa.testcases;

import com.saucelab.qa.base.BaseTest;
import com.saucelab.qa.pages.LoginPage;
import com.saucelab.qa.util.TestStrings;
import com.saucelab.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    LoginPage login;
    String sheetName1 = "correctlogindata";
    String sheetName2 = "incorrectlogindata";
    String sheetName3 = "lockedoutdata";


    public LoginPageTest() {

        super();
    }


    @BeforeMethod
    public void setUp() {
        initialization();
        login = new LoginPage();

    }

    @DataProvider
    public Object[][] getData() {
        Object data[][] = TestUtil.getTestData(sheetName1);
        return data;
    }

    @DataProvider
    public Object[][] getIncorrectData() {
        Object data[][] = TestUtil.getTestData(sheetName2);
        return data;
    }

    @DataProvider
    public Object[][] getLockedData() {
        Object data[][] = TestUtil.getTestData(sheetName3);
        return data;
    }

    @Test
    public void loginPageTitleTest() {
        String title = login.pageTitle();
        Assert.assertEquals(title, TestStrings.LOGIN_TITLE, "Title Mismatch");
        Reporter.log("Verified login page title", true);
    }

    @Test(dataProvider = "getData")
    public void loginTestWithCorrectCredentials(String user, String password) {
        login.login(user, password);
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.HOME_URL, "Url mismatch");
        Reporter.log("Verified login is successful with correct credentials, user proceeds to Home page", true);
    }

    @Test(dataProvider = "getIncorrectData")
    public void loginTestWithInCorrectCredentials(String user, String password) {
        login.login(user, password);
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.LOGIN_URL, "Url mismatch");
        Assert.assertEquals(login.isErrorTextDisplayed(TestStrings.INCORRECT_USER), true, "Error not present");
        Assert.assertEquals(TestStrings.INCORRECT_USER, login.getErrorText(), "Error messsage text mismatch");
        System.out.println(login.getErrorText());
        Reporter.log("Verified login is unsuccessful with incorrect credentials, user stays on Login page", true);
        Reporter.log("Verified error message is displayed", true);
    }

    @Test(dataProvider = "getLockedData")
    public void loginTestWithLockedUserCredentials(String user, String password) {
        login.login(user, password);
        Assert.assertEquals(driver.getCurrentUrl(), TestStrings.LOGIN_URL, "Url mismatch");
        Assert.assertEquals(login.isErrorTextDisplayed(TestStrings.LOCKED_USER), true, "Error not present");
        Assert.assertEquals(TestStrings.LOCKED_USER, login.getErrorText(), "Error messsage text mismatch");
        System.out.println(login.getErrorText());
        Reporter.log("Verified login is unsuccessful with incorrect credentials, user stays on Login page", true);
        Reporter.log("Verified error message is displayed", true);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

