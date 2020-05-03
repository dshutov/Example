package steps;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageHelpers.PageHolder;
import pages.AbstractPage;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static providers.WaitDuration.FIVE_SECONDS;
import static providers.WebDriverWaitProvider.getWebDriverWait;
import static tools.driver.WebDriverProvider.quitDriver;

public class CommonSteps {

    public CommonSteps() {
    }

    @After
    public void close(){
        quitDriver();
    }

    @Then("^on the \"([^\"]*)\" i click on \"([^\"]*)\"$")
    public void clickElementByName(String pageName, String elementName) throws Throwable {
        Log.info(String.format("Clicking on element '%s' on the '%s'.", elementName, pageName));
        WebElement element = PageHolder.getPageByName(pageName).getElementByName(elementName);
        getWebDriverWait(FIVE_SECONDS).until(visibilityOf(element));
        element.click();
    }

    @Then("^on the \"([^\"]*)\" i select \"([^\"]*)\" option located in \"([^\"]*)\"$")
    public void selectComboBoxElement(String pageName, String optionName, String dropdownElementName) throws Exception {
        Log.info(String.format("Selecting '%s' option in '%s' element.", optionName, dropdownElementName));
        WebElement dropdownElement = PageHolder.getPageByName(pageName).getElementByName(dropdownElementName);
        new Select(dropdownElement).selectByVisibleText(optionName);
    }

    @Then("^i check that on the \"([^\"]*)\" the following elements have expected values:$")
    public void getElementTextByElementName(String pageName, DataTable dataTable) throws Exception {
        AbstractPage page = PageHolder.getPageByName(pageName);
        List<List<String>> valuesList = dataTable.raw();
        for (int i = 1; i < valuesList.size(); i++) {
            Log.info(String.format("Checking '%s' element value.", valuesList.get(i).get(0)));
            WebElement element = page.getElementByName(valuesList.get(i).get(0));
            String expectedValue = valuesList.get(i).get(1);
            getWebDriverWait(FIVE_SECONDS).until(visibilityOf(element));
            Assert.assertEquals(element.getText(), expectedValue);
        }
    }

    @Then("^on the \"([^\"]*)\" i set \"([^\"]*)\" on the \"([^\"]*)\"$")
    public void setValueToTextBox(String pageName, String value, String elementName) throws Throwable {
        Log.info(String.format("Send '%s' to the '%s' element.", value, elementName));
        WebElement element = PageHolder.getPageByName(pageName).getElementByName(elementName);
        getWebDriverWait(FIVE_SECONDS).until(elementToBeClickable(element));
        element.clear();
        element.sendKeys(value);
    }

    private Logger Log = LogManager.getLogger(CommonSteps.class);

}
