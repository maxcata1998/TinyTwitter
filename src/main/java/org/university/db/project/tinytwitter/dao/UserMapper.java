package org.university.db.project.tinytwitter.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.university.db.project.tinytwitter.entity.User;

@Mapper
public interface UserMapper {

    @Select("select count(1) from user where name = #{username}")
    int findUNameCount(String username);

    @Insert("insert into user (name, password, email) values (#{name}, #{password}, #{email})")
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "userId", before = false, resultType =
            Integer.class)
    int insert(User record);

    @Select("select user_id, name, email from user where name = #{username} and password = #{password}")
    @Results(id = "userMapOnlyName", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "name", property = "name"),
            @Result(column = "email", property = "email")
    })
    User findByUNamePwd(String username, String password);
}