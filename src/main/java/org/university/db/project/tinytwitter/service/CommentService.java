package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Comment;

import java.util.List;

@Service
public class CommentService implements IService<Comment> {
    @Override
    public boolean add(Comment service) {
        return false;
    }

    @Override
    public boolean update(Comment service) {
        return false;
    }

    @Override
    public boolean delete(Comment service) {
        return false;
    }

    @Override
    public List<Comment> find(String pattern) {
        return null;
    }

    public List<Comment> getBlogComments(Blog blog) {
        return null;
    }
}
