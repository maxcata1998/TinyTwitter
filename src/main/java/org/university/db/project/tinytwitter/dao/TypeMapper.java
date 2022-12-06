package org.university.db.project.tinytwitter.dao;

import java.util.List;
import org.university.db.project.tinytwitter.entity.Type;

public interface TypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table type
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer typeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table type
     *
     * @mbggenerated
     */
    int insert(Type record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table type
     *
     * @mbggenerated
     */
    Type selectByPrimaryKey(Integer typeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table type
     *
     * @mbggenerated
     */
    List<Type> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table type
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Type record);
}