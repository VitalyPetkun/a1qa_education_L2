package steps;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import pages.registrationpage.RegistrationPage;
import pages.registrationpage.forms.*;
import utils.ConfigManager;
import utils.CooperationWithDialogWindow;
import java.util.Iterator;
import java.util.Set;

public class RegistrationPageSteps {

    private static final RegistrationPage REGISTRATION_PAGE = new RegistrationPage();
    private static final LoginForm LOGIN_FORM = new LoginForm();
    private static final AvatarAndInterestsForm AVATAR_AND_INTERESTS_FORM = new AvatarAndInterestsForm();
    private static final PersonalDetailsForm PERSONAL_DETAILS_FORM = new PersonalDetailsForm();
    private static final CookieForm COOKIE_FORM = new CookieForm();
    private static final HelpForm HELP_FORM = new HelpForm();

    private static String getRandomPassword() {
        String email = ConfigManager.getTestDataString("email");
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
        LOGIN_FORM.passwordTxtInput(getRandomPassword());
    }

    public static void emailTxtInput() {
        LOGIN_FORM.emailTxtInput();
    }

    public static void domainTxtInput() {
        LOGIN_FORM.domainTxtInput();
    }

    private static void domainExtensionsCmbClick() {
        LOGIN_FORM.domainExtensionsCmbClick();
    }

    public static void domainExtensionsItemClick() {
        domainExtensionsCmbClick();
        LOGIN_FORM.itemDomainExtensionsCmbClick();
    }

    private static boolean isTermsAndConditionsChkChecked() {
        return LOGIN_FORM.isTermsAndConditionsChkChecked();
    }

    public static void termsAndConditionsChkCheck() {
        if (!isTermsAndConditionsChkChecked())
            LOGIN_FORM.termsAndConditionsChkCheck();
    }

    public static void loginFormNextBtnClick() {
        LOGIN_FORM.nextBtnClick();
    }

    private static boolean isInterestsChkChecked(String nameChk) {
        return AVATAR_AND_INTERESTS_FORM.isInterestsChkChecked(nameChk);
    }

    private static int getChkSize() {
        return AVATAR_AND_INTERESTS_FORM.getChkSize();
    }

    private static Set<String> getChkNames() {
        return AVATAR_AND_INTERESTS_FORM.getChkName();
    }

    private static void interestsChkCheck(String nameChk) {
        AVATAR_AND_INTERESTS_FORM.interestsChkCheck(nameChk);
    }

    public static void threeRandomInterestsSelect() {
        int rand;
        int flag;
        int interestsSelect = 0;
        String nameChk;
        Iterator<String> chkIterator;

        if (!isInterestsChkChecked("Unselect all"))
            interestsChkCheck("Unselect all");

        while (interestsSelect != 3) {
            chkIterator = getChkNames().iterator();
            rand = (int) (Math.random() * getChkSize());
            flag = 0;

            while (chkIterator.hasNext()) {
                nameChk = chkIterator.next();

                if (flag == rand) {
                    if (!nameChk.equals("Select all") && !nameChk.equals("Unselect all")) {
                        interestsChkCheck(nameChk);
                        ++interestsSelect;
                        break;
                    }
                }

                ++flag;
            }
        }
    }

    private static void unloadAvatarBtnClick() {
        AVATAR_AND_INTERESTS_FORM.unloadAvatarBtnClick();
    }

    public static void uploadAvatarIcon() {
        unloadAvatarBtnClick();
        CooperationWithDialogWindow.openFileDialogWindow("pathAvatarIcon", "nameAvatarIcon");
    }

    public static void avatarAndInterestsFormNextBtnClick() {
        AVATAR_AND_INTERESTS_FORM.nextBtnClick();
    }

    public static void notReallyNoBtnClick() {
        COOKIE_FORM.notReallyNoBtnClick();
    }

    public static void sendToBottomBtnClick() {
        HELP_FORM.sendToBottomBtnClick();
    }

    public static void waitForHelpFormHidden() {
        AqualityServices.getConditionalWait().waitFor(condition -> (!HELP_FORM.isLblTitleOnScreen()));
    }

    private static String getTimerLblText() {
        return REGISTRATION_PAGE.geTimerLblText();
    }

    public static void assertIsTimerStartFromZero(String time) {
        Assert.assertEquals(getTimerLblText(), time, "Timer doesn't start from zero.");
    }

    public static void assertIsRegistrationPageOpen() {
        Assert.assertTrue(REGISTRATION_PAGE.isDisplayed(), "Registration page isn't open.");
    }

    public static void assertIsLoginFormOpen() {
        Assert.assertTrue(LOGIN_FORM.isDisplayed(), "Login form isn't open.");
    }

    public static void assertIsAvatarAndInterestsFormOpen() {
        Assert.assertTrue(AVATAR_AND_INTERESTS_FORM.isDisplayed(), "Avatar and interests form isn't open.");
    }

    public static void assertIsPersonalDetailsFormOpen() {
        Assert.assertTrue(PERSONAL_DETAILS_FORM.isDisplayed(), "Personal details form isn't open.");
    }

    public static void assertIsCookieFormOpen() {
        Assert.assertTrue(COOKIE_FORM.isDisplayed(), "Cookie form isn't open.");
    }

    public static void assertIsCookieFormHidden() {
        Assert.assertFalse(COOKIE_FORM.isDisplayed(), "Cookie form isn't hidden.");
    }

    public static void assertIsHelpFormOpen() {
        Assert.assertTrue(HELP_FORM.isDisplayed(), "Help form isn't open.");
    }

    public static void assertIsHelpFormHidden() {
        Assert.assertFalse(HELP_FORM.isDisplayed(), "Help form isn't hidden.");
    }
}
