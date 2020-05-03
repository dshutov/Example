package pages;

import annotations.Element;
import annotations.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static tools.driver.WebDriverProvider.getDriver;

@Page(title = "View my shopping cart", name = "Shopping Cart page", partialUrl = "Checkout/MyShoppingCart.aspx")
public class ShoppingCartPage extends AbstractPage{

    public ShoppingCartPage() throws Exception {
        super();
    }

    @FindBy(id = "MainContent_ShoppingCartLabel")
    @Element(name = "Shopping cart status header")
    public WebElement shoppingCartStatusHeader;

    @FindBy(id = "updShoppingCart")
    @Element(name = "Number of tickets")
    public WebElement numberOfTickets;

    public Map<String, String> getShoppingCartTableValuesByRow(String rowNum) {
        Map<String, String> shoppingCartValuesMap = new HashMap<>();
        List<WebElement> headerColumns = getDriver().findElements(By.xpath(HEADER_ITEMS_FROM_SHOPPING_CART_TABLE));
        List<String> headerColumnsValues =  headerColumns.stream().map(WebElement::getText).collect(Collectors.toList());

        List<WebElement> rowColumns = getDriver().findElements(By.xpath(ITEMS_FROM_SHOPPING_CART_TABLE_TEMPLATE
                .replace("ROW_NUMBER", rowNum)));
        List<String> rowColumnsValues =  rowColumns.stream().map(WebElement::getText).collect(Collectors.toList());

        for (int i = 0; i < headerColumnsValues.size(); i++) {
            shoppingCartValuesMap.put(headerColumnsValues.get(i), rowColumnsValues.get(i));
        }

        return shoppingCartValuesMap;
    }

    private static final String HEADER_ITEMS_FROM_SHOPPING_CART_TABLE = "//table[@class='gridarea']//tr[1]/th";
    private static final String ITEMS_FROM_SHOPPING_CART_TABLE_TEMPLATE = "//table[@class='gridarea']//tr[@class='nrlbg'][ROW_NUMBER]/td";
}
