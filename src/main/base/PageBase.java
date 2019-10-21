package main.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.Report;

public abstract class PageBase extends Report {

	//---------------------------------------------------------------------------------------------
	// Constants
	//---------------------------------------------------------------------------------------------
	private final boolean WRITE_DETAILED_LOG = true;
	private final long MAX_WAIT_FOR_ELEMENTS = 10;

	//---------------------------------------------------------------------------------------------

	protected WebDriver driver;
	private WebDriverWait wait;

	public PageBase(WebDriver driver) {
		setDriver(driver);
		setWait(MAX_WAIT_FOR_ELEMENTS);
		this.waitPageToLoad();
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	protected void setWait(long waitingTimeInSeconds) {
		wait = new WebDriverWait(getDriver(), waitingTimeInSeconds);
	}

	public WebDriverWait getWait() {
		return wait;
	}

	//---------------------------------------------------------------------------------------------
	// Page operations
	//---------------------------------------------------------------------------------------------
	public abstract boolean isPageDsiplayed();

	public abstract void waitPageToLoad();

	public void waitPageToLoad(By locator) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	//---------------------------------------------------------------------------------------------
	// Element operations
	//---------------------------------------------------------------------------------------------
	public WebElement findElement(By locator) {
		return getDriver().findElement(locator);
	}

	public boolean isElementPresent(By locator) {
		try {
			findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Object jsExec(WebElement ele, String js) {
		Object jsResult = "";
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			if(ele != null) {
				jsResult = jse.executeScript(js, ele);
			} else {
				jsResult = jse.executeScript(js);
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Error executing javascript, stale element reference.");
		} catch (Exception e) {
			System.out.println("Error executing javascript.");
		}
		return jsResult;
	}

	public void scrollIntoView(WebElement element) {
		jsExec(element, "arguments[0].scrollIntoView({ behavior: 'auto', block: 'center',inline: 'center'});");
	}

	public WebElement clickElement(WebElement element) {
		if (WRITE_DETAILED_LOG) {
			writeInReport("Clicking element [%s].", element.toString());
		}
		scrollIntoView(element);
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
		if (WRITE_DETAILED_LOG) {
			writeInReport("Writing [%s] in element [%s].", value, element.toString());
		}
		scrollIntoView(element);
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
		scrollIntoView(element);
		return element.getText();
	}

	public String getFieldValue(By locator) {
		WebElement element = findElement(locator);
		return getFieldValue(element);
	}
}
