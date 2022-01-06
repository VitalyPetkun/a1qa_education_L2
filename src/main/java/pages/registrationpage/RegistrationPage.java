package pages.registrationpage;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.actions.JsActions;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class RegistrationPage extends Form {

    private final ILabel labelTimer = this.getElementFactory().getLabel(
            By.xpath("//div[contains(@class,'timer')]"),"Label timer");

    public RegistrationPage() {
        super(By.xpath("//div[@class='game view']"),"Registration page content");
    }

    public String getLabelTimerText() {
        return new JsActions(labelTimer, ElementType.LABEL.name()).getElementText();
    }
}
