package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProfilePage extends Form {

    public ProfilePage() {
        super(By.xpath("//div[@id='profile']"),"Profile page");
    }
}
