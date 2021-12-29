package pages.registrationpage.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookieForm extends Form {
    protected final By XPATH_BUTTON_NOT_REALLY_NO =
            By.xpath("//div[@class='cookies']//button[not(contains(@class,'cookies'))]");

    public CookieForm() {
        super(By.xpath("//div[@class='cookies']"), "Cookie form");
    }
}
