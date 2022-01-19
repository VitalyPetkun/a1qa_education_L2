package models;

import java.util.List;

public class Posts {

    private List<PostModelForResponse> posts;

    public List<PostModelForResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModelForResponse> posts) {
        this.posts = posts;
    }
}
