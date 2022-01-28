package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PropertiesManager;

public class ProfilePage extends Form {

    private final ILabel FIRST_POST_AUTHOR = getElementFactory().getLabel(
            By.xpath("//div[@id='page_wall_posts']/div[1]//div[contains(@class,'PostHeader')]//a[@class='author']"),
            "first post author");

    private final ILabel FIRST_POST_TEXT = getElementFactory().getLabel(
            By.xpath("//div[@id='page_wall_posts']/div[1]//div[@class='wall_post_text']"), "first post text");

    private final ILabel FIRST_POST_PHOTO = getElementFactory().getLabel(
            By.xpath("//div[@id='page_wall_posts']/div[1]//div[contains(@class,'page')]/a"), "first post photo");

    public ProfilePage() {
        super(By.xpath("//div[@id='profile']"),"Profile page");
    }

    public String getFirstPostAuthor() {
        return FIRST_POST_AUTHOR.getAttribute(PropertiesManager.getTestDataValue("postAttributeHref")).
                replaceAll("[^0-9]", "");
    }

    public String getFirstPostText() {
        return FIRST_POST_TEXT.getText();
    }

    public String getFirstPostPhoto() {
        return FIRST_POST_PHOTO.getAttribute(PropertiesManager.getTestDataValue("postAttributeDataPhotoId"));
    }
}
