package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

    private final By INVENTORY_HEADER = By.cssSelector("[data-test='title']");
    private final By INVENTORY_ITEMS = By.cssSelector("[data-test='inventory-item']");
    private final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private final By SORT_DROPDOWN = By.cssSelector("[data-test='product-sort-container']");
    private final By CART_LINK = By.cssSelector("[data-test='shopping-cart-link']");
    private final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }


    public boolean isProductsHeaderDisplayed() {
        return find(INVENTORY_HEADER).isDisplayed();
    }


    public int getProductCount() {
        return findAll(INVENTORY_ITEMS).size();
    }


    public boolean areAllItemNamesDisplayedAndNotEmpty() {
        List<WebElement> nameElements = findAll(ITEM_NAME);
        if (nameElements.isEmpty()) return false;

        for (WebElement element : nameElements) {
            if (!element.isDisplayed() || element.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public boolean areAllItemPricesDisplayedAndNotEmpty() {
        List<WebElement> priceElements = findAll(ITEM_PRICE);
        if (priceElements.isEmpty()) return false;

        for (WebElement element : priceElements) {
            if (!element.isDisplayed() || element.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public List<Double> getProductPrices() {
        List<WebElement> priceElements = findAll(ITEM_PRICE);
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }


    public void selectSortOption(String optionValue) {
        Select select = new Select(find(SORT_DROPDOWN));
        select.selectByValue(optionValue);
    }

    public void addProductToCartByName(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.cssSelector("[data-test='add-to-cart-" + formattedName + "']");
        click(addToCartButton);
    }


    public InventoryPage addBackpackToCart() {
        addProductToCartByName("Sauce Labs Backpack");
        return this;
    }


    public CartPage clickCartIcon() {
        click(CART_LINK);
        return new CartPage(driver);
    }

}