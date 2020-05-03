package tools.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tools.driver.WebDriverProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import org.junit.Assert;

public class PropertiesConfig {

    private static final Logger LOGGER = LogManager.getLogger(WebDriverProvider.class);
    private static final Properties PROPERTIES = getProperties();

    private PropertiesConfig()
    {
    }

    private static Properties getProperties()
    {
        final Properties props = new Properties();
        final ClassLoader classLoader = PropertiesConfig.class.getClassLoader();
        final String file = "application-test.properties";
        try
        {
            final Enumeration<URL> resources = classLoader.getResources(file);
            while (resources.hasMoreElements())
            {
                final URL currentResource = resources.nextElement();
                try (InputStream stream = currentResource.openStream())
                {
                    props.load(stream);
                }
            }
        }
        catch (IOException e)
        {
            LOGGER.error("Error while reading property file:{},{}", e, e.getMessage());
            throw new RuntimeException(e);
        }
        return props;
    }

    public static String getProperty(String propertyName)
    {
        final String result = PROPERTIES.getProperty(propertyName);
        Assert.assertNotNull("Property must not be null " + propertyName, result);

        return result;
    }
}
