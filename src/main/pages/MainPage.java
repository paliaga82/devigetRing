package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.base.PageBase;

public class MainPage extends PageBase {

	//---------------------------------------------------------------------------------------------
	// Locator
	//---------------------------------------------------------------------------------------------
	private final String MAIN_PAGE_BODY_LOCATOR_CSS = "body[data-spm='home']";

	private final String MAIN_PAGE_INPUT_SEARCH_LOCATOR_CSS = "input#search-key";
	private final String MAIN_PAGE_BUTTON_SEARCH_LOCATOR_CSS = "form#form-searchbar input.search-button";

	//---------------------------------------------------------------------------------------------
	// Actions
	//---------------------------------------------------------------------------------------------
	public MainPage(WebDriver driver) {
		super(driver, "Main");
	}

	@Override
	public boolean isPageDsiplayed() {
		return isElementPresent(By.cssSelector(MAIN_PAGE_BODY_LOCATOR_CSS));
	}

	@Override
	public void waitPageToLoad() {
		waitPageToLoad(By.cssSelector(MAIN_PAGE_INPUT_SEARCH_LOCATOR_CSS));
	}

	public void setSearchField(String value) {
		completeField(By.cssSelector(MAIN_PAGE_INPUT_SEARCH_LOCATOR_CSS), value);
	}

	public void clickSearchButton() {
		clickElement(By.cssSelector(MAIN_PAGE_BUTTON_SEARCH_LOCATOR_CSS));
	}

}
