package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.HeaderMenuSteps;
import steps.NewsPageSteps;
import utils.SmartLogger;
import utils.api.VkApiUtils;
import static services.Uri.VK_URI;

public class BaseTest {

    @BeforeMethod
    protected void setup() {
        SmartLogger.logInfo("Set baseUri");
        VkApiUtils.setupBaseUri(VK_URI.getUri());
        SmartLogger.logInfo("Set window size maximize");
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        SmartLogger.logInfo("Timeout load browser");
        AqualityServices.getBrowser().getDriver().manage().timeouts();
    }

    @AfterMethod
    protected void quitDriver() {
        SmartLogger.logInfo("Open top profile menu");
        NewsPageSteps.profileArrowCmbClick();
        SmartLogger.logInfo("Sign out account");
        NewsPageSteps.signOutBtnClick();
        SmartLogger.logInfo("Quit browser");
        AqualityServices.getBrowser().getDriver().quit();
    }
}
