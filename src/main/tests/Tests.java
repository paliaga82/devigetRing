package main.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import main.base.TestBase;
import main.pages.BannerPage;
import main.pages.MainPage;
import main.pages.ProductPage;
import main.pages.SearchResultsPage;

public class Tests extends TestBase {

	private WebDriver driver;
	private MainPage mainPage;
	private BannerPage bannerPage;
	private SearchResultsPage searchResultsPage;
	private ProductPage productPage;

	public Tests(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyStock() {
		String searchProduct = "ipod";
		int pageNumber = 2;
		int elementFromList = 2;

		bannerPage = new BannerPage(driver);
		bannerPage.closeBannerIfDispleyd();

		mainPage = new MainPage(driver);
		verifyPageLoaded(mainPage);

		mainPage.setSearchField(searchProduct);
		mainPage.clickSearchButton();

		bannerPage.closeBannerIfDispleyd();

		searchResultsPage = new SearchResultsPage(driver);
		verifyPageLoaded(searchResultsPage);
		searchResultsPage.goToPage(pageNumber);
		searchResultsPage.clickProductFromList(elementFromList);
		productPage = new ProductPage(driver);
		verifyPageLoaded(productPage);
		int stockAvailable = productPage.getStockAvailable();
		writeCustomMessageInReport("Stock available: " + stockAvailable);
		Assert.assertTrue(stockAvailable > 0, "No Stock available.");
	}

}
