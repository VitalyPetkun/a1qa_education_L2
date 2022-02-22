package pages.registrationpage.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PropertiesManager;

public class LoginForm extends Form {

    private final ITextBox passwordTxt = getElementFactory().getTextBox(
            By.xpath("//div[@class='login-form__field-row']//input"), "TextBox password");
    private final ITextBox emailTxt = getElementFactory().getTextBox(
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'email')]"), "TextBox email");
    private final ITextBox domainTxt = getElementFactory().getTextBox(
            By.xpath("//div[@class='align__cell']//input[contains(@placeholder,'Domain')]"), "TextBox domain");

    private final IComboBox domainExtensionsCmb = getElementFactory().getComboBox(
            By.xpath("//div[@class='dropdown__header']//span"), "ComboBox domain extensions");
    private final IComboBox itemDomainExtensionsCmb = getElementFactory().getComboBox(
            By.xpath("//div[@class='dropdown__list-item' and text()='" +
                    PropertiesManager.getTestDataValue("domainExtensions") + "']"),
            "ComboBoxItem domain extensions");

    private final ICheckBox termsAndConditinsChk = getElementFactory().getCheckBox(
            By.xpath("//span[@class='checkbox__box']"), "CheckBox terms and conditions");

    private final IButton nextBtn = getElementFactory().getButton(
            By.xpath("//a[@class='button--secondary']"), "Button 'Next'");

    public LoginForm() {
        super(By.xpath("//div[@class='login-form__container']"), "Login form");
    }

    public void passwordTxtInput(String password) {
        passwordTxt.clearAndType(password);
    }

    public void emailTxtInput() {
        emailTxt.clearAndType(PropertiesManager.getTestDataValue("email"));
    }

    public void domainTxtInput() {
        domainTxt.clearAndType(PropertiesManager.getTestDataValue("domain"));
    }

    public void domainExtensionsCmbClick() {
        domainExtensionsCmb.clickAndWait();
    }

    public void itemDomainExtensionsCmbClick() {
        itemDomainExtensionsCmb.click();
    }

    public boolean isTermsAndConditionsChkChecked() {
        return termsAndConditinsChk.isChecked();
    }

    public void termsAndConditionsChkCheck() {
        termsAndConditinsChk.check();
    }

    public void nextBtnClick() {
        nextBtn.click();
    }
}
