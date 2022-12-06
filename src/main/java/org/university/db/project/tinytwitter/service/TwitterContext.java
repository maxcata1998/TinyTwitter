package org.university.db.project.tinytwitter.service;

import lombok.Data;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.User;

import java.util.Scanner;

@Data
public class TwitterContext {
    private Scanner in;

    private User user;

    private Blog blog;

    private Object updateObj;
}
