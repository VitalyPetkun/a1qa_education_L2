package pages.registrationpage.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookieForm extends Form {

    private final IButton NOT_REALLY_NO_BTN = getElementFactory().getButton(
            By.xpath("//div[@class='cookies']//button[not(contains(@class,'cookies'))]"),
            "Button 'Not Really, No'");

    public CookieForm() {
        super(By.xpath("//div[@class='cookies']"), "Cookie form");
    }

    public void notReallyNoBtnClick() {
        NOT_REALLY_NO_BTN.click();
    }
}
