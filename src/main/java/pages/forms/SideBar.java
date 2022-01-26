package pages.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SideBar extends Form{

    public SideBar() {
        super(By.xpath("//div[@id='side_bar']"),"Side bar");
    }
}
