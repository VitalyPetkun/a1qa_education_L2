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

    private static final String downloadFilePath = PropertiesManager.getConfigValue("avatarIconPath");
    private static final String downloadFileName = PropertiesManager.getTestDataValue("avatarIconName");
    private static final String unselectAllInterest = "Unselect all";
    private static final String selectAllInterest = "Select all";
    private static final int randomInterestsNumber = Integer.parseInt(PropertiesManager.getTestDataValue("randomInterestsNumber"));

    private RegistrationPageSteps() {
    }

    public static void inputPasswordTxt() {
        loginForm.inputPasswordTxt(RegistrationPageUtils.getRandomPassword());
    }

    public static void inputEmailTxt() {
        loginForm.inputEmailTxt();
    }

    public static void inputDmainTxt() {
        loginForm.inputDmainTxt();
    }

    public static void clickDomainExtensionsItem() {
        loginForm.clickDomainExtensionsCmb();
        loginForm.clickItemDomainExtensionsCmb();
    }

    public static void checkTermsAndConditionsChk() {
        if (!loginForm.isCheckedTermsAndConditionsChk())
            loginForm.checkTermsAndConditionsChk();
    }

    public static void clickLoginFormNextBtn() {
        loginForm.clickNextBtn();
    }

    public static void selectThreeRandomInterests() {
        Object[] interestsName = avatarAndInterestsForm.getNameInterests().stream().toArray();
        String[] unwantedInterests = {unselectAllInterest, selectAllInterest};
        List<Integer> randInterests = RegistrationPageUtils.getRandomList(
                randomInterestsNumber, avatarAndInterestsForm.getSizeInterests(), interestsName, unwantedInterests);

        avatarAndInterestsForm.checkInterest(unselectAllInterest);

        for (int i = 0; i < randInterests.size(); i++) {
            avatarAndInterestsForm.checkInterest(String.valueOf(interestsName[randInterests.get(i)]));
        }
    }

    public static void uploadAvatarIcon() {
        avatarAndInterestsForm.clickUnloadAvatarBtn();
        CooperationWithDialogWindow.openFileDialogWindow(downloadFilePath, downloadFileName);
    }

    public static void clickAvatarAndInterestsFormNextBtn() {
        avatarAndInterestsForm.clickNextBtn();
    }

    public static void clickNotReallyNoBtn() {
        cookieForm.clickNotReallyNoBtn();
    }

    public static void clickSendToBottomBtn() {
        helpForm.clickSendToBottomBtn();
    }

    public static void waitForHelpFormHidden() {
        AqualityServices.getConditionalWait().waitFor(condition -> (!helpForm.isDisplayedTitleLbl()));
    }

    private static String getTextTimerLbl() {
        return registrationPage.getTextTimerLbl();
    }

    public static void assertIsStartTimer(String time) {
        Assert.assertEquals(getTextTimerLbl(), time, "Timer isn't correct.");
    }

    public static void assertIsOpenRegistrationPage() {
        Assert.assertTrue(registrationPage.state().isDisplayed(), "Registration page isn't open.");
    }

    public static void assertIsOpenLoginForm() {
        Assert.assertTrue(loginForm.state().isDisplayed(), "Login form isn't open.");
    }

    public static void assertIsOpenAvatarAndInterestsForm() {
        Assert.assertTrue(avatarAndInterestsForm.state().isDisplayed(), "Avatar and interests form isn't open.");
    }

    public static void assertIsOpenPersonalDetailsForm() {
        Assert.assertTrue(personalDetailsForm.state().isDisplayed(), "Personal details form isn't open.");
    }

    public static void assertIsOpenCookieForm() {
        Assert.assertTrue(cookieForm.state().waitForDisplayed(), "Cookie form isn't open.");
    }

    public static void assertIsCloseCookieForm() {
        Assert.assertFalse(cookieForm.state().isDisplayed(), "Cookie form isn't close.");
    }

    public static void assertIsOpenHelpForm() {
        Assert.assertTrue(helpForm.state().isDisplayed(), "Help form isn't open.");
    }

    public static void assertIsHiddenHelpForm() {
        Assert.assertFalse(helpForm.state().isDisplayed(), "Help form isn't hidden.");
    }
}
