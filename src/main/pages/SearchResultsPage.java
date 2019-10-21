package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.base.PageBase;

public class SearchResultsPage extends PageBase {

	//---------------------------------------------------------------------------------------------
	// Locator
	//---------------------------------------------------------------------------------------------
	private final String SEARCH_RESULTS_BODY_LOCATOR_CSS = "body[data-spm='productlist']";
	private final String SEARCH_RESULTS_LIST_LOCATOR_CSS = "div.list-wrap.product-list";

	private final String SEARCH_RESULTS_BUTTON_PREVIOUS_PAGE_LOCATOR_CSS = "button.next-btn.next-medium.next-btn-normal.next-pagination-item.next-prev";
	private final String SEARCH_RESULTS_BUTTON_NEXT_PAGE_LOCATOR_CSS     = "button.next-btn.next-medium.next-btn-normal.next-pagination-item.next-next";

	//---------------------------------------------------------------------------------------------
	// Actions
	//---------------------------------------------------------------------------------------------
	public SearchResultsPage(WebDriver driver) {
		super(driver, "Search Results");
	}

	@Override
	public boolean isPageDsiplayed() {
		return isElementPresent(By.cssSelector(SEARCH_RESULTS_BODY_LOCATOR_CSS));
	}

	@Override
	public void waitPageToLoad() {
		waitPageToLoad(By.cssSelector(SEARCH_RESULTS_LIST_LOCATOR_CSS));
	}

	public void clickPreviousPageButton() {
		clickElement(By.cssSelector(SEARCH_RESULTS_BUTTON_PREVIOUS_PAGE_LOCATOR_CSS));
	}

	public void clickNextPageButton() {
		clickElement(By.cssSelector(SEARCH_RESULTS_BUTTON_NEXT_PAGE_LOCATOR_CSS));
	}

	public void scrollPageToBottom() {
		jsExec(null, "window.scrollTo(0, document.body.scrollHeight)");
	}
}
