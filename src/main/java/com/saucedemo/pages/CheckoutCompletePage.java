package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By COMPLETE_HEADER = By.cssSelector("[data-test='complete-header']");
    private final By BACK_HOME_BUTTON = By.cssSelector("[data-test='back-to-products']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return find(COMPLETE_HEADER).getText();
    }

    public boolean isCompleteHeaderDisplayed() {
        return find(COMPLETE_HEADER).isDisplayed();
    }

    public InventoryPage clickBackHome() {
        click(BACK_HOME_BUTTON);
        return new InventoryPage(driver);
    }
}