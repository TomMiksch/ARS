package edu.uiowa.ars.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum BrowserEntry {
	CHROME(ChromeDriver.class, "webdriver.chrome.driver", "CHROME_DRIVER"),
	FIREFOX(FirefoxDriver.class, "webdriver.gecko.driver", "FIREFOX_DRIVER"),
	IE(InternetExplorerDriver.class, "webdriver.internetexplorer.driver", "IE_DRIVER");

	private final Class<? extends WebDriver> driverClass;
	private final String driverProp;
	private final String driverEnvVar;

	BrowserEntry(final Class<? extends WebDriver> driverClass, final String driverProp, final String driverEnvVar) {
		this.driverClass = driverClass;
		this.driverProp = driverProp;
		this.driverEnvVar = driverEnvVar;

		// Set the java system variable to the value of the environment variable
		// if it is set.
		if (driverEnvVar != null) {
			final String envVal = System.getenv(driverEnvVar);
			if (envVal != null) {
				System.setProperty(driverProp, envVal);
			}
		}
	}

	public Class<? extends WebDriver> getDriverClass() {
		return this.driverClass;
	}

	public String getDriverProp() {
		return this.driverProp;
	}

	public String getDriverEnvVar() {
		return this.driverEnvVar;
	}
}
