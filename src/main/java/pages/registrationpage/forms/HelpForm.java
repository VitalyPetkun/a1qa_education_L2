package pages.registrationpage.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {

    private final IButton SEND_TO_BOTTOM_BTN = getElementFactory().getButton(
            By.xpath("//button[contains(@class,'send-to-bottom')]"), "Button 'Send to bottom'");

    private final ILabel TITLE_LBL = getElementFactory().getLabel(
            By.xpath("//h2[contains(@class,'help-form')]"), "Label title");

    public HelpForm() {
        super(By.xpath("//a[contains(@class,'help')]"), "Help form");
    }

    public void sendToBottomBtnClick() {
        SEND_TO_BOTTOM_BTN.click();
    }

    public boolean isLblTitleOnScreen() {
        return TITLE_LBL.state().isDisplayed();
    }
}
