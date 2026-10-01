# SauceDemo Test Automation Framework

![Java](https://img.shields.io/badge/Java-19-orange.svg)
![Selenium](https://img.shields.io/badge/Selenium-4.29.0-green.svg)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-blue.svg)
![Maven](https://img.shields.io/badge/Maven-Build-red.svg)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue.svg)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-red.svg)

This project is an enterprise-standard, production-ready Automated Testing Framework built for the [SauceDemo](https://www.saucedemo.com/) e-commerce web application. Designed using the **Page Object Model (POM)** pattern, it ensures maintainability, scalability, and robust synchronization for End-to-End (E2E) and Regression testing suites, fully integrated with Docker and Jenkins CI/CD pipelines.

---

## 🛠️ Tech Stack & Key Tools

- **Programming Language:** Java 19
- **Automation Engine:** Selenium WebDriver (v4.29.0)
- **Testing Framework:** TestNG (v7.11.0)
- **Build & Dependency Management:** Apache Maven
- **Design Pattern:** Page Object Model (POM)
- **CI/CD & Containerization:** Docker, Jenkins Pipeline
- **Logging:** SLF4J (Simple Logging Facade)

---

## 🏗️ Architecture & Core Features

* **Page Object Model (POM):** Complete decoupling of Test Scenarios from UI Locators and Actions, enhancing code reusability.
* **Explicit Wait Synchronization:** Custom wrapper methods utilizing `WebDriverWait` to mitigate flakiness and eliminate hardcoded delays (`Thread.sleep`).
* **Optimized Chrome Configuration:** Customized `ChromeOptions` to suppress password manager prompts, save-password banners, and browser notifications during test execution.
* **Method Chaining (Fluent Interface):** Smooth navigation transitions between pages (e.g., `LoginPage` -> `InventoryPage` -> `CartPage`).
* **TestNG Test Suite:** Organized parallel/sequential suite execution using `testng.xml`.
* **CI/CD & Headless Ready:** Integrated `Dockerfile` and `Jenkinsfile` for seamless containerized execution and automated CI/CD pipeline builds.

---

## 📁 Project Structure

```text
AVICOM-Sauce-Demo-site-automation/
├── src/
│   ├── main/java/com/saucedemo/
│   │   ├── base/
│   │   │   └── BasePage.java            # Explicit waits & reusable UI wrappers
│   │   ├── utils/
│   │   │   ├── Utility.java             # Base parent class for driver reference
│   │   │   ├── JavaScriptUtility.java   # JavaScript Executor helper methods
│   │   │   └── ElementUtils.java        # Explicit wait wrappers & web element actions
│   │   └── pages/
│   │       ├── LoginPage.java           # Authentication UI actions
│   │       ├── InventoryPage.java       # Product list & sorting logic
│   │       ├── CartPage.java            # Shopping cart items & quantities
│   │       ├── CheckoutStepOnePage.java # User details form
│   │       ├── CheckoutStepTwoPage.java # Order review & price calculations
│   │       └── CheckoutCompletePage.java# Order confirmation header
│   └── test/java/com/saucedemo/
│       ├── base/
│       │   └── BaseTest.java            # Driver lifecycle (@BeforeMethod, @AfterMethod)
│       └── tests/
│           ├── LoginTests.java          # Valid/Invalid/Locked login scenarios
│           ├── InventoryTests.java      # Product catalog, pricing, & sorting validation
│           └── CartAndCheckoutTests.java# E2E Happy Path & Negative validation tests
├── Dockerfile                           # Docker container configuration for headless Chrome execution
├── Jenkinsfile                          # Jenkins CI/CD pipeline definition
├── testng.xml                           # TestNG suite runner configuration
├── pom.xml                              # Maven build & surefire plugin setup
└── README.md                            # Framework documentation