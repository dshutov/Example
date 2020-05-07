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
        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/main/resources/drivers/chrome/VERSION/chromedriver.exe"
                        .replace("VERSION", PropertiesConfig.getProperty(PropertiesKeys.CHROME_VERSION))))
                .usingAnyFreePort()
                .build();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        final WebDriver driver = new ChromeDriver(service, options);
        driver.manage().window().maximize();

        return driver;
    }
}
