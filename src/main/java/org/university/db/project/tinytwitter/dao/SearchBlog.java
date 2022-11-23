package org.university.db.project.tinytwitter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import org.university.db.project.tinytwitter.model.Blog;

@Mapper
public interface SearchBlog {
    @Select("select * from blog where type LIKE #{title}")
    List<Blog> getBlog(@Param("title") String title);
}
