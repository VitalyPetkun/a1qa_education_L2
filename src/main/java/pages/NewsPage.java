package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {

    public NewsPage() {
        super(By.xpath("//div[@id='main_feed']"), "News page");
    }
}
