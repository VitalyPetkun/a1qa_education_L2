package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SideBar extends Form {

    private final IButton MY_PROFILE_BTN = getElementFactory().getButton(By.xpath("//li[@id='l_pr']"),
            "My profile");

    public SideBar() {
        super(By.xpath("//div[@id='side_bar']"), "Side bar");
    }

    public void myProfileBtnClick() {
        MY_PROFILE_BTN.click();
    }
}
