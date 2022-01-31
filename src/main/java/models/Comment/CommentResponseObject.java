package models.Comment;

import lombok.Getter;

@Getter
public class CommentResponseObject {

    private int comment_id;
    private int[] parents_stack;
}
