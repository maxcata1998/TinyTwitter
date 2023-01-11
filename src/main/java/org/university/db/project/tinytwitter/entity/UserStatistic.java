package org.university.db.project.tinytwitter.entity;

import lombok.Data;

@Data
public class UserStatistic {
    private String name;

    private int blogs;

    private float avgBlogLen;

    private int comments;

    private float avgCommentLen;
}
