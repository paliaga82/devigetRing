package main.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class PageBase {

	protected WebDriver driver;

	public PageBase(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	//---------------------------------------------------------------------------------------------
	// Page operations
	//---------------------------------------------------------------------------------------------
	public abstract boolean isPageDsiplayed();

	public abstract void waitPageToLoad();

	//---------------------------------------------------------------------------------------------
	// Element operations
	//---------------------------------------------------------------------------------------------
	public WebElement findElement(By locator) {
		return getDriver().findElement(locator);
	}

	public WebElement clickElement(WebElement element) {
		try {
			element.click();
		} catch(Exception e) {
			System.out.println(String.format("Click element exception, not able to click [%s].", element.toString()));
		}
		return element;
	}

	public WebElement clickElement(By locator) {
		WebElement element = findElement(locator);
		return clickElement(element);
	}

	public WebElement completeField(WebElement element, String value, boolean clearField) {
		if (clearField) {
			element.clear();
		}
		element.sendKeys(value);
		return element;
	}

	public WebElement completeField(By locator, String value, boolean clearField) {
		WebElement element = findElement(locator);
		return completeField(element, value, clearField);
	}

	public WebElement completeField(By locator, String value) {
		WebElement element = findElement(locator);
		return completeField(element, value, true);
	}

	public String getFieldValue(WebElement element) {
		return element.getText();
	}

	public String getFieldValue(By locator) {
		WebElement element = findElement(locator);
		return getFieldValue(element);
	}
}
