package steps;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import pages.registrationpage.RegistrationPage;
import pages.registrationpage.forms.*;
import utils.PropertiesManager;
import utils.CooperationWithDialogWindow;
import utils.RegistrationPageUtils;

import java.util.*;

public class RegistrationPageSteps {

    private static final RegistrationPage registrationPage = new RegistrationPage();
    private static final LoginForm loginForm = new LoginForm();
    private static final AvatarAndInterestsForm avatarAndInterestsForm = new AvatarAndInterestsForm();
    private static final PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
    private static final CookieForm cookieForm = new CookieForm();
    private static final HelpForm helpForm = new HelpForm();

    private static final String downloadFilePath = PropertiesManager.getTestDataValue("avatarIconPath");
    private static final String downloadFileName = PropertiesManager.getTestDataValue("avatarIconName");
    private static final String unselectAllInterest = "Unselect all";
    private static final String selectAllInterest = "Select all";
    private static final int randomInterestsNumber = Integer.parseInt(PropertiesManager.getTestDataValue("randomInterestsNumber"));

    private RegistrationPageSteps() {
    }

    public static void passwordTxtInput() {
        loginForm.passwordTxtInput(RegistrationPageUtils.getRandomPassword());
    }

    public static void emailTxtInput() {
        loginForm.emailTxtInput();
    }

    public static void domainTxtInput() {
        loginForm.domainTxtInput();
    }

    private static void domainExtensionsCmbClick() {
        loginForm.domainExtensionsCmbClick();
    }

    public static void domainExtensionsItemClick() {
        domainExtensionsCmbClick();
        loginForm.itemDomainExtensionsCmbClick();
    }

    private static boolean isTermsAndConditionsChkChecked() {
        return loginForm.isTermsAndConditionsChkChecked();
    }

    public static void termsAndConditionsChkCheck() {
        if (!isTermsAndConditionsChkChecked())
            loginForm.termsAndConditionsChkCheck();
    }

    public static void loginFormNextBtnClick() {
        loginForm.nextBtnClick();
    }

    public static void threeRandomInterestsSelect() {
        Object[] interestsName = avatarAndInterestsForm.getInterestsName().stream().toArray();
        String[] unwantedInterests = {unselectAllInterest, selectAllInterest};

        List<Integer> randInterests = RegistrationPageUtils.getRandomList(
                randomInterestsNumber, avatarAndInterestsForm.getInterestsSize(), interestsName, unwantedInterests);

        avatarAndInterestsForm.interestCheck(unselectAllInterest);

        for (int i = 0; i < randInterests.size(); i++) {
            avatarAndInterestsForm.interestCheck(String.valueOf(interestsName[randInterests.get(i)]));
        }
    }

    private static void unloadAvatarBtnClick() {
        avatarAndInterestsForm.unloadAvatarBtnClick();
    }

    public static void uploadAvatarIcon() {
        unloadAvatarBtnClick();
        CooperationWithDialogWindow.openFileDialogWindow(downloadFilePath, downloadFileName);
    }

    public static void avatarAndInterestsFormNextBtnClick() {
        avatarAndInterestsForm.nextBtnClick();
    }

    public static void notReallyNoBtnClick() {
        cookieForm.notReallyNoBtnClick();
    }

    public static void sendToBottomBtnClick() {
        helpForm.sendToBottomBtnClick();
    }

    public static void waitForHelpFormHidden() {
        AqualityServices.getConditionalWait().waitFor(condition -> (!helpForm.isLblTitleOnScreen()));
    }

    private static String getTimerLblText() {
        return registrationPage.geTimerLblText();
    }

    public static void assertIsTimerStartFromZero(String time) {
        Assert.assertEquals(getTimerLblText(), time, "Timer doesn't start from zero.");
    }

    public static void assertIsRegistrationPageOpen() {
        Assert.assertTrue(registrationPage.state().isDisplayed(), "Registration page isn't open.");
    }

    public static void assertIsLoginFormOpen() {
        Assert.assertTrue(loginForm.state().isDisplayed(), "Login form isn't open.");
    }

    public static void assertIsAvatarAndInterestsFormOpen() {
        Assert.assertTrue(avatarAndInterestsForm.state().isDisplayed(), "Avatar and interests form isn't open.");
    }

    public static void assertIsPersonalDetailsFormOpen() {
        Assert.assertTrue(personalDetailsForm.state().isDisplayed(), "Personal details form isn't open.");
    }

    public static void assertIsCookieFormOpen() {
        Assert.assertTrue(cookieForm.state().waitForDisplayed(), "Cookie form isn't open.");
    }

    public static void assertIsCookieFormHidden() {
        Assert.assertFalse(cookieForm.state().isDisplayed(), "Cookie form isn't hidden.");
    }

    public static void assertIsHelpFormOpen() {
        Assert.assertTrue(helpForm.state().isDisplayed(), "Help form isn't open.");
    }

    public static void assertIsHelpFormHidden() {
        Assert.assertFalse(helpForm.state().isDisplayed(), "Help form isn't hidden.");
    }
}
