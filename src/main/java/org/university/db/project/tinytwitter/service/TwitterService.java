package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.User;

import java.util.List;

@Service
public class TwitterService implements IService<Blog> {
    @Override
    public boolean add(Blog blog) {
        return true;
    }

    @Override
    public boolean update(Blog blog) {
        return true;
    }

    @Override
    public boolean delete(Blog blog) {
        return true;
    }

    @Override
    public List<Blog> find(String pattern) {
        return null;
    }

    public boolean like(User user, Blog blog) {
        return true;
    }

    public List<User> likedUser(Blog blog) {
        return null;
    }
}
