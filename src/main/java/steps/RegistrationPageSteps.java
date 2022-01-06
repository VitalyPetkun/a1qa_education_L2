package steps;

import aquality.selenium.elements.actions.JsActions;
import org.testng.Assert;
import pages.registrationpage.RegistrationPage;
import pages.registrationpage.forms.AvatarAndInterestsForm;
import pages.registrationpage.forms.LoginForm;
import pages.registrationpage.forms.PersonalDetailsForm;
import utils.ConfigManager;

public class RegistrationPageSteps {

    public static final RegistrationPage registrationPage = new RegistrationPage();
    public static final LoginForm loginForm = new LoginForm();
    public static final AvatarAndInterestsForm avatarAndInterestsForm = new AvatarAndInterestsForm();
    public static final PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();

    private static void passwordScrollIntoView() {
        loginForm.passwordScrollIntoView();
    }

    public static void passwordInput() {
        passwordScrollIntoView();
        loginForm.passwordInput();
    }

    public static void emailInput() {
        loginForm.emailInput();
    }

    public static void domainInput() {
        loginForm.domainInput();
    }

    private static void domainExtensionsClick() {
        loginForm.domainExtensionsClick();
    }

    public static void domainExtensionsItemClick() {
        domainExtensionsClick();
        loginForm.domainExtensionsItemClick();
    }

    private static boolean isTermsAndConditionsChecked() {
        return loginForm.isTermsAndConditionsChecked();
    }

    public static void termsAndConditionsCheck() {
        if(!isTermsAndConditionsChecked())
            loginForm.termsAndConditionsCheck();
    }

    public static void loginFormBtnNextClick() {
        loginForm.btnNextClick();
    }

    private static String getLabelTimerText() {
        return registrationPage.getLabelTimerText();
    }

    public static void assertIsTimerStartFromZero() {
        Assert.assertEquals(getLabelTimerText(),"00:00:00", "Timer doesn't start from zero.");
    }

    public static void assertIsRegistrationPageOpen() {
        Assert.assertTrue(registrationPage.isDisplayed(),"Registration page isn't open.");
    }

    public static void assertIsLoginFormOpen() {
        Assert.assertTrue(loginForm.isDisplayed(),"Login form isn't open.");
    }

    public static void assertIsAvatarAndInterestsFormOpen() {
        Assert.assertTrue(avatarAndInterestsForm.isDisplayed(),"Avatar and interests form isn't open.");
    }
    public static void assertIsPersonalDetailsFormOpen() {
        Assert.assertTrue(personalDetailsForm.isDisplayed(),"Personal details form isn't open.");
    }
}
