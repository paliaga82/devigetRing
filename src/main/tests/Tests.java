package main.tests;

import org.openqa.selenium.WebDriver;

import main.pages.BannerPage;
import main.pages.MainPage;

public class Tests {

	private WebDriver driver;
	private MainPage mainPage;
	private BannerPage bannerPage;

	public Tests(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyStock() {
		String searchProduct = "ipod";

		bannerPage = new BannerPage(driver);

		if (bannerPage.isPageDsiplayed()) {
			bannerPage.clickCloseButton();
		}
		mainPage = new MainPage(driver);
		mainPage.setSearchField(searchProduct);
		mainPage.clickSearchButton();
	}

}
