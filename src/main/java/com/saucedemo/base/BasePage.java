package com.saucedemo.base;

import com.saucedemo.utils.ElementUtils;
import com.saucedemo.utils.JavaScriptUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected ElementUtils elementUtils;
    protected JavaScriptUtility jsUtility;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.jsUtility = new JavaScriptUtility(driver);
    }

    protected WebElement find(By locator) {
        return elementUtils.getElement(locator);
    }

    protected List<WebElement> findAll(By locator) {
        return elementUtils.getElements(locator);
    }

    protected void set(By locator, String text) {
        elementUtils.doSendKeys(locator, text);
    }

    protected void click(By locator) {
        elementUtils.doClick(locator);
    }
}
