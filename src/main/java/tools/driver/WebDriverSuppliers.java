package tools.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import tools.properties.PropertiesConfig;
import tools.properties.PropertiesKeys;

import java.io.File;

class WebDriverSuppliers {

    private WebDriverSuppliers()
    {
    }

    static WebDriver firefoxDriver()
    {
        return new FirefoxDriver();
    }


    static WebDriver chromeDriver()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");

        final WebDriver driver = new ChromeDriver( options);
        driver.manage().window().maximize();

        return driver;
    }
}
