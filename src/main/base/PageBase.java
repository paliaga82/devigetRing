package main.base;

import java.util.List;

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
	private String pageName;

	public PageBase(WebDriver driver, String pageName) {
		setDriver(driver);
		setWait(MAX_WAIT_FOR_ELEMENTS);
		setPageName(pageName);
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

	private void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void waitTabToOpen() {
		int attemps = 0;
		WebDriver wd = getDriver();
		while ((wd.getWindowHandles().size() == 1) && attemps <= MAX_WAIT_FOR_ELEMENTS) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			attemps++;
		}
	}

	public void switchTab() {
		for (String window : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(window);
		}
	}

	//---------------------------------------------------------------------------------------------
	// Element operations
	//---------------------------------------------------------------------------------------------
	public WebElement findElement(By locator) {
		return getDriver().findElement(locator);
	}

	public List<WebElement> findElements(By locator) {
		return getDriver().findElements(locator);
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
			writeInReport("Error executing javascript, stale element reference.");
		} catch (Exception e) {
			writeInReport("Error executing javascript.");
		}
		return jsResult;
	}

	public void scrollIntoView(WebElement element) {
		jsExec(element, "arguments[0].scrollIntoView({ behavior: 'auto', block: 'center',inline: 'center'});");
	}

	public WebElement clickElement(WebElement element) {
		scrollIntoView(element);
		try {
			element.click();
		} catch(Exception e) {
			writeInReport("Click element exception, not able to click [%s].", element.toString());
		}
		return element;
	}

	public WebElement clickElement(By locator) {
		if (WRITE_DETAILED_LOG) {
			writeInReport("Clicking element [%s] at [%s] page.", locator.toString(), getPageName());
		}
		WebElement element = findElement(locator);
		return clickElement(element);
	}

	public WebElement completeField(WebElement element, String value, boolean clearField) {
		scrollIntoView(element);
		if (clearField) {
			element.clear();
		}
		element.sendKeys(value);
		return element;
	}

	public WebElement completeField(By locator, String value, boolean clearField) {
		if (WRITE_DETAILED_LOG) {
			writeInReport("Writing [%s] in element [%s] at [%s] page.", value, locator.toString(), getPageName());
		}
		WebElement element = findElement(locator);
		return completeField(element, value, clearField);
	}

	public WebElement completeField(By locator, String value) {
		return completeField(locator, value, true);
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
