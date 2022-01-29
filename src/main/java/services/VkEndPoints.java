package services;

public enum VkEndPoints {

    METHOD("/method/%s?"),
    PARAM_POST_ID("post_id=%s&"),
    PARAM_MESSAGE("message=%s&"),
    PARAM_ATTACHMENT_PHOTO("attachments=photo%s_%s&"),
    PARAM_TYPE("type=%s&"),
    PARAM_ITEM_ID("item_id=%s&"),
    TOKEN("access_token=%s&"),
    VERSION("v=%s");

    private String point;

    VkEndPoints(String point) {
        this.point = point;
    }

    public String getPoint(String firstValue, String secondValue) {
        return String.format(point,firstValue,secondValue);
    }
}
