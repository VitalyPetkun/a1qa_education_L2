package pages.registrationpage.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {

    private final IButton sendToBottomBtn = getElementFactory().getButton(
            By.xpath("//button[contains(@class,'send-to-bottom')]"), "Button 'Send to bottom'");

    private final ILabel titleLbl = getElementFactory().getLabel(
            By.xpath("//h2[contains(@class,'help-form')]"), "Label title");

    public HelpForm() {
        super(By.xpath("//a[contains(@class,'help')]"), "Help form");
    }

    public void clickSendToBottomBtn() {
        sendToBottomBtn.click();
    }

    public boolean isDisplayedTitleLbl() {
        return titleLbl.state().isDisplayed();
    }
}
