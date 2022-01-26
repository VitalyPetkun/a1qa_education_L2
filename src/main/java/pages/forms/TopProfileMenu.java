package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TopProfileMenu extends Form {

    private final IButton SIGN_OUT_BTN = getElementFactory().getButton(By.xpath("//a[@id='top_logout_link']//span"),
            "Button sign out");

    public TopProfileMenu() {
        super(By.xpath("//div[@id='top_profile_menu']"),"Top profile menu");
    }

    protected void signOutBtnClick() {
        SIGN_OUT_BTN.click();
    }
}
