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
        ChromeOptions options = new ChromeOptions();

        // Pop-up və parolla bağlı xəbərdarlıqları söndürmək üçün
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");

        // --- Docker və Headless rejimi üçün lazımi parametrlər ---
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        if (isHeadless) {
            options.addArguments("--headless=new"); // Modern headless rejimi
            options.addArguments("--no-sandbox"); // Docker daxilində root hüquqları ilə işləmək üçün mütləqdir
            options.addArguments("--disable-dev-shm-usage"); // Docker yaddaş çətinliyinin (64MB /dev/shm) qarşısını alır
            options.addArguments("--window-size=1920,1080"); // Headless rejimdə elementlərin görsənməsi üçün ölçü
            options.addArguments("--disable-gpu");
        }

        // 1. Driver-i tənzimlənmiş options ilə başladırıq
        driver = new ChromeDriver(options);

        // 2. Brauzer pəncərəsini böyüdüb sayta daxil oluruq
        driver.manage().window().maximize();
        driver.get(SAUCEDEMO_URL);

        // 3. Page Object hazırlığı
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}