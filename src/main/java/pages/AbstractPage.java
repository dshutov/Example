package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import pageHelpers.PageAnnotationHelper;
import pageHelpers.PageHolder;

import java.util.List;

import static providers.WaitDuration.FIVE_SECONDS;
import static providers.WebDriverWaitProvider.getWebDriverWait;
import static tools.driver.WebDriverProvider.getDriver;

public abstract class AbstractPage {

    AbstractPage() throws Exception {
        PageFactory.initElements(getDriver(), this);
        cPageAnnotationHelper = new PageAnnotationHelper(this);
        PageHolder.putPageToCache(this);
    }

    public String getTitle() {
        return cPageAnnotationHelper.getPageTitle();
    }

    public List<WebElement> getAllWebElementsOnThePage() {
        return cPageAnnotationHelper.getElementList();
    }

    public WebElement getElementByName(String annotationName) throws Exception {
        return cPageAnnotationHelper.getElementByAnnotation(annotationName);
    }

    public boolean isAppeared(String pageName) throws Exception {
        try {
            return getWebDriverWait(FIVE_SECONDS)
                    .until(ExpectedConditions.titleIs(PageHolder.getPageByName(pageName).getTitle()));
        } catch (TimeoutException e) {
            Log.info(String.format("Page with name '%s' was not opened. Error message: %s", pageName, e.getMessage()));
        }
        return false;
    }


    private Logger Log = LogManager.getLogger(AbstractPage.class);

    private PageAnnotationHelper cPageAnnotationHelper;
}
