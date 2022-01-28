package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PropertiesManager;

public class ProfilePage extends Form {

    private final String POST_AUTHOR = "//div[@id='page_wall_posts']/div[contains(@id,'%s')]//div[contains(@class,'PostHeader')]//a[@class='author']";
    private final String POST_TEXT = "//div[@id='page_wall_posts']/div[contains(@id,'%s')]//div[@class='wall_post_text']";
    private final String POST_PHOTO = "//div[@id='page_wall_posts']/div[contains(@id,'%s')]//div[contains(@class,'page')]/a";
    private final String POST_COMMENT = "//div[@id='page_wall_posts']/div[contains(@id,'%s')]" +
            "//div[@class='replies']//div[contains(@id,'post') and contains(@id,'%s')]";
    private final String SHOW_NEXT_REPLIES = "//div[@id='page_wall_posts']/div[contains(@id,'%s')]//div[@class='replies']" +
            "//a[contains(@onclick,'wall.showNextReplies')]//span[contains(@class,'next_label')]";

    public ProfilePage() {
        super(By.xpath("//div[@id='profile']"),"Profile page");
    }

    public String getPostAuthor(String postId) {
        String authorHref = getElementFactory().getLabel(By.xpath(String.format(POST_AUTHOR,postId)),"post author").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeHref"));
        return authorHref.replaceAll("[^0-9]", "");
    }

    public String getPostText(String postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_TEXT, postId)), "post text").getText();
    }

    public String getPostPhoto(String postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_PHOTO, postId)), "post photo").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeDataPhotoId"));
    }

    public String getPostCommentAuthor(String postId, String commentId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_COMMENT, postId, commentId)), "post comment").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeDataAnsweringId"));
    }

    public boolean isShowNextRepliesDisplayed(String postId) {
        return getElementFactory().getLink(By.xpath(String.format(SHOW_NEXT_REPLIES, postId)),
                "show next replies").state().isDisplayed();
    }

    public void showNextRepliesClick(String postId) {
        getElementFactory().getLink(By.xpath(String.format(SHOW_NEXT_REPLIES, postId)),
                "show next replies").click();
    }
}
