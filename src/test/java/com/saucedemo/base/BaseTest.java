package com.saucedemo.base;

import com.saucedemo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    private final String SAUCEDEMO_URL = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp() {
        // 1. Chrome parametrlərini və pop-up ləğvetmələrini əvvəlcə hazırlayırıq
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        // Headless rejimdə işlətmək istəsəniz: options.addArguments("--headless=new");

        // 2. Yalnız BİR dəfə driver-i tənzimlənmiş options ilə başladırıq
        driver = new ChromeDriver(options);

        // 3. Brauzer pəncərəsini böyüdüb sayta daxil oluruq
        driver.manage().window().maximize();
        driver.get(SAUCEDEMO_URL);

        // 4. Page Object hazırlığı
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Hər test bitdikdə brauzeri tam bağlayır
        }
    }
}