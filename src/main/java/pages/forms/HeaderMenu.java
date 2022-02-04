package pages.forms;

import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HeaderMenu extends Form {

    private final IComboBox profileArrowCmb = getElementFactory().getComboBox(
            By.xpath("//div[@class='TopNavBtn__profileArrow']"), "Profile arrow");

    public HeaderMenu() {
        super(By.xpath("//div[@id='page_header_cont']"), "Header menu");
    }

    public void profileArrowCmbClick() {
        profileArrowCmb.clickAndWait();
    }
}
