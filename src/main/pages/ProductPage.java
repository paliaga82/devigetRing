package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.base.PageBase;

public class ProductPage extends PageBase {

	//---------------------------------------------------------------------------------------------
	// Locator
	//---------------------------------------------------------------------------------------------
	private final String PRODUCT_INFO_LOCATOR_CSS               = "div.product-info";
	private final String PRODUCT_LABEL_AVAILABILITY_LOCATOR_CSS = "div.product-info div.product-quantity-tip span";

	//---------------------------------------------------------------------------------------------
	// Actions
	//---------------------------------------------------------------------------------------------
	public ProductPage(WebDriver driver) {
		super(driver, "Product");
	}

	@Override
	public boolean isPageDsiplayed() {
		return isElementPresent(By.cssSelector(PRODUCT_INFO_LOCATOR_CSS));
	}

	@Override
	public void waitPageToLoad() {
		waitTabToOpen();
		switchTab();
		waitPageToLoad(By.cssSelector(PRODUCT_INFO_LOCATOR_CSS));
	}

	public String getStockAvailable() {
		return getFieldValue(By.cssSelector(PRODUCT_LABEL_AVAILABILITY_LOCATOR_CSS));
	}

}
