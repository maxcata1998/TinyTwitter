package org.university.db.project.tinytwitter.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Comment;
import org.university.db.project.tinytwitter.entity.User;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment (`date`, blog_id, create_date, author, content) " +
            "values (#{date}, #{blogId}, #{createDate}, #{author.userId}, #{content})")
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "commentId", before = false, resultType =
            Integer.class)
    int insert(Comment record);

    @Update("update comment set `date` = #{date}, blog_id = #{blogId}, create_date = #{createDate}," +
            "author = #{author.userId}, content = #{content} " +
            "where comment_id = #{commentId}")
    int updateByPrimaryKey(Comment record);

    @Delete("delete from comment where comment_id = #{commentId}")
    int deleteByPrimaryKey(Integer commentId);

    @Select("select *")
    @Results(id = "userMapOnlyName", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "name", property = "name")
    })
    @Deprecated
    User fake();

    @Select("select comment_id, `date`, blog_id, create_date, author, content, user_id, name from comment " +
            "left join user on comment.author = user.user_id" +
            "where blog_id = #{blogId} " +
            "order by update_date desc")
    @Results(id = "commentMap", value = {
            @Result(column = "blog_id", property = "blogId"),
            @Result(column = "title", property = "title"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "update_date", property = "updateDate"),
            @Result(column = "content", property = "content"),
            @Result(property = "author", one = @One(columnPrefix = "user_", resultMap = "userMapOnlyName"))
    })
    List<Comment> getCommentAndUserByBlogId(int blogId);
}