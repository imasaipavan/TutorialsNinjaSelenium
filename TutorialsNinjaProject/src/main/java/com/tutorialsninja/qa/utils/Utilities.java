package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	public static Object[][] getDataFromExcel(String sheetName) throws IOException {

		File file = new File("./src/main/java/com/tutorialsninja/qa/testdata/TestData.xlsx");
		FileInputStream excelfile = new FileInputStream(file);
		workbook = new XSSFWorkbook(excelfile);
		System.out.println(workbook.getSheet(sheetName));
		sheet= workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object [][] data = new Object[rows][cols];

		for(int i=1;i<rows;i++) {

			XSSFRow row = sheet.getRow(i+1);

			for(int j=0;j<cols;j++) {

				XSSFCell cell = row.getCell(j);

				CellType cellType = cell.getCellType();

				switch(cellType) {

				case STRING:
					data[i][j]= cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]= Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]= cell.getBooleanCellValue();
					break;
				}
				System.out.println(data);

			}

		}

		return data;
	}
	
	public static Object[][] dataProviderMethod() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsninja/qa"
				+ "/testdata/TestData.xlsx");
        System.out.println(file);
        FileInputStream inputStream = new FileInputStream(file);
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
	
	public static String generateScreenshot(String testName, WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"/Screenshots/"+testName+".png";
				try {
					FileUtils.copyFile(src, new File(dest));
					org.openqa.selenium.io.FileHandler.copy(src, new File(dest));
		
				} catch (IOException e) {
					e.printStackTrace();
				}
				return dest;
	}

}
