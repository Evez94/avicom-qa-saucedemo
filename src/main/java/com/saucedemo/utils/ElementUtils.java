package com.saucedemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtils extends Utility {

    private WebDriverWait wait;

    public ElementUtils(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> getElements(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public void doSendKeys(By locator, String value) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getElementText(By locator) {
        return getElement(locator).getText();
    }
}