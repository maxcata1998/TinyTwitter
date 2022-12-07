package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.CommentMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.Comment;

import java.util.List;

@Service
public class CommentService implements IService<Comment> {
    private CommentMapper commentMapper;
    public CommentService(CommentMapper commentMapper){
        this.commentMapper = commentMapper;
    }
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

    public List<Comment> getBlogComments(Blog blog) {
        return null;
    }
}
