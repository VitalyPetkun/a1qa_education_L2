package pages.registrationpage;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class RegistrationPage extends Form {
    protected final By XPATH_LABEL_TIMER = By.xpath("//div[contains(@class,'timer')]");

    public RegistrationPage() {
        super(By.xpath("//div[@class='game view']"),"Registration Page");
    }
}
