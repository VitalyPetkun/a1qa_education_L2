package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected final Logger LOGGER = Logger.getInstance();

    @BeforeMethod
    protected void setup() {
        LOGGER.info("Set window size maximize");
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        LOGGER.info("Timeout load browser");
        AqualityServices.getBrowser().getDriver().manage().timeouts();
    }

    @AfterMethod
    protected void quitDriver() {
        LOGGER.info("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
    }
}