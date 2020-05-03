package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.ShoppingCartPage;

import java.util.List;
import java.util.Map;


public class ShoppingCartSteps {

    public ShoppingCartSteps() throws Exception {
        this.shoppingCartPage = new ShoppingCartPage();
    }

    @Then("^i check the following data in Shopping cart table from row #(\\d+):$")
    public void getElementTextByElementName(String rowNumber, DataTable dataTable) {
        Map<String, String> tableValues = shoppingCartPage.getShoppingCartTableValuesByRow(rowNumber);
        List<List<String>> valuesList = dataTable.raw();
        for (int i = 1; i < valuesList.size(); i++) {
            Log.info(String.format("Checking Shopping Cart table '%s' element value.", valuesList.get(i).get(0)));
            Assert.assertEquals(valuesList.get(i).get(1), tableValues.get(valuesList.get(i).get(0)));
        }
    }

    private final ShoppingCartPage shoppingCartPage;
    private Logger Log = LogManager.getLogger(ShoppingCartSteps.class);
}
