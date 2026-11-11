package org.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {
	public static WebDriver driver;
	
	//launch setup
	public void setup(String browser,String url) {
		switch (browser.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		default:System.out.println("Invalid browser name...");
			break;	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().window().maximize();
	}

	// excel data
	public String readExcel(String sheetName,int rowNum,int cellNum) throws IOException {
		File file=new File("ExcelData\\swaglabs_data.xlsx");
		FileInputStream stream=new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(cellNum);
		CellType cellType = cell.getCellType();
		
		String value="";
		switch (cellType) {
		case STRING:
			value = cell.getStringCellValue();
			break;
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat SimpleDateFormat=new SimpleDateFormat("dd//MM//yyy");
				value= SimpleDateFormat.format(dateCellValue);
			}else {
				double numericCellValue = cell.getNumericCellValue();
				BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
				value = valueOf.toString();
			}
			break;
			default:System.out.println("Invalid cell Data...");
			break;
		}
		return value;
	}
	
	//sendkeys Method
	public void getSendKeys(WebElement element,String keysToSend) {
		element.sendKeys(keysToSend);
	}
	
	//click method
	public void performClick(WebElement element) {
		element.click();
	}
	
	//getText method
	public String captureText(WebElement element) {
		String text = element.getText();
		return text;
	}
	
}












