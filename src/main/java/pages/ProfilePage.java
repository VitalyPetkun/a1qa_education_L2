package pages;

import aquality.selenium.elements.actions.JsActions;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PropertiesManager;

public class ProfilePage extends Form {

    private final String POST = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]";
    private final String POST_AUTHOR_LOCATOR = String.format("%s//div[contains(@class,'PostHeader')]//a[@class='author']", POST);
    private final String POST_TEXT_LOCATOR = String.format("%s//div[contains(@class,'wall_post_text')]", POST);
    private final String POST_PHOTO_LOCATOR = String.format("%s//div[contains(@class,'page')]/a", POST);
    private final String COMMENT_LOCATOR = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]//div[@class='replies']//div[contains(@id,'post') and contains(@id,'%d')]";
    private final String SHOW_NEXT_REPLIES_LOCATOR = String.format("%s//div[@class='replies']//a[contains(@onclick,'wall.showNextReplies')]", POST);
    private final String POST_LIKE_LOCATOR = String.format("%s//div[contains(@class,'PostBottomAction') and contains(@class,'PostButtonReactions--post')]", POST);

    private IButton showNextReplices;

    public ProfilePage() {
        super(By.xpath("//div[contains(@class,'ProfileActions')]"), "Profile page");
    }

    public String getPostAuthor(int postId) {
        String authorHref = getElementFactory().getLabel(By.xpath(String.format(POST_AUTHOR_LOCATOR, postId)), "post author").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeHref"));
        return authorHref.replaceAll("[^0-9]", "");
    }

    public String getPostText(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_TEXT_LOCATOR, postId)), "post text").getText();
    }

    public String getPostPhoto(int postId) {
        ILabel postPhoto = getElementFactory().getLabel(By.xpath(String.format(POST_PHOTO_LOCATOR, postId)), "post photo");
        return postPhoto.getAttribute(PropertiesManager.getTestDataValue("postAttributeDataPhotoId"));
    }

    public String getCommentAuthor(int postId, int commentId) {
        return getElementFactory().getLabel(By.xpath(String.format(COMMENT_LOCATOR, postId, commentId)), "post comment").
                getAttribute(PropertiesManager.getTestDataValue("postAttributeDataAnsweringId"));
    }

    public boolean isShowNextRepliesDisplayed(int postId) {
        showNextReplices = getElementFactory().getButton(By.xpath(String.format(SHOW_NEXT_REPLIES_LOCATOR, postId)),
                "show next replies");
        showNextReplices.getMouseActions().moveMouseToElement();
        return showNextReplices.state().isDisplayed();
    }

    public void showNextRepliesClick() {
        showNextReplices.click();
    }

    public void postLikeClick(int postId) {
        getElementFactory().getButton(By.xpath(String.format(POST_LIKE_LOCATOR, postId)), "post like").clickAndWait();
    }

    public boolean isPostDelete(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST, postId)), "post").state().isDisplayed();
    }
}
