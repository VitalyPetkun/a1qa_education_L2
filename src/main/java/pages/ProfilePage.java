package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PropertiesManager;

public class ProfilePage extends Form {

    private final ILabel FIRST_POST_AUTHOR = getElementFactory().getLabel(
            By.xpath("//div[@id='page_wall_posts']/div[1]//div[contains(@class,'PostHeader')]//a[@class='author']"),
            "first post author");

    private final ILabel FIRST_POST_TITLE = getElementFactory().getLabel(
            By.xpath("//div[@id='page_wall_posts']/div[1]//div[@class='wall_post_text']"), "first post title");

    public ProfilePage() {
        super(By.xpath("//div[@id='profile']"),"Profile page");
    }

    public String getFirstPostAuthor() {
        return FIRST_POST_AUTHOR.getAttribute(PropertiesManager.getTestDataValue("postAttributeHref"));
    }

    public String getFirstPostTitle() {
        return FIRST_POST_TITLE.getText();
    }
}
