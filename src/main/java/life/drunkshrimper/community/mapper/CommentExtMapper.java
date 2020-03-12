package life.drunkshrimper.community.mapper;


import life.drunkshrimper.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}