package edu.uiowa.ars.test;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class TestSupport {

	public static boolean verifyProperty(final Properties propList, final String prop, final String receivedVal) {
		if (!propList.containsKey(prop)) {
			throw new IllegalStateException("Poperty list does not contain property: " + prop);
		}
		return propList.getProperty(prop).equals(receivedVal);
	}

	public static List<WebElement> getElementsById(final WebDriver driver, final String elementId) {
		// Wait until the element is available.
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
		return driver.findElements(By.id(elementId));
	}

	public static WebElement getElementById(final WebDriver driver, final String elementId) {
		final List<WebElement> result = getElementsById(driver, elementId);
		if (result.size() == 1) {
			return result.get(0);
		} else {
			throw new IllegalStateException("Could not extract element with id: " + elementId + " " + result.size());
		}
	}
}
