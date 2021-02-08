package kontrolni3;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Registracija {
	


	public static void inputUserName(WebDriver driver, String username) {
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
	}

	public static void inputPassword(WebDriver driver, String password) {
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);;
	}

	public static void login(WebDriver driver) {
		driver.findElement(By.id("login-button")).click();
	}
	
	
}
