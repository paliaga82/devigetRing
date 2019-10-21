package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.base.PageBase;

public class BannerPage extends PageBase {

	//---------------------------------------------------------------------------------------------
	// Locator
	//---------------------------------------------------------------------------------------------
	private final String BANNER_WINDOW_LOCATOR_CSS = "div.ui-window.ui-window-normal.ui-window-transition.ui-newuser-layer-dialog";
	private final String BANNER_BUTTON_CLOSE_LOCATOR_CSS = "a[data-role='layer-close']";

	//---------------------------------------------------------------------------------------------
	// Actions
	//---------------------------------------------------------------------------------------------
	public BannerPage(WebDriver driver) {
		super(driver);
		setPageName("Banner");
	}

	@Override
	public boolean isPageDsiplayed() {
		return isElementPresent(By.cssSelector(BANNER_WINDOW_LOCATOR_CSS));
	}

	@Override
	public void waitPageToLoad() {
		// No waiting for banner to load as waiting is done by the page.
	}

	public void clickCloseButton() {
		clickElement(By.cssSelector(BANNER_BUTTON_CLOSE_LOCATOR_CSS));
	}

	//---------------------------------------------------------------------------------------------
	// Complex actions
	//---------------------------------------------------------------------------------------------
	public void closeBannerIfDispleyd() {
		if (isPageDsiplayed()) {
			clickCloseButton();
		}
	}
}
