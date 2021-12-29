package steps.registrationsteps.formssteps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.actions.JsActions;
import org.testng.Assert;
import pages.registrationpage.forms.HelpForm;
import utils.ConfigManager;
import java.time.Duration;
import java.util.function.BooleanSupplier;

public class HelpFormSteps extends HelpForm {
    public HelpFormSteps() {
        super();
    }

    public void clickBtnSendToBottom() {
        new JsActions(super.getElementFactory().getButton(XPATH_BUTTON_SEND_TO_BOTTOM
                ,"Button send to bottom"), "Button").clickAndWait();

        BooleanSupplier booleanSupplier = new BooleanSupplier() {
            HelpFormSteps helpSteps = new HelpFormSteps();
            @Override
            public boolean getAsBoolean() {
                return !(new JsActions(helpSteps.getElementFactory().getLabel(XPATH_LABEL_TITLE
                        ,"Label title"), "Label").isElementOnScreen());
            }
        };

        AqualityServices.getConditionalWait().waitFor(booleanSupplier,
                Duration.ofSeconds(ConfigManager.getConfigInt("timeout")),
                Duration.ofSeconds(ConfigManager.getConfigInt("pollingInterval")));
    }

    public void isClose() {
        Assert.assertFalse(this.isDisplayed());
    }
}
