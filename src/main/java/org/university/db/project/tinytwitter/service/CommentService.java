package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.CommentMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Comment;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements IService<Comment> {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public boolean add(Comment service) {
        return commentMapper.insert(service) ==1;
    }

    @Override
    public boolean update(Comment service) {
        return commentMapper.updateByPrimaryKey(service)==1;
    }

    @Override
    public boolean delete(Comment service) {
        return commentMapper.deleteByPrimaryKey(service.getCommentId())==1;
    }

    @Override
    public List<Comment> find(String pattern) {
        return commentMapper.find(pattern);
    }

    @Override
    public List<Comment> getAll() {
        return commentMapper.selectAll();
    }

    public List<Comment> searchComments(TwitterContext context) {
        return new ArrayList<>();
    }

    public List<Comment> getBlogComments(Blog blog) {
        return commentMapper.getCommentAndUserByBlogId(blog.getBlogId());
    }
}
