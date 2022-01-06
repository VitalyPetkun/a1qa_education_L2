package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected final Logger logger = Logger.getInstance();

    @BeforeMethod
    protected void setup() {
        logger.info("Set window size maximize");
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        logger.info("Timeout load browser");
        AqualityServices.getBrowser().getDriver().manage().timeouts();
    }

    @AfterMethod
    protected void quitDriver() {
        logger.info("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
    }
}