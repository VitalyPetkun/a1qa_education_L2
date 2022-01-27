package pages.forms;

import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HeaderMenu extends Form {

    private final IComboBox PROFILE_ARROW_CMB = getElementFactory().getComboBox(
            By.xpath("//div[@class='TopNavBtn__profileArrow']"), "Profile arrow");

    private static final TopProfileMenu TOP_PROFILE_MENU = new TopProfileMenu();

    public HeaderMenu() {
        super(By.xpath("//div[@id='page_header_cont']"),"Header menu");
    }

    public void profileArrowCmbClick() {
        PROFILE_ARROW_CMB.clickAndWait();
    }
}
