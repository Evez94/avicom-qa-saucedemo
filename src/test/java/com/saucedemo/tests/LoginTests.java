package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Scenario 1: Verify successful login with valid credentials")
    public void testValidLogin() {
        InventoryPage inventoryPage = loginPage.logIntoApplication("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isProductsHeaderDisplayed(),
                "Products header is not displayed after logging in with valid credentials!");
    }

    @Test(description = "Scenario 2: Verify unsuccessful login with invalid password shows error message")
    public void testInvalidLoginErrorMessage() {
        loginPage.setUsername("standard_user");
        loginPage.setPassword("asdfg");
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessage();
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";

        // 1. Xəta mesajının yoxlanması
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Error message text did not match! Actual: " + actualMessage);

        // 2. Inventory səhifəsinə keçid EDİLMƏDİYİNİN yoxlanması (AVICOM Requirement)
        // URL-in hələ də inventory-yə dəyişmədiyini yoxlayırıq:
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"),
                "User was incorrectly redirected to inventory page after failed login!");

    }
    @Test(description = "Scenario 2/7: Verify locked out user cannot log in")
    public void testLockedOutUserLogin() {
        loginPage.logIntoApplication("locked_out_user", "secret_sauce");

        String actualMessage = loginPage.getErrorMessage();
        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";

        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Locked out user error message did not match! Actual: " + actualMessage);
    }
}