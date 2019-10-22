package main.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.base.PageBase;

public class SearchResultsPage extends PageBase {

	//---------------------------------------------------------------------------------------------
	// Locator
	//---------------------------------------------------------------------------------------------
	private final String SEARCH_RESULTS_BODY_LOCATOR_CSS = "body[data-spm='productlist']";
	private final String SEARCH_RESULTS_LIST_LOCATOR_CSS = "div.list-wrap.product-list li a.item-title";

	private final String SEARCH_RESULTS_BUTTON_PREVIOUS_PAGE_LOCATOR_CSS = "button.next-btn.next-medium.next-btn-normal.next-pagination-item.next-prev";
	private final String SEARCH_RESULTS_BUTTON_PAGE_NUMBER_LOCATOR_CSS   = "div.next-pagination-list button";
	private final String SEARCH_RESULTS_BUTTON_NEXT_PAGE_LOCATOR_CSS     = "button.next-btn.next-medium.next-btn-normal.next-pagination-item.next-next";

	private final String SEARCH_RESULTS_INPUT_GO_TO_PAGE_LOCATOR_CSS  = "div.jump-aera input";
	private final String SEARCH_RESULTS_BUTTON_GO_TO_PAGE_LOCATOR_CSS = "div.jump-aera span.jump-btn";

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

	public void clickProductFromList(int item) {
		scrollPageToBottom(); // to ensure dynamically created elements were created.
		List<WebElement> productList = findElements(By.cssSelector(SEARCH_RESULTS_LIST_LOCATOR_CSS));
		clickElement(productList.get(item - 1));
	}

	public void clickPreviousPageButton() {
		clickElement(By.cssSelector(SEARCH_RESULTS_BUTTON_PREVIOUS_PAGE_LOCATOR_CSS));
	}

	public void clickPageButton(int pageNumber) {
		List<WebElement> pages = findElements(By.cssSelector(SEARCH_RESULTS_BUTTON_PAGE_NUMBER_LOCATOR_CSS));
		clickElement(pages.get(pageNumber - 1));
	}

	public void clickNextPageButton() {
		clickElement(By.cssSelector(SEARCH_RESULTS_BUTTON_NEXT_PAGE_LOCATOR_CSS));
	}

	public void setGoToPageField(String value) {
		completeField(By.cssSelector(SEARCH_RESULTS_INPUT_GO_TO_PAGE_LOCATOR_CSS), value);
	}

	public void setGoToPageField(int value) {
		setGoToPageField(String.valueOf(value));
	}

	public void clickGoToPageButton() {
		clickElement(By.cssSelector(SEARCH_RESULTS_BUTTON_GO_TO_PAGE_LOCATOR_CSS));
	}

	public void scrollPageToBottom() {
		int step = 250;
		int pageHeight = Integer.valueOf(jsExec(null, "return document.body.scrollHeight;").toString());
		int pos = 0;

		while (pos <= pageHeight) {
			pos += step;
			jsExec(null, String.format("window.scrollBy(0, %d);", step));
		}
	}

	//---------------------------------------------------------------------------------------------
	// Complex actions
	//---------------------------------------------------------------------------------------------
	public void goToPage(int pageNumber) {
		scrollPageToBottom();
		setGoToPageField(pageNumber);
		clickGoToPageButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
}
