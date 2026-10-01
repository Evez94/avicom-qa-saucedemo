package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CartAndCheckoutTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void login() {
        // Hər testdən əvvəl avtomatik login olunur
        inventoryPage = loginPage.logIntoApplication("standard_user", "secret_sauce");
    }

    @Test(description = "Scenario 5: Add two products to cart and verify names and quantities")
    public void testCartTwoProducts() {
        String item1 = "Sauce Labs Backpack";
        String item2 = "Sauce Labs Bike Light";

        inventoryPage.addProductToCartByName(item1);
        inventoryPage.addProductToCartByName(item2);

        CartPage cartPage = inventoryPage.clickCartIcon();

        List<String> cartItems = cartPage.getCartItemNames();
        Assert.assertTrue(cartItems.contains(item1), "First item is missing in the cart!");
        Assert.assertTrue(cartItems.contains(item2), "Second item is missing in the cart!");

        List<Integer> quantities = cartPage.getCartItemQuantities();
        for (Integer qty : quantities) {
            Assert.assertEquals(qty, Integer.valueOf(1), "Product quantity in cart should be 1!");
        }
    }

    @Test(description = "Scenario 6: Complete happy path checkout and validate confirmation message")
    public void testCheckoutHappyPath() {
        inventoryPage.addProductToCartByName("Sauce Labs Backpack");

        CartPage cartPage = inventoryPage.clickCartIcon();
        CheckoutStepOnePage stepOne = cartPage.clickCheckout();

        CheckoutStepTwoPage stepTwo = stepOne.fillInformationAndContinue("Evez", "Aslanov", "AZ1000");
        CheckoutCompletePage completePage = stepTwo.clickFinish();

        String confirmationMessage = completePage.getCompleteHeaderText();
        Assert.assertEquals(confirmationMessage, "Thank you for your order!",
                "Order confirmation header text did not match expected!");
    }

    @Test(description = "Scenario 7: Negative checkout test - missing first name")
    public void testCheckoutMissingFirstName() {
        inventoryPage.addProductToCartByName("Sauce Labs Backpack");

        CartPage cartPage = inventoryPage.clickCartIcon();
        CheckoutStepOnePage stepOne = cartPage.clickCheckout();

        stepOne.fillInformationAndContinue("", "Aslanov", "AZ1000");

        String errorMessage = stepOne.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"),
                "Error message for missing First Name was not displayed correctly! Actual: " + errorMessage);
    }

    @Test(description = "Scenario 8: Verify successful full checkout flow from login to order completion")
    public void testSuccessfulFullCheckoutFlow() {
        // @BeforeMethod artıq login olduğu üçün birbaşa məhsul əlavə edirik:
        inventoryPage.addBackpackToCart();

        CartPage cartPage = inventoryPage.clickCartIcon();
        CheckoutStepOnePage stepOnePage = cartPage.clickCheckout();

        CheckoutStepTwoPage stepTwoPage = stepOnePage.fillInformationAndContinue("Evez", "Aslanov", "AZ1000");
        CheckoutCompletePage completePage = stepTwoPage.clickFinish();

        Assert.assertTrue(completePage.isCompleteHeaderDisplayed(),
                "Complete header visual display check failed!");

        Assert.assertEquals(completePage.getCompleteHeaderText(),
                "Thank you for your order!",
                "Order completion header text did not match!");

        InventoryPage finalInventoryPage = completePage.clickBackHome();
        Assert.assertTrue(finalInventoryPage.isProductsHeaderDisplayed(),
                "Failed to return to Inventory page after clicking Back Home!");
    }

}