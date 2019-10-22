package test;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.tests.Tests;

public class ExecutionTest {

	private WebDriver driver;
	Tests test;
	private String url = "http://www.aliexpress.com";

	private String getWebDriverPath() {
		return System.getProperty("user.dir").concat(File.separator).concat("webDriver").concat(File.separator).concat("chromedriver.exe");
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", getWebDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		test = new Tests(driver);
	}

	@Test
	public void codeChallenge() {
		String searchProduct = "iphone";
		int pageNumber = 2;
		int elementFromList = 2;

		test.verifyStock(searchProduct, pageNumber, elementFromList);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
	}
}
