package main.tests;

import org.openqa.selenium.WebDriver;

import main.base.TestBase;
import main.pages.BannerPage;
import main.pages.MainPage;
import main.pages.SearchResultsPage;

public class Tests extends TestBase {

	private WebDriver driver;
	private MainPage mainPage;
	private BannerPage bannerPage;
	private SearchResultsPage searchResultsPage;

	public Tests(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyStock() {
		String searchProduct = "ipod";
		int pageNumber = 2;

		bannerPage = new BannerPage(driver);
		bannerPage.closeBannerIfDispleyd();

		mainPage = new MainPage(driver);
		verifyPageLoaded(mainPage);

		mainPage.setSearchField(searchProduct);
		mainPage.clickSearchButton();

		bannerPage.closeBannerIfDispleyd();

		searchResultsPage = new SearchResultsPage(driver);
		verifyPageLoaded(searchResultsPage);
		searchResultsPage.scrollPageToBottom();
		searchResultsPage.setGoToPageField(pageNumber);
		searchResultsPage.clickGoToPageButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

}
