package pages.registrationpage.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.actions.JsActions;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.ConfigManager;

public class LoginForm extends Form {

    private final ITextBox password = this.getElementFactory().getTextBox(
            By.xpath("//div[@class='login-form__field-row']//input"), "TextBox password");
    private final ITextBox email = this.getElementFactory().getTextBox(
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'email')]"), "TextBox email");
    private final ITextBox domain = this.getElementFactory().getTextBox(
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'Domain')]"), "TextBox domain");

    private final IComboBox comboBoxDomainExtensions = this.getElementFactory().getComboBox(
            By.xpath("//div[@class='dropdown__header']//span"), "ComboBox domain extensions");
    private final IComboBox comboBoxItemDomainExtensions = this.getElementFactory().getComboBox(
            By.xpath("//div[@class='dropdown__list-item' and text()='" +
                    ConfigManager.getTestDataString("domainExtensions") + "']"),
            "ComboBoxItem domain extensions");

    private final ICheckBox checkBoxTermsAndConditions = this.getElementFactory().getCheckBox(
            By.xpath("//span[@class='checkbox__box']"), "CheckBox terms and conditions");

    private final IButton btnNext = this.getElementFactory().getButton(
            By.xpath("//a[@class='button--secondary']"), "Button 'Next'");

    public LoginForm() {
        super(By.xpath("//div[@class='login-form__container']"),"Login form");
    }

    public void passwordScrollIntoView() {
        new JsActions(password, ElementType.TEXTBOX.name()).scrollIntoView();
    }

    public void passwordInput() {
        password.clearAndType(ConfigManager.getTestDataString("password"));
    }

    public void emailInput() {
        email.clearAndType(ConfigManager.getTestDataString("email"));
    }

    public void domainInput() {
        domain.clearAndType(ConfigManager.getTestDataString("domain"));
    }

    public void domainExtensionsClick() {
        new JsActions(comboBoxDomainExtensions, ElementType.COMBOBOX.name()).clickAndWait();
    }

    public void domainExtensionsItemClick() {
        comboBoxItemDomainExtensions.click();
    }

    public boolean isTermsAndConditionsChecked() {
        return checkBoxTermsAndConditions.isChecked();
    }

    public void termsAndConditionsCheck() {
        checkBoxTermsAndConditions.check();
    }

    public void btnNextClick() {
        btnNext.click();
    }
}
