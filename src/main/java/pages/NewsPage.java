package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.forms.HeaderMenu;
import pages.forms.SideBar;

public class NewsPage extends Form {

    private final HeaderMenu HEADER_MENU = new HeaderMenu();
    private final SideBar SIDE_BAR = new SideBar();

    public NewsPage() {
        super(By.xpath("//div[@id='main_feed']"), "News page");
    }
}
