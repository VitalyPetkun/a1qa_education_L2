package services;

public enum EndPointsJSONPlaceholder {

    POSTS("/posts"),
    POST("/posts/%s"),
    USERS("/users"),
    USER("/users/%s");

    private String point;

    EndPointsJSONPlaceholder(String point) {
         this.point = point;
    }

    public String getPoint() {
        return point;
    }

    public String getChangedPoint(String number) {
        return String.format(point, number);
    }
}
