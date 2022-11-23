package DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import Model.Blog;

@Mapper
public interface SearchBlog {
  @Select("select * from blog where type LIKE #{title}")
  List<Blog> getBlog(@Param("title") String title);

}
