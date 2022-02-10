package steps;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import pages.registrationpage.RegistrationPage;
import pages.registrationpage.forms.*;
import utils.PropertiesManager;
import utils.CooperationWithDialogWindow;

import java.util.*;

public class RegistrationPageSteps {

    private static final RegistrationPage registrationPage = new RegistrationPage();
    private static final LoginForm loginForm = new LoginForm();
    private static final AvatarAndInterestsForm avatarAndInterestsForm = new AvatarAndInterestsForm();
    private static final PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
    private static final CookieForm cookieForm = new CookieForm();
    private static final HelpForm helpForm = new HelpForm();

    private RegistrationPageSteps() {}

    private static String getRandomPassword() {
        String email = PropertiesManager.getTestDataValue("email");
        char[] emailToArray = email.toCharArray();
        String password = String.valueOf(emailToArray[(int) (Math.random() * (emailToArray.length))]);
        password += String.valueOf(Character.toChars((int) (Math.random() * (91 - 65)) + 65));
        password += String.valueOf(Character.toChars((int) (Math.random() * (58 - 48)) + 48));

        for (int i = 0; i <= 8 + (int) (Math.random() * 10); i++) {
            password += String.valueOf(Character.toChars((int) (Math.random() * (123 - 97)) + 97));
        }

        return password;
    }

    public static void passwordTxtInput() {
        loginForm.passwordTxtInput(getRandomPassword());
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

    private static boolean isInterestsChkChecked(String nameChk) {
        return avatarAndInterestsForm.isInterestsChkChecked(nameChk);
    }

    private static int getChkSize() {
        return avatarAndInterestsForm.getChkSize();
    }

    private static Set<String> getChkNames() {
        return avatarAndInterestsForm.getChkName();
    }

    private static void interestsChkCheck(String nameChk) {
        avatarAndInterestsForm.interestsChkCheck(nameChk);
    }

    public static void threeRandomInterestsSelect() {
        int rand;
        List<Integer> randInterests = new ArrayList<>();
        int flag;
        String nameChk;
        Iterator<String> chkIterator;

        if (!isInterestsChkChecked("Unselect all"))
            interestsChkCheck("Unselect all");

        while (randInterests.size() != 3) {
            chkIterator = getChkNames().iterator();
            rand = (int) (Math.random() * getChkSize());

            if (!randInterests.contains(rand)) {
                flag = 0;

                while (chkIterator.hasNext()) {
                    nameChk = chkIterator.next();

                    if (flag == rand) {
                        if (!nameChk.equals("Select all") && !nameChk.equals("Unselect all")) {
                            interestsChkCheck(nameChk);
                            break;
                        }
                    }

                    ++flag;
                }

                randInterests.add(rand);
            }
        }
    }

    private static void unloadAvatarBtnClick() {
        avatarAndInterestsForm.unloadAvatarBtnClick();
    }

    public static void uploadAvatarIcon() {
        unloadAvatarBtnClick();
        CooperationWithDialogWindow.openFileDialogWindow("pathAvatarIcon", "nameAvatarIcon");
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
