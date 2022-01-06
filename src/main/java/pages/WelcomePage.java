package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    private final ILink linkNextPage = this.getElementFactory().getLink(
            By.xpath("//div[@class='view__row']//a"), "Link next page");

    public WelcomePage() {
        super(By.xpath("//button[@class='start__button']"),"Button 'No'");
    }

    public void linkNextPageClick() {
        linkNextPage.click();
    }
}
