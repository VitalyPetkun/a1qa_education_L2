package steps.registrationsteps.formssteps;

import aquality.selenium.elements.actions.JsActions;
import org.testng.Assert;
import pages.registrationpage.forms.CookieForm;

public class CookieFormSteps extends CookieForm {
    public CookieFormSteps() {
        super();
    }

    public void clickButtonNotReallyNo() {
        new JsActions(super.getElementFactory().getTextBox(XPATH_BUTTON_NOT_REALLY_NO,"Button Not Really No")
                ,"Button").click();
    }

    public void isClose() {
        Assert.assertFalse(this.isDisplayed());
    }
}
