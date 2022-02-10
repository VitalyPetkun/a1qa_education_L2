package pages.registrationpage.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PropertiesManager;

public class LoginForm extends Form {

    private final ITextBox PASSWORD_TXT = getElementFactory().getTextBox(
            By.xpath("//div[@class='login-form__field-row']//input"), "TextBox password");
    private final ITextBox EMAIL_TXT = getElementFactory().getTextBox(
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'email')]"), "TextBox email");
    private final ITextBox DOMAIN_TXT = getElementFactory().getTextBox(
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'Domain')]"), "TextBox domain");

    private final IComboBox DOMAIN_EXTENSIONS_CMB = getElementFactory().getComboBox(
            By.xpath("//div[@class='dropdown__header']//span"), "ComboBox domain extensions");
    private final IComboBox ITEM_DOMAIN_EXTENSIONS_CMB = getElementFactory().getComboBox(
            By.xpath("//div[@class='dropdown__list-item' and text()='" +
                    PropertiesManager.getTestDataValue("domainExtensions") + "']"),
            "ComboBoxItem domain extensions");

    private final ICheckBox TERMS_AND_CONDITINS_CHK = getElementFactory().getCheckBox(
            By.xpath("//span[@class='checkbox__box']"), "CheckBox terms and conditions");

    private final IButton NEXT_BTN = getElementFactory().getButton(
            By.xpath("//a[@class='button--secondary']"), "Button 'Next'");

    public LoginForm() {
        super(By.xpath("//div[@class='login-form__container']"), "Login form");
    }

    public void passwordTxtInput(String password) {
        PASSWORD_TXT.clearAndType(password);
    }

    public void emailTxtInput() {
        EMAIL_TXT.clearAndType(PropertiesManager.getTestDataValue("email"));
    }

    public void domainTxtInput() {
        DOMAIN_TXT.clearAndType(PropertiesManager.getTestDataValue("domain"));
    }

    public void domainExtensionsCmbClick() {
        DOMAIN_EXTENSIONS_CMB.clickAndWait();
    }

    public void itemDomainExtensionsCmbClick() {
        ITEM_DOMAIN_EXTENSIONS_CMB.click();
    }

    public boolean isTermsAndConditionsChkChecked() {
        return TERMS_AND_CONDITINS_CHK.isChecked();
    }

    public void termsAndConditionsChkCheck() {
        TERMS_AND_CONDITINS_CHK.check();
    }

    public void nextBtnClick() {
        NEXT_BTN.click();
    }
}
