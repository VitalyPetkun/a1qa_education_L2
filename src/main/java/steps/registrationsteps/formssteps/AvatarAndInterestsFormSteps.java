package steps.registrationsteps.formssteps;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.actions.JsActions;
import org.testng.Assert;
import pages.registrationpage.forms.AvatarAndInterestsForm;
import utils.ConfigManager;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class AvatarAndInterestsFormSteps extends AvatarAndInterestsForm {
    public AvatarAndInterestsFormSteps() {
        super();
    }

    public void checkThreeRandomInterests() {
        int rand;
        checkBoxesInterests = super.getElementFactory().findElements(XPATH_CHECK_BOX_INTEREST, ElementType.CHECKBOX);
        checkBoxesTextInterests = super.getElementFactory().findElements(XPATH_CHECK_BOX_TEXT_INTEREST, ElementType.LABEL);

        if(!(checkBoxesInterests.get(checkBoxesInterests.size()-1).isChecked())){
            checkBoxesInterests.get(checkBoxesInterests.size()-1).check();
        }

        for(int i = 0; i < 3; i++) {
            while (true){
                rand = (int)(Math.random() * checkBoxesInterests.size());
                if(!checkBoxesTextInterests.get(rand).getText().equals("Select all") &&
                        !checkBoxesTextInterests.get(rand).getText().equals("Unselect all")) {
                    checkBoxesInterests.get(rand).check();
                    break;
                }
            }
        }
    }

    public void uploadAvatarIcon() {
        new JsActions(super.getElementFactory().getButton(XPATH_BUTTON_UNLOAD_AVATAR
                ,"Button upload avatar icon"), "Button").click();

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

    public void clickButtonNext() {
        new JsActions(super.getElementFactory().getButton(XPATH_BUTTON_NEXT
                ,"Button next"), "Button").click();
    }

    public void isOpen() {
        Assert.assertTrue(this.isDisplayed());
    }
}
