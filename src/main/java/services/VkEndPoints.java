package services;

public enum VkEndPoints {

    MY_PROFILE("/id627657327"),
    METHOD("/method/%s"),
    PARAMS("?message=%s"),
    TOKEN("&access_token=%s"),
    VERSION("&v=%s");

    private String point;

    VkEndPoints(String point) {
        this.point = point;
    }

    public String getPoint(String value) {
        return String.format(point,value);
    }
}
