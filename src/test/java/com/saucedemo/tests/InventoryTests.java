package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginAndNavigateToInventory() {

        inventoryPage = loginPage.logIntoApplication("standard_user", "secret_sauce");
    }

    @Test(description = "Scenario 3: Verify product inventory list is visible, not empty, and shows name & price")
    public void testProductInventoryList() {

        Assert.assertTrue(inventoryPage.isProductsHeaderDisplayed(),
                "Products header is not displayed!");

        int productCount = inventoryPage.getProductCount();
        Assert.assertTrue(productCount > 0,
                "Product count should be greater than 0, but found: " + productCount);

        Assert.assertTrue(inventoryPage.areAllItemNamesDisplayedAndNotEmpty(),
                "One or more products are missing a visible item name!");

        Assert.assertTrue(inventoryPage.areAllItemPricesDisplayedAndNotEmpty(),
                "One or more products are missing a visible price!");
    }

    @Test(description = "Scenario 4.1: Verify sorting products by price (Low to High)")
    public void testSortByPriceLowToHigh() {
        inventoryPage.selectSortOption("lohi");

        List<Double> actualPrices = inventoryPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices,
                "Products are not sorted correctly by Price (Low to High)!");
    }

    @Test(description = "Scenario 4.2 : Verify sorting products by price (High to Low)")
    public void testSortByPriceHighToLow() {

        inventoryPage.selectSortOption("hilo");

        List<Double> actualPrices = inventoryPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices, expectedPrices,
                "Products are not sorted correctly by Price (High to Low)!");
    }
}