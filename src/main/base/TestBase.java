package main.base;

import org.testng.Assert;

import main.Report;

public class TestBase extends Report {

	private final String PAGE_NOT_DISPLAYED_MESSAGE = "Page [%s] not displayed.";

	//---------------------------------------------------------------------------------------------

	public String pageNotDisplayed(String pageName) {
		return String.format(PAGE_NOT_DISPLAYED_MESSAGE, pageName);
	}

	public void verifyPageLoaded(PageBase page) {
		Assert.assertTrue(page.isPageDsiplayed(), pageNotDisplayed(page.getPageName()));
		writeCustomMessageInReport(String.format("Page [%s] displayed correctly.", page.getPageName()));
	}
}
