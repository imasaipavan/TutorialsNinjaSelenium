package com.tutorialsninja.qa.dataprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.InvalidArgumentException;

public class cxplite_dataprovider {
	//public static String TESTDATA_SHEET_PATH = "D:/cxpdatadata.xls";

	public static String TESTDATA_SHEET_PATH = "./src/main/java/com/tutorialsninja/qa/testdata/TestData.xlsx";
	static Workbook book;
	static Sheet sheet;



	public static Object[][] getTestData(String sheetName) throws FileNotFoundException 
	{
		FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH);
		System.out.println(book.getSheetName(0));
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++)
		{
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) 
			{
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();			
			}
		}
		return data;
	}

	public void setDataEventNumber(String sheetName, int row, int column, String eventNumber) throws IOException {
		try {

			book.getSheet(sheetName).createRow(row).createCell(column).setCellValue(eventNumber);
			FileOutputStream fout = new FileOutputStream(TESTDATA_SHEET_PATH);
			book.write(fout);
			fout.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
