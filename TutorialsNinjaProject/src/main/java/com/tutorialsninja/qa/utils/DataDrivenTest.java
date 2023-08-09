package com.tutorialsninja.qa.utils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class DataDrivenTest {

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        // Your Selenium code for login test
        // Replace this with your actual test logic
        System.out.println("Logging in with username: " + username + " and password: " + password);
    }

    @DataProvider(name = "loginData")
    public Object[][] dataProviderMethod() throws IOException {
        String excelFile = "./src/main/java/com/tutorialsninja/qa/testdata/TestData.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                DataFormatter dataFormatter = new DataFormatter();
                String cellValue = dataFormatter.formatCellValue(cell);
                data[i][j] = cellValue;
            }
        }

        workbook.close();
        inputStream.close();
        return data;
    }
}