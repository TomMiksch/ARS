package edu.uiowa.ars.test;

import java.lang.reflect.Constructor;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest {

	@Test
	public void testLogin() throws Exception {

		WebDriver driver = null;
		try {
			// Test this functionality for all browsers in the map.
			for (final BrowserEntry browserEntry : BrowserEntry.values()) {
				final String driverProp = browserEntry.getDriverProp();

				if (System.getProperty(driverProp) != null) {
					System.out.println("Testing login for browser with driver " + browserEntry.getDriverClass());
					// objects and variables instantiation
					final Constructor<?> ctor = browserEntry.getDriverClass().getConstructor();
					driver = (WebDriver) ctor.newInstance();
					String appUrl = "https://accounts.google.com";

					// launch the browser and open the application url
					driver.get(appUrl);

					// maximize the browser window
					driver.manage().window().maximize();

					// declare and initialize the variable to store the expected
					// title of
					// the webpage.
					String expectedTitle = "Sign in - Google Accounts";

					// fetch the title of the web page and save it into a string
					// variable
					String actualTitle = driver.getTitle();

					// compare the expected title of the page with the actual
					// title
					// of the
					// page and print the result
					if (expectedTitle.equals(actualTitle)) {
						System.out.println("Verification Successful - The correct title is displayed on the web page.");
					} else {
						System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
					}

					// enter a valid username in the email textbox
					WebElement username = driver.findElement(By.id("Email"));
					username.clear();
					username.sendKeys("TestSelenium");

					// click on the Sign in button
					WebElement SignInButton = driver.findElement(By.id("next"));
					SignInButton.click();

					System.out.println("Success!");
				} else {
					System.out.println("Driver not available for type " + browserEntry.getDriverClass() + ". ");
				}
			}
		} catch (final Exception e) {
			System.err.println("Failed!");
			throw e;
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
}
