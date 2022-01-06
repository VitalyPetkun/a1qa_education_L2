package steps;

import org.testng.Assert;
import pages.registrationpage.RegistrationPage;
import pages.registrationpage.forms.AvatarAndInterestsForm;
import pages.registrationpage.forms.LoginForm;
import pages.registrationpage.forms.PersonalDetailsForm;
import utils.ConfigManager;
import utils.CooperationWithDialogWindow;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RegistrationPageSteps {

    private static final RegistrationPage registrationPage = new RegistrationPage();
    private static final LoginForm loginForm = new LoginForm();
    private static final AvatarAndInterestsForm avatarAndInterestsForm = new AvatarAndInterestsForm();
    private static final PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();

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

    private static boolean isCheckBoxInterestsChecked(int index) {
        return avatarAndInterestsForm.isCheckBoxInterestsChecked(index);
    }

    private static int getListCheckBoxInterestsSize() {
        return avatarAndInterestsForm.getListCheckBoxInterestsSize();
    }

    private static String getCheckBoxTextInterestsName(int index) {
        return avatarAndInterestsForm.getCheckBoxTextInterestsName(index);
    }

    private static void checkBoxInterestsCheck(int index) {
        avatarAndInterestsForm.checkBoxInterestsCheck(index);
    }

    public static void threeRandomInterestsSelect() {
        int rand;

        for(int index = 0; index <= getListCheckBoxInterestsSize()-1; index++) {
            if (getCheckBoxTextInterestsName(index).equals("Unselect all") &&
                    !isCheckBoxInterestsChecked(index)){
                checkBoxInterestsCheck(index);
            }
        }

        for(int i = 0; i < 3; i++) {
            while (true){
                rand = (int)(Math.random() * getListCheckBoxInterestsSize());
                if(!getCheckBoxTextInterestsName(rand).equals("Select all") &&
                        !getCheckBoxTextInterestsName(rand).equals("Unselect all")) {
                    checkBoxInterestsCheck(rand);
                    break;
                }
            }
        }
    }

    private static void btnUnloadAvatarClick() {
        avatarAndInterestsForm.btnUnloadAvatarClick();
    }

    private static void openFileDialogWindow() {
        try {
            Thread.sleep(ConfigManager.getConfigInt("sleepTime"));

            Robot robot = new Robot();
            StringSelection stringSelection = new StringSelection(System.getProperty("user.dir") + "\\" +
                    ConfigManager.getTestDataString("pathAvatarIcon") + "\\" +
                    ConfigManager.getTestDataString("nameAvatarIcon"));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(ConfigManager.getConfigInt("sleepTime"));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void uploadAvatarIcon() {
        btnUnloadAvatarClick();
        CooperationWithDialogWindow.openFileDialogWindow("pathAvatarIcon", "nameAvatarIcon");
    }

    public static void avatarAndInterestsFormBtnNextClick() {
        avatarAndInterestsForm.btnNextClick();
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
