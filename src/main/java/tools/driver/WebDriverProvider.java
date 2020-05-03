package tools.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import tools.properties.PropertiesConfig;
import tools.properties.PropertiesKeys;


public class WebDriverProvider {

    private static final String BROWSER = PropertiesConfig.getProperty(PropertiesKeys.BROWSER_TYPE);

    private static WebDriver driver;

    private WebDriverProvider()
    {
    }

    public static WebDriver getDriver()
    {
        final BrowserType browserType = BrowserType.fromString(BROWSER);

        System.setProperty(browserType.getSystemPropertyName(), browserType.getDriverResourcePath());

        if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
            driver = browserType.newInstance();
        }

        return driver;
    }

    public static void quitDriver()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}
