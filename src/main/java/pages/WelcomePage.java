package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    private final ITextBox loginTxt = getElementFactory().getTextBox(By.xpath("//input[@id='index_email']"),
            "Login");
    private final ITextBox passwordTxt = getElementFactory().getTextBox(By.xpath("//input[@id='index_pass']"),
            "Password");

    private final IButton signInBtn = getElementFactory().getButton(By.xpath("//button[@id='index_login_button']"),
            "Sign in");

    public WelcomePage() {
        super(By.xpath("//div[@id='index_login']"), "Welcome page");
    }

    public void loginTxtInput(String login) {
        loginTxt.clearAndType(login);
    }

    public void passwordTxtInput(String password) {
        passwordTxt.clearAndType(password);
    }

    public void signInBtnClick() {
        signInBtn.clickAndWait();
    }
}
