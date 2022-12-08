package com.myntra.stepdefenition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	
	public static WebDriver driver;
	public static long stime;
	@BeforeClass
	public void browserLaunch() 
	{
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		String url = "https://www.myntra.com/";
		driver.get(url);
	}
	@AfterClass
	public void closeValidation() 
	{ 
		//driver.quit();
		System.out.println("validation close");
		
	}
	
	@BeforeTest
	public void startingTime() 
	{
		 stime = System.currentTimeMillis();
		System.out.println(stime);
		
	}
	@AfterTest
	public void endTime() 
	{
		long etime = System.currentTimeMillis();
		System.out.println("running time of method:"+ (stime-etime));
	}
	@Test(priority = 1)
	public void men() 
	{
		WebElement men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		WebElement jeans = driver.findElement(By.xpath("//li[@data-reactid='63']"));
		Actions a= new Actions(driver);
		a.moveToElement(men).perform();
		a.moveToElement(jeans).click().build().perform();
		
	}	
	     
	@Test(priority = 2,enabled = true)
	public void jeansModule() throws Exception 
    {	
		Thread.sleep(3000);
		List<WebElement> highlander = driver.findElements(By.xpath("//*[text()='HIGHLANDER']"));
		File f = new File("C:\\Users\\Admin\\eclipse-workspace\\MyntraValidation\\datas\\myntrajeans.xlsx");
			Workbook b = new XSSFWorkbook();
		      Sheet s = b.createSheet("jeans");
	     for(int i=0; i<highlander.size();i++) {
			WebElement product = highlander.get(i);
			String jhd = product.getText();
		System.out.println(jhd);
		Row r = s.createRow(i);
		Cell c = r.createCell(0);
			c.setCellValue(jhd);
			FileOutputStream op = new FileOutputStream(f);
	         b.write(op);
	}
}
}
