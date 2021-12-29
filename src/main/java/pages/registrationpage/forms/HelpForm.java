package pages.registrationpage.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {
    protected final By XPATH_BUTTON_SEND_TO_BOTTOM = By.xpath("//button[contains(@class,'send-to-bottom')]");
    protected final By XPATH_LABEL_TITLE = By.xpath("//h2[contains(@class,'help-form')]");

    public HelpForm() {
        super(By.xpath("//a[contains(@class,'help')]"), "Link help");
    }
}
