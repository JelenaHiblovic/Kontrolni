package kontrolni3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Testovi {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jelenin Dellic\\chrome driver\\ChromeDriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void testLogin() {

		File f = new File("data.xlsx");

		try {
			InputStream inp = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			SoftAssert sa = new SoftAssert();

			for (int i = 0; i < 3; i++) {
				Row row = sheet.getRow(i);
				Cell c1 = row.getCell(0);
				Cell c2 = row.getCell(1);

				String username = c1.toString();
				String password = c2.toString();

				driver.navigate().to(Home.URL);
				Registracija.inputUserName(driver, username);
				Registracija.inputPassword(driver, password);
				Registracija.login(driver);
				
				System.out.println(driver.getCurrentUrl());
			
				String actual = driver.getCurrentUrl();
				String expected = "https://www.saucedemo.com/inventory.html";

				sa.assertEquals(actual, expected);
			}
			
			sa.assertAll();

			wb.close();
		} catch (IOException e) {
			System.out.println("Nije pronadjen fajl!");
			e.printStackTrace();
		}

	}

	@Test
	public void testNepravilanLogin() {
		File f = new File("data.xlsx");

		try {
			InputStream inp = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(6);
			Cell c1 = row.getCell(0);
			Cell c2 = row.getCell(1);
			
			String username = c1.toString();
			String password = c2.toString();
			
			driver.navigate().to(Home.URL);
			Registracija.inputUserName(driver, username);
			Registracija.inputPassword(driver, password);
			Registracija.login(driver);
			
			String actual = driver.getCurrentUrl();
			String expected = "https://www.saucedemo.com/";

			Assert.assertEquals(actual, expected);
			
		} catch (IOException e) {
			System.out.println("Nije pronadjen fajl!");
			e.printStackTrace();
		}
		
	}
	@Test
	public void testInventory() {
		
		driver.navigate().to(Home.SUCC_URL);
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_filter_container\"]/select")));
		
		
		Select drop = new Select(element);
		drop.selectByVisibleText("Price (low to high)");
		
		List<WebElement> img = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/div"));
		System.out.println(img.size());
       
        for (int i = 0; i<img.size(); i=i+1) 
        {
        System.out.println(img.get(i).getText());          
        }           
	}
}
