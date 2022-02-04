package services;

public enum VkEndPoints {

    WALL_POST("wall.post"),
    WALL_EDIT("wall.edit"),
    WALL_CREATE_COMMENT("wall.createComment"),
    WALL_DELETE("wall.delete"),
    LIKES_IS_LIKED("likes.isLiked");

    private String point;

    VkEndPoints(String point) {
        this.point = point;
    }

    public String getPoint() {
        return point;
    }
}
