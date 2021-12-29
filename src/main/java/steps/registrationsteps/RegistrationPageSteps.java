package steps.registrationsteps;

import aquality.selenium.elements.actions.JsActions;
import org.testng.Assert;
import pages.registrationpage.RegistrationPage;
import steps.registrationsteps.formssteps.*;

public class RegistrationPageSteps extends RegistrationPage {
    public RegistrationPageSteps() {
        super();
    }

    public void isStartFromZero() {
        String currentTime = new JsActions(super.getElementFactory().getLabel(XPATH_LABEL_TIMER,"Label of timer"),
                "Label").getElementText();

        Assert.assertEquals(currentTime, "00:00:00");
    }

    public LoginFormSteps getLoginFormSteps(){
        return new LoginFormSteps();
    }

    public AvatarAndInterestsFormSteps getAvatarAndInterestsFormSteps(){
        return new AvatarAndInterestsFormSteps();
    }

    public PersonalDetailsFormSteps getPersonalDetailsFormSteps() {
        return new PersonalDetailsFormSteps();
    }

    public HelpFormSteps getHelpFormSteps(){
        return new HelpFormSteps();
    }

    public CookieFormSteps getCookieFormSteps(){
        return new CookieFormSteps();
    }
}
