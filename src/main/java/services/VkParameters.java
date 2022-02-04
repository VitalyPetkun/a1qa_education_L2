package services;

public enum VkParameters {

    METHOD("/method/"),
    POST_ID("post_id="),
    MESSAGE("message="),
    ATTACHMENT_PHOTO("attachments=photo"),
    TYPE("type="),
    ITEM_ID("item_id="),
    TOKEN("access_token="),
    VERSION("v="),
    HREF("href"),
    DATA_PHOTO_ID("data-photo-id"),
    DATA_ANSWERING_ID("data-answering-id"),
    OBJECT_TYPE("post"),
    CURRENT_VERSION_API("5.131"),
    LIKED("1"),
    PHOTO_ID("457246431");

    private String parameter;

    VkParameters(String parameter) {
        this.parameter = parameter;
    }

    public String getMethod(String method) {
        return parameter.concat(method).concat("?");
    }

    public String getPostId(int postId) {
        return parameter.concat(String.valueOf(postId)).concat("&");
    }

    public String getMessage(String message) {
        return parameter.concat(message).concat("&");
    }

    public String getAttachmentPhoto(int userId, int photoId) {
        return parameter.concat(String.valueOf(userId)).concat("_").concat(String.valueOf(photoId)).concat("&");
    }

    public String getType(String type) {
        return parameter.concat(type).concat("&");
    }

    public String getItemId(int itemId) {
        return parameter.concat(String.valueOf(itemId)).concat("&");
    }

    public String getToken(String token) {
        return parameter.concat(token).concat("&");
    }

    public String getVersion(String version) {
        return parameter.concat(version);
    }

    public String getHref() {
        return parameter;
    }

    public String getDataPhotoId() {
        return parameter;
    }

    public String getDataAnsweringId() {
        return parameter;
    }

    public String getObjectType() {
        return parameter;
    }

    public String getCurrentVersionApi() {
        return parameter;
    }

    public int getLiked() {
        return Integer.parseInt(parameter);
    }

    public int getPhotoId() {
        return Integer.parseInt(parameter);
    }
}
