package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    private final By FIRST_NAME_INPUT = By.cssSelector("[data-test='firstName']");
    private final By LAST_NAME_INPUT = By.cssSelector("[data-test='lastName']");
    private final By POSTAL_CODE_INPUT = By.cssSelector("[data-test='postalCode']");
    private final By CONTINUE_BUTTON = By.cssSelector("[data-test='continue']");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        set(FIRST_NAME_INPUT, firstName);
    }

    public void enterLastName(String lastName) {
        set(LAST_NAME_INPUT, lastName);
    }

    public void enterPostalCode(String postalCode) {
        set(POSTAL_CODE_INPUT, postalCode);
    }

    public void clickContinue() {
        click(CONTINUE_BUTTON);
    }

    // Reusable metod: Bütün məlumatları doldurub daxil olur
    public CheckoutStepTwoPage fillInformationAndContinue(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
        return new CheckoutStepTwoPage(driver);
    }

    public String getErrorMessage() {
        return find(ERROR_MESSAGE).getText();
    }
}