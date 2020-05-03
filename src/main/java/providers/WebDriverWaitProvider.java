package providers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.properties.PropertiesConfig;
import tools.properties.PropertiesKeys;

import static tools.driver.WebDriverProvider.getDriver;


public final class WebDriverWaitProvider
{

    private WebDriverWaitProvider()
    {
    }

    public static FluentWait<WebDriver> getWebDriverWait()
    {
        final long timeOutInSeconds = Long.parseLong(PropertiesConfig.getProperty(PropertiesKeys.TIMEOUT_IN_SECONDS));
        final long sleepInMillis = Long.parseLong(PropertiesConfig.getProperty(PropertiesKeys.SLEEP_IN_MILLIS));
        return new WebDriverWait(getDriver(), timeOutInSeconds, sleepInMillis)
                   .ignoring(StaleElementReferenceException.class)
                   .ignoring(NoSuchElementException.class);
    }

    public static FluentWait<WebDriver> getWebDriverWait(WaitDuration timeOutInSeconds)
    {
        final long sleepInMillis = Long.parseLong(PropertiesConfig.getProperty(PropertiesKeys.SLEEP_IN_MILLIS));
        return new WebDriverWait(getDriver(), timeOutInSeconds.getDuration(), sleepInMillis)
                   .ignoring(StaleElementReferenceException.class)
                   .ignoring(NoSuchElementException.class);
    }
}