package pages;

import annotations.Element;
import annotations.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static providers.WaitDuration.FIVE_SECONDS;
import static providers.WebDriverWaitProvider.getWebDriverWait;
import static tools.driver.WebDriverProvider.getDriver;

@Page(title = "All tickets", name = "All tickets page", partialUrl = "AllTickets.aspx")
public class AllTicketsPage extends AbstractPage {

    public AllTicketsPage() throws Exception {
        super();
    }

    @FindBy(xpath = "//li[.='Shopping Cart']")
    @Element(name = "Shopping Cart tab")
    public WebElement shoppingCartTab;

    @FindBy(id = "MainContent_TeamNamesDropDownList")
    @Element(name = "Select team dropdown")
    public WebElement selectTeamDropdown;

    @FindBy(xpath = "//input[@value='Check Games']")
    @Element(name = "Check Games button")
    public WebElement checkGamesButton;

    @FindBy(xpath = "//table[@class='gridarea']")
    @Element(name = "Available games table")
    public WebElement availableGamesTable;

    @FindBy(id = "MainContent_WipPriceLabel")
    @Element(name = "Wip price label")
    public WebElement wipPriceLable;

    @FindBy(id = "MainContent_PrimaryPriceLabel")
    @Element(name = "Primary price label")
    public WebElement primaryPriceLabel;

    @FindBy(id = "MainContent_SecondaryPriceLabel")
    @Element(name = "Secondary price label")
    public WebElement secondaryPriceLabel;

    @FindBy(id = "MainContent_PrimaryAmountTextBox")
    @Element(name = "Primary amount textbox")
    public WebElement primaryAmountTextbox;

    @FindBy(id = "MainContent_AddToCartButton")
    @Element(name = "Add to shopping cart button")
    public WebElement addToCartButton;

    @FindBy(id = "MainContent_ErrorMessageLabel")
    @Element(name = "Adding to shopping cart status text")
    public WebElement addToCartButtonStatusText;

    public List<String> getMissingDropDownOptions(List<String> dropdownOptionNames) {
        List<String> missingElements = new ArrayList<>();
        for (String dropdownOptionName : dropdownOptionNames) {
            List<WebElement> elements = getDriver().findElements(By.xpath(ITEM_FROM_SELECT_TEAM_DROPDOWN_TEMPLATE.replace("ITEM_NAME", dropdownOptionName)));
            if (elements.size() == 0) {
                missingElements.add(dropdownOptionName);
            }
        }
        return missingElements;
    }

    public WebElement getCheckTicketButtonElementByOpponentName(String opponentName) {
        String btnXpath = CHECK_TICKETS_BUTTON_BY_OPPONENT_NAME_XPATH.replace("ITEM_NAME" , opponentName);
        return getWebDriverWait(FIVE_SECONDS).until(visibilityOfElementLocated(By.xpath(btnXpath)));
    }

    private static final String ITEM_FROM_SELECT_TEAM_DROPDOWN_TEMPLATE = "//select[@id='MainContent_TeamNamesDropDownList']/option[.='ITEM_NAME']";
    private static final String CHECK_TICKETS_BUTTON_BY_OPPONENT_NAME_XPATH = "//table[@class='gridarea']//tr[contains(.,'ITEM_NAME')]//input";
}
