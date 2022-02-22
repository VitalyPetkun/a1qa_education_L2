package pages.registrationpage;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class RegistrationPage extends Form {

    private final ILabel timerLbl = getElementFactory().getLabel(
            By.xpath("//div[contains(@class,'timer')]"), "Label timer");

    public RegistrationPage() {
        super(By.xpath("//div[@class='game view']"), "Registration page");
    }

    public String geTimerLblText() {
        return timerLbl.getText();
    }
}
