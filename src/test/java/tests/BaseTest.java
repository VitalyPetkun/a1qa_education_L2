package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    protected void setup() {
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        AqualityServices.getBrowser().getDriver().manage().timeouts();
    }

    @AfterMethod
    protected void quitDriver() {
        AqualityServices.getBrowser().getDriver().quit();
    }
}