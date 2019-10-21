package test;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.pages.BannerPage;
import main.pages.MainPage;

public class ExecutionTest {

	private WebDriver driver;
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
	}

	@Test
	public void smokeTest() {
		System.out.println(">>> The test has started.");
		MainPage mainPage = new MainPage(driver);
		BannerPage bannerPage = new BannerPage(driver);
		if (bannerPage.isPageDsiplayed()) {
			bannerPage.clickCloseButton();
		}
		mainPage.setSearchField("ipod");
		mainPage.clickSearchButton();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
	}
}
