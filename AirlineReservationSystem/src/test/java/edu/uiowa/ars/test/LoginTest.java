package edu.uiowa.ars.test;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class LoginTest {

	private static final String PROP_FILE_NAME = "loginTest.properties";

	@Test
	public void testLogin() throws Exception {
		// TODO Jetty needs to be running to have this work.
		final Properties prop = new Properties();
		WebDriver driver = null;
		try (final InputStream input = this.getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME)) {
			if (input == null) {
				throw new NullPointerException("Could not find file " + PROP_FILE_NAME);
			}
			prop.load(input);

			// Test this functionality for all browsers in the map.
			for (final BrowserEntry browserEntry : BrowserEntry.values()) {
				final String driverProp = browserEntry.getDriverProp();

				if (System.getProperty(driverProp) != null) {
					System.out.println("Testing login for browser with driver " + browserEntry.getDriverClass());
					final Constructor<?> ctor = browserEntry.getDriverClass().getConstructor();
					driver = (WebDriver) ctor.newInstance();

					// Launch the main page.
					driver.get("http://localhost:8080/home");

					// First verify the title of the home page.
					assertTrue(TestSupport.verifyProperty(prop, "frontPageTitle", driver.getTitle()));

					// Verify the login button exists, then click it.
					final WebElement loginButton = TestSupport.getElementById(driver, "loginButton");
					loginButton.click();

					// Verify that clicking the login button with an invalid
					// username and password displays an error message.
					final WebElement submitButton = TestSupport.getElementById(driver, "submitButton");
					submitButton.click();
					final WebElement errorMessage = TestSupport.getElementById(driver, "formErrors");
					assertTrue(TestSupport.verifyProperty(prop, "loginPageErrorMessage", errorMessage.getText()));

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
