package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By CART_ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private final By CART_ITEM_QUANTITY = By.cssSelector("[data-test='item-quantity']");
    private final By CHECKOUT_BUTTON = By.cssSelector("[data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Səbətdəki məhsul adlarını siyahı kimi qaytarır
    public List<String> getCartItemNames() {
        List<WebElement> elements = findAll(CART_ITEM_NAME);
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText().trim());
        }
        return names;
    }

    // Hər bir məhsulun miqdarını (quantity) qaytarır
    public List<Integer> getCartItemQuantities() {
        List<WebElement> elements = findAll(CART_ITEM_QUANTITY);
        List<Integer> quantities = new ArrayList<>();
        for (WebElement element : elements) {
            quantities.add(Integer.parseInt(element.getText().trim()));
        }
        return quantities;
    }

    // Checkout səhifəsinə keçid etmək üçün
    public CheckoutStepOnePage clickCheckout() {
        click(CHECKOUT_BUTTON);
        return new CheckoutStepOnePage(driver);
    }
}