package utils.api;

import static services.VkEndPoints.*;
import static services.VkParameters.*;

public class VkApiUtils {

    private VkApiUtils() {
    }

    public static Response createPost(String message, String accessToken) {
        return ApiUtils.doPost(METHOD.getMethod(WALL_POST.getPoint()).
                concat(MESSAGE.getMessage(message)).
                concat(TOKEN.getToken(accessToken)).
                concat(VERSION.getVersion(CURRENT_VERSION_API.getCurrentVersionApi()))
        );
    }

    public static Response editPost(int postId, String message, int userId, int photoId, String accessToken) {
        return ApiUtils.doPost(METHOD.getMethod(WALL_EDIT.getPoint()).
                concat(POST_ID.getPostId(postId)).
                concat(MESSAGE.getMessage(message)).
                concat(ATTACHMENT_PHOTO.getAttachmentPhoto(userId, photoId)).
                concat(TOKEN.getToken(accessToken)).
                concat(VERSION.getVersion(CURRENT_VERSION_API.getCurrentVersionApi()))
        );
    }

    public static void deletePost(int postId, String accessToken) {
        ApiUtils.doPost(METHOD.getMethod(WALL_DELETE.getPoint()).
                concat(POST_ID.getPostId(postId)).
                concat(TOKEN.getToken(accessToken)).
                concat(VERSION.getVersion(CURRENT_VERSION_API.getCurrentVersionApi()))
        );
    }

    public static Response createComment(int postId, String message, String accessToken) {
        return ApiUtils.doPost(METHOD.getMethod(WALL_CREATE_COMMENT.getPoint()).
                concat(POST_ID.getPostId(postId)).
                concat(MESSAGE.getMessage(message)).
                concat(TOKEN.getToken(accessToken)).
                concat(VERSION.getVersion(CURRENT_VERSION_API.getCurrentVersionApi()))
        );
    }

    public static Response isUserLiked(String objectType, int objectId, String accessToken) {
        return ApiUtils.doGet(METHOD.getMethod(LIKES_IS_LIKED.getPoint()).
                concat(TYPE.getType(objectType)).
                concat(ITEM_ID.getItemId(objectId)).
                concat(TOKEN.getToken(accessToken)).
                concat(VERSION.getVersion(CURRENT_VERSION_API.getCurrentVersionApi()))
        );
    }
}
