package stepdefs.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import context.TestContextUI;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import utils.ui.Interact;

public class SearchMutipleProducts extends Interact {
	TestContextUI testContextUI;
	Scenario scn;

	@When("I search for and add single product <Product Name> of each category as below")
	public void i_search_for_and_add_single_product_Product_Name_of_each_category_as_below(List<String> list){
		try {
			for (int i = 0; i < list.size(); i++) {
				testContextUI.getCmnPageObjects().SetSearchTextBox(list.get(i));
				testContextUI.getCmnPageObjects().ClickOnSearchButton();
				testContextUI.getSearchPageObjects().ClickOnProductLink();
				testContextUI.getDriver().switchTo().window((String) testContextUI.getDriver().getWindowHandles().toArray()[1]);
				testContextUI.getCmnPageObjects().ClickOnAddToCart();
				testContextUI.getDriver().close();
				testContextUI.getDriver().switchTo().window((String) testContextUI.getDriver().getWindowHandles().toArray()[0])
						.navigate().refresh();
				testContextUI.getCmnPageObjects().CleartextBox();
			}
		} catch (NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}

	}

	@Then("All the products should be added in the cart")
	public void all_the_products_should_be_added_in_the_cart() {
		testContextUI.getCmnPageObjects().ClickOnCart();
		testContextUI.getCmnPageObjects().proceedToBuyProduts();
	}

}
