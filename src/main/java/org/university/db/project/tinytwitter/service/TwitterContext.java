package org.university.db.project.tinytwitter.service;

import lombok.Data;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.User;

import java.util.List;
import java.util.Scanner;

@Data
public class TwitterContext {
    private Scanner in;

    private User user;

    private Blog blog;

    private Object updateObj;

    private String blogKeyword = null;

    private List<Blog> blogList = null;

    private BlogSearchContext blogSearchContext = new BlogSearchContext();

    @Data
    public static class BlogSearchContext {
        private String user;

        private String blogTitle;

        private String blogContent;
    }
}
