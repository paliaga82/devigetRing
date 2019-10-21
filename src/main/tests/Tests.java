package main.tests;

import org.openqa.selenium.WebDriver;

import main.base.TestBase;
import main.pages.BannerPage;
import main.pages.MainPage;

public class Tests extends TestBase {

	private WebDriver driver;
	private MainPage mainPage;
	private BannerPage bannerPage;

	public Tests(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyStock() {
		String searchProduct = "ipod";

		bannerPage = new BannerPage(driver);
		bannerPage.closeBannerIfDispleyd();

		mainPage = new MainPage(driver);
		verifyPageLoaded(mainPage);
		mainPage.setSearchField(searchProduct);
		mainPage.clickSearchButton();

		bannerPage.closeBannerIfDispleyd();
	}

}
