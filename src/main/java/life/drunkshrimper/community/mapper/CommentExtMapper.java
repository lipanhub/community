package life.drunkshrimper.community.mapper;


import life.drunkshrimper.community.model.Comment;

/**
 * @author 001
 */
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}