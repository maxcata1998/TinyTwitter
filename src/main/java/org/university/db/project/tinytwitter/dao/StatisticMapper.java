package org.university.db.project.tinytwitter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.university.db.project.tinytwitter.entity.BlogStatistics;
import org.university.db.project.tinytwitter.entity.UserStatistic;

import java.util.List;

@Mapper
public interface StatisticMapper {
    @Select("call blogStatistics()")
    @Results(value = {
            @Result(column = "title", property = "title"),
            @Result(column = "author", property = "author"),
            @Result(column = "update_date", property = "lastUpdateDate"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "collects", property = "collects"),
            @Result(column = "comments", property = "comments")
    })
    List<BlogStatistics> getBlogStatistics();

    @Select("call userStatistics()")
    @Results(value = {
            @Result(column = "name", property = "name"),
            @Result(column = "blogs", property = "blogs"),
            @Result(column = "avg_blog_len", property = "avgBlogLen"),
            @Result(column = "comments", property = "comments"),
            @Result(column = "avg_cmt_len", property = "avgCommentLen")
    })
    List<UserStatistic> getUserStatistics();
}
