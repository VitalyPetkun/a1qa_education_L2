package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {
    protected final By XPATH_LINK_NEXT_PAGE = By.xpath("//div[@class='view__row']//a");

    protected WelcomePage() {
        super(By.xpath("//button[@class='start__button']"),"Button 'No'");
    }
}
