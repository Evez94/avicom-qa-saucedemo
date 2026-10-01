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
        // Hər testdən əvvəl avtomatik login oluruq
        inventoryPage = loginPage.logIntoApplication("standard_user", "secret_sauce");
    }

    @Test(description = "Scenario 3: Verify product inventory list is visible, not empty, and shows name & price")
    public void testProductInventoryList() {
        // 1. Header görsənir?
        Assert.assertTrue(inventoryPage.isProductsHeaderDisplayed(),
                "Products header is not displayed!");

        // 2. Siyahı boş deyil?
        int productCount = inventoryPage.getProductCount();
        Assert.assertTrue(productCount > 0,
                "Product count should be greater than 0, but found: " + productCount);

        // 3. Hər bir məhsulun adı var?
        Assert.assertTrue(inventoryPage.areAllItemNamesDisplayedAndNotEmpty(),
                "One or more products are missing a visible item name!");

        // 4. Hər bir məhsulun qiyməti var?
        Assert.assertTrue(inventoryPage.areAllItemPricesDisplayedAndNotEmpty(),
                "One or more products are missing a visible price!");
    }

    @Test(description = "Scenario 4.1: Verify sorting products by price (Low to High)")
    public void testSortByPriceLowToHigh() {
        // Dropdown-dan 'Price (low to high)' seçirik (value="lohi")
        inventoryPage.selectSortOption("lohi");

        // Saytdakı qiymətlərin siyahısını götürürük
        List<Double> actualPrices = inventoryPage.getProductPrices();

        // Götürdüyümüz siyahının kopyasını yaradıb Java ilə özümüz çeşidləyirik (Expected)
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        // Saytdakı ardıcıllıqla gözlənilən artan ardıcıllığı müqayisə edirik
        Assert.assertEquals(actualPrices, expectedPrices,
                "Products are not sorted correctly by Price (Low to High)!");
    }

    @Test(description = "Scenario 4.2 : Verify sorting products by price (High to Low)")
    public void testSortByPriceHighToLow() {
        // Dropdown-dan 'Price (high to low)' seçirik (value="hilo")
        inventoryPage.selectSortOption("hilo");

        List<Double> actualPrices = inventoryPage.getProductPrices();

        // Qiymətləri azalan sıra ilə düzürük (Expected)
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices, expectedPrices,
                "Products are not sorted correctly by Price (High to Low)!");
    }
}