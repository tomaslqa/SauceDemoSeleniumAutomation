package com.saucelab.qa.base;

import com.saucelab.qa.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;

    public static Properties prop;

    public BaseTest() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    "/Testing/SauceDemoAutomation/src/main/java/com/saucelab/qa/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void initialization() {
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver-win64/chromedriver.exe");
            driver = new ChromeDriver();
        } if (browserName.equals("edge")) {
            System.setProperty("webdriver.edge.driver", "/drivers/edgedriver_win64/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/drivers/geckodriver-v0.34.0-win64/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    @AfterTest
    public void teardown() {

        driver.quit();

    }
}
