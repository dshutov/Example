package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageHelpers.PageHolder;
import pages.AllTicketsPage;
import tools.properties.PropertiesConfig;
import tools.properties.PropertiesKeys;

import java.util.List;
import java.util.stream.Collectors;

import static tools.driver.WebDriverProvider.getDriver;

public class AllTicketsPageSteps {

    public AllTicketsPageSteps() throws Exception {
        this.allTicketsPage = new AllTicketsPage();
    }

    @Given("^i navigate to the \"([^\"]*)\"$")
    public void navigateToThePage(String pageName) throws Throwable {
        String pageUrl = BASE_URL + PageHolder.getPartialUrlByPageName(pageName);
        getDriver().navigate().to(pageUrl);
        Log.info("Navigating to the " + pageName + ". Url: " + pageUrl);
    }

    @Then("^i am on the \"([^\"]*)\"$")
    public void andPageOpened(String pageName) throws Exception {
        Assert.assertTrue(PageHolder.getPageByName(pageName).isAppeared(pageName), "Page " + pageName + " hasn't been opened");
    }

    @Then("^i check that \"Select Team\" dropdown contains the following elements:$")
    public void checkAllTicketsPageDropDownElements(List<String> titleNames) {
        List<String> missingOptions = allTicketsPage.getMissingDropDownOptions(titleNames);
        Assert.assertTrue(missingOptions.isEmpty(), String.format("The following dropdown options are missing: %s",
                missingOptions.stream().map(item -> "'" + item + "'").collect(Collectors.joining(","))));
    }

    @Then("^i click on \"Check Tickets button\" for game where opponent is \"([^\"]*)\"$")
    public void clickCheckTickets(String opponentName){
        Log.info(String.format("Clicking on 'Check Tickets' button for game with opponent '%s'.", opponentName));
        WebElement btnElement = allTicketsPage.getCheckTicketButtonElementByOpponentName(opponentName);
        btnElement.click();
    }

    private final AllTicketsPage allTicketsPage;
    private static final String BASE_URL = PropertiesConfig.getProperty(PropertiesKeys.URL_APP_MAIN);
    private Logger Log = LogManager.getLogger(AllTicketsPageSteps.class);
}
