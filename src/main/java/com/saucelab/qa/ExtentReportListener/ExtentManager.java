package com.saucelab.qa.ExtentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucelab.qa.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ExtentManager extends BaseTest {

    private static ExtentReports extent;
    public static String screenshotName;

    public static ExtentReports createInstance(String fileName) {

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);


        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS:", System.getProperty("os.name"));
        extent.setSystemInfo("Java version:", System.getProperty("java.version"));
       /* extent.setSystemInfo("Browser Name:", TestUtil.getBrowser());
        extent.setSystemInfo("Browser Version:",TestUtil.getVersion());*/
        extent.setSystemInfo("User:", System.getProperty("user.name"));

        return extent;
    }


    public static void captureScreenshot() {

        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Call method to capture screenshot
        File src = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            Date d = new Date();
            screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
            FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
            System.out.println("Successfully captured a screenshot");
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }
}