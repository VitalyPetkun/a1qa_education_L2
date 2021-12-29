package steps.registrationsteps.formssteps;

import org.testng.Assert;
import pages.registrationpage.forms.PersonalDetailsForm;

public class PersonalDetailsFormSteps extends PersonalDetailsForm {
    public PersonalDetailsFormSteps() {
        super();
    }

    public void isOpen() {
        Assert.assertTrue(this.isDisplayed());
    }
}
