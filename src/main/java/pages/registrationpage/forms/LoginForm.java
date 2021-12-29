package pages.registrationpage.forms;

import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.ConfigManager;

public class LoginForm extends Form {
    protected final By XPATH_TXT_BOX_PASSWORD =
            By.xpath("//div[@class='login-form__field-row']//input");
    protected final By XPATH_TXT_BOX_EMAIL =
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'email')]");
    protected final By XPATH_TXT_BOX_DOMAIN =
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'Domain')]");
    protected final By XPATH_COMBO_BOX_DOMAIN_EXTENSIONS =
            By.xpath("//div[@class='dropdown__header']//span");
    protected final By XPATH_COMBO_BOX_ITEM_DOMAIN_EXTENSIONS =
            By.xpath("//div[@class='dropdown__list-item' and text()='" +
                    ConfigManager.getTestDataString("domainExtensions") + "']");
    protected final By XPATH_CHECK_BOX_TERMS_AND_CONDITIONS =
            By.xpath("//span[@class='checkbox__box']");
    protected final By XPATH_BUTTON_NEXT =
            By.xpath("//a[@class='button--secondary']");

    protected ITextBox password;
    protected ITextBox email;
    protected ITextBox domain;
    protected ICheckBox checkBoxTermsAndConditions;

    public LoginForm() {
        super(By.xpath("//div[@class='login-form__container']"),"Login form");
    }
}
