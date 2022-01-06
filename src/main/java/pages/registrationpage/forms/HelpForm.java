package pages.registrationpage.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.actions.JsActions;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {

    private final IButton btnSendToBottom = this.getElementFactory().getButton(
            By.xpath("//button[contains(@class,'send-to-bottom')]"), "Button 'Send to bottom'");

    private final ILabel lblTitle = this.getElementFactory().getLabel(
            By.xpath("//h2[contains(@class,'help-form')]"), "Lable title");

    public HelpForm() {
        super(By.xpath("//a[contains(@class,'help')]"), "Link help");
    }

    public void btnSendToBottomClick() {
        this.btnSendToBottom.click();
    }

    public boolean isLblTitleOnScreen() {
        return new JsActions(this.lblTitle, ElementType.LABEL.name()).isElementOnScreen();
    }
}
