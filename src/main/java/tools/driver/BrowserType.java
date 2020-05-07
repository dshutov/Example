package tools.driver;

import org.openqa.selenium.WebDriver;
import tools.properties.PropertiesConfig;
import tools.properties.PropertiesKeys;

import java.io.File;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum BrowserType {
    CHROME("chrome", "chromedriver.exe", "webdriver.chrome.driver", WebDriverSuppliers::chromeDriver),
    FIREFOX("firefox", "geckodriver.exe", "webdriver.gecko.driver", WebDriverSuppliers::firefoxDriver);

    private final String name;

    private final String driverResourcePath;

    private final String systemPropertyName;

    private final Supplier<WebDriver> webDriverSupplier;

    BrowserType(String name, String driverResourcePath, String systemPropertyName, Supplier<WebDriver> webDriverSupplier)
    {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource("drivers").getFile());

        this.name = name;
        this.driverResourcePath = name.equals("chrome") ? file.getAbsolutePath() + "\\" + VERSION_OF_CHROME + "\\" + driverResourcePath
                : file.getAbsolutePath() + "\\" + driverResourcePath;
        this.systemPropertyName = systemPropertyName;
        this.webDriverSupplier = webDriverSupplier;
    }

    static BrowserType fromString(String browser) {
        final Optional<BrowserType> browserTypeOptional = Stream.of(BrowserType.values()).filter(browserType -> browserType.name.equals(browser)).findFirst();

        return browserTypeOptional.orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported browser type [%s]", browser)));
    }

    public String getDriverResourcePath()
    {
        return driverResourcePath;
    }

    public String getSystemPropertyName()
    {
        return systemPropertyName;
    }

    public WebDriver newInstance() {
        return webDriverSupplier.get();
    }

    private final String VERSION_OF_CHROME = PropertiesConfig.getProperty(PropertiesKeys.CHROME_VERSION);
}
