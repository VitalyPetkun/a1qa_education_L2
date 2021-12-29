package steps.registrationsteps.formssteps;

import aquality.selenium.elements.actions.JsActions;
import org.testng.Assert;
import pages.registrationpage.forms.LoginForm;
import utils.ConfigManager;

public class LoginFormSteps extends LoginForm {
    public LoginFormSteps() {
        super();
    }

    public void inputPassword() {
        password = super.getElementFactory().getTextBox(XPATH_TXT_BOX_PASSWORD,"TextBox password");
        new JsActions(password,"TextBox").scrollIntoView();

        password.clearAndType(ConfigManager.getTestDataString("password"));
    }

    public void inputEmail() {
        email = super.getElementFactory().getTextBox(XPATH_TXT_BOX_EMAIL,"TextBox email");
        new JsActions(email,"TextBox").scrollIntoView();

        email.clearAndType(ConfigManager.getTestDataString("email"));
    }

    public void inputDomain() {
        domain = super.getElementFactory().getTextBox(XPATH_TXT_BOX_DOMAIN,"TextBox domain");
        new JsActions(domain,"TextBox").scrollIntoView();

        domain.clearAndType(ConfigManager.getTestDataString("domain"));
    }

    public void selectDomainExtensions() {
        new JsActions(super.getElementFactory().getComboBox(XPATH_COMBO_BOX_DOMAIN_EXTENSIONS
                ,"ComboBox domain extensions"), "ComboBox").clickAndWait();
        new JsActions(super.getElementFactory().getComboBox(XPATH_COMBO_BOX_ITEM_DOMAIN_EXTENSIONS
                , "ComboBoxItem domain extensions"), "ComboBoxItem").clickAndWait();
    }

    public void checkTermsAndConditions() {
        checkBoxTermsAndConditions = super.getElementFactory().getCheckBox(XPATH_CHECK_BOX_TERMS_AND_CONDITIONS
                ,"CheckBox terms and conditions");
        new JsActions(checkBoxTermsAndConditions, "CheckBox").scrollIntoView();

        if(!checkBoxTermsAndConditions.isChecked()){
            checkBoxTermsAndConditions.check();
        }
    }

    public void clickBtnNext() {
        new JsActions(super.getElementFactory().getButton(XPATH_BUTTON_NEXT
                ,"Button next"), "Button").click();
    }

    public void isOpen() {
        Assert.assertTrue(this.isDisplayed());
    }
}
