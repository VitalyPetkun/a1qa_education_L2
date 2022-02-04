package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static services.VkParameters.*;

public class ProfilePage extends Form {

    private final String POST = "//div[@id='page_wall_posts']/div[contains(@id,'%d')]";
    private final String POST_AUTHOR_LOCATOR = String.format("%s//a[@class='author']", POST);
    private final String POST_TEXT_LOCATOR = String.format("%s//div[contains(@class,'post_text')]", POST);
    private final String POST_PHOTO_LOCATOR = String.format("%s//a[contains(@class,'page')]", POST);
    private final String COMMENT_LOCATOR = String.format("%s//div[contains(@id,'post')]", POST);
    private final String SHOW_NEXT_REPLIES_LOCATOR = String.format("%s//a[contains(@onclick,'wall.showNextReplies')]", POST);
    private final String POST_LIKE_LOCATOR = String.format("%s//div[contains(@class,'PostButtonReactions--post')]", POST);

    private IButton showNextReplices;
    private ILabel postPhoto;

    public ProfilePage() {
        super(By.xpath("//div[contains(@class,'ProfileActions')]"), "Profile page");
    }

    public String getPostAuthor(int postId) {
        String authorHref = getElementFactory().getLabel(By.xpath(String.format(POST_AUTHOR_LOCATOR, postId)), "post author").
                getAttribute(HREF.getHref());
        return authorHref.replaceAll("[^0-9]", "");
    }

    public String getPostText(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST_TEXT_LOCATOR, postId)), "post text").getText();
    }

    public String getPostPhoto(int postId) {
        postPhoto = getElementFactory().getLabel(By.xpath(String.format(POST_PHOTO_LOCATOR, postId)), "post photo");
        return postPhoto.getAttribute(DATA_PHOTO_ID.getDataPhotoId());
    }

    public String getCommentAuthorId(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(COMMENT_LOCATOR, postId)),
                "post comment").getAttribute(DATA_ANSWERING_ID.getDataAnsweringId());
    }

    public boolean isShowNextRepliesDisplayed(int postId) {
        showNextReplices = getElementFactory().getButton(By.xpath(String.format(SHOW_NEXT_REPLIES_LOCATOR, postId)),
                "show next replies");
        showNextReplices.focus();
        return showNextReplices.state().isDisplayed();
    }

    public void showNextRepliesClick() {
        showNextReplices.click();
    }

    public void postLikeClick(int postId) {
        getElementFactory().getButton(By.xpath(String.format(POST_LIKE_LOCATOR, postId)), "post like").clickAndWait();
    }

    public boolean isPostDisplayed(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(POST, postId)), "post").state().isDisplayed();
    }
}
