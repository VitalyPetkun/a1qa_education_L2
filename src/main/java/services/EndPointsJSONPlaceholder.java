package services;

public enum EndPointsJSONPlaceholder {

    POSTS("/posts"),
    NINETIETH_POST("/posts/99"),
    WRONG_POST("/posts/150"),
    USERS("/users"),
    FIFTH_USER("/users/5");

    private String point;

    EndPointsJSONPlaceholder(String point) {
         this.point = point;
    }

    public String getPoint() {
        return point;
    }
}
