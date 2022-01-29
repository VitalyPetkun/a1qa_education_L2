package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PropertiesManager;

public class ProfilePage extends Form {

    private final String POST_AUTHOR_LOCATOR = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]//div[contains(@class,'PostHeader')]//a[@class='author']";
    private final String POST_TEXT_LOCATOR = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]//div[@class='wall_post_text']";
    private final String POST_PHOTO_LOCATOR = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]//div[contains(@class,'page')]/a";
    private final String COMMENT_LOCATOR = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]" +
            "//div[@class='replies']//div[contains(@id,'post') and contains(@id,'%d')]";
    private final String SHOW_NEXT_REPLIES_LOCATOR = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]//div[@class='replies']" +
            "//a[contains(@onclick,'wall.showNextReplies')]//span[contains(@class,'next_label')]";
    private final String POST_LIKE_LOCATOR = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]" +
            "//div[contains(@class,'PostBottomAction') and contains(@class,'PostButtonReactions--post')]";

    public ProfilePage() {
        super(By.xpath("//div[@id='profile']"),"Profile page");
    }

    public String getPostAuthor(int postId) {
        String authorHref = getElementFactory().getLabel(By.xpath(String.format(POST_AUTHOR_LOCATOR,postId)),"post author").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeHref"));
        return authorHref.replaceAll("[^0-9]", "");
    }

    public String getPostText(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_TEXT_LOCATOR, postId)), "post text").getText();
    }

    public String getPostPhoto(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_PHOTO_LOCATOR, postId)), "post photo").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeDataPhotoId"));
    }

    public String getCommentAuthor(int postId, int commentId) {
        return getElementFactory().getLabel(By.xpath(String.format(COMMENT_LOCATOR, postId, commentId)), "post comment").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeDataAnsweringId"));
    }

    public boolean isShowNextRepliesDisplayed(int postId) {
        return getElementFactory().getButton(By.xpath(String.format(SHOW_NEXT_REPLIES_LOCATOR, postId)),
                "show next replies").state().isDisplayed();
    }

    public void showNextRepliesClick(int postId) {
        getElementFactory().getButton(By.xpath(String.format(SHOW_NEXT_REPLIES_LOCATOR, postId)),
                "show next replies").click();
    }

    public void postLikeClick(int postId) {
        getElementFactory().getButton(By.xpath(String.format(POST_LIKE_LOCATOR, postId)),"post like").click();
    }
}
