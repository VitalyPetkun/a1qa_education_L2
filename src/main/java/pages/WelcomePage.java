package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    private final ITextBox LOGIN_TXT = getElementFactory().getTextBox(By.xpath("//input[@id='index_email']"),
            "Login");
    private final ITextBox PASSWORD_TXT = getElementFactory().getTextBox(By.xpath("//input[@id='index_pass']"),
            "Password");

    private final IButton SIGN_IN_BTN = getElementFactory().getButton(By.xpath("//button[@id='index_login_button']"),
            "Sign in");

    public WelcomePage() {
        super(By.xpath("//div[@id='index_login']"), "Welcome page");
    }

    public void loginTxtInput(String login) {
        LOGIN_TXT.clearAndType(login);
    }

    public void passwordTxtInput(String password) {
        PASSWORD_TXT.clearAndType(password);
    }

    public void signInBtnClick() {
        SIGN_IN_BTN.click();
    }
}
