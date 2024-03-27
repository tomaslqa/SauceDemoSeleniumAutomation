package com.saucelab.qa.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.saucelab.qa.base.BaseTest.driver;

public class TestUtil {

    public static long PAGE_LOAD_TIMEOUT = 220;
    public static long IMPLICIT_WAIT = 10;

    public static String TESTDATA_SHEET_PATH = "/Testing/SauceDemoAutomation/src/main/java/com/saucelab/qa/testdata/SauceDemoTestData.xlsx";

    public static Object[][] getTestData(String sheetName) {

        FileInputStream file = null;
        Workbook workbook = null;
        Sheet sheet = null;

        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i+1).getCell(k).toString();
            }
        }
        return data;
    }


    public static String getBrowser() {
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        return browserName;
    }

    public static String getVersion() {
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        String version = cap.getBrowserVersion().toString();
        return version;
    }
}




