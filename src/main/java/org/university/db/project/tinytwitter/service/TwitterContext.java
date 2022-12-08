package org.university.db.project.tinytwitter.service;

import lombok.Data;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Comment;
import org.university.db.project.tinytwitter.entity.User;

import java.util.List;
import java.util.Scanner;

@Data
public class TwitterContext {
    private Scanner in;

    private User user;

    private Blog blog;

    private Comment comment;

    private Object updateObj;

    private String blogKeyword = null;

    private List<Blog> blogList = null;

    private final BlogSearchContext blogSearchContext = new BlogSearchContext();

    private final CommentSearchContext commentSearchContext = new CommentSearchContext();

    @Data
    public static class BlogSearchContext {
        private String user;

        private String blogTitle;

        private String blogContent;

        private String blogType;

        private Boolean isLike;

        private Boolean isCollected;

        public void clear() {
            user = blogTitle = blogContent = blogType = null;
            isLike = isCollected = null;
        }
    }

    @Data
    public static class CommentSearchContext {
        private String user;

        private String content;
    }
}
