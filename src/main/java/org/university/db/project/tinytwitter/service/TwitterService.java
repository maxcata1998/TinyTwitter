package org.university.db.project.tinytwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.BlogMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.User;

import java.util.List;

@Service
public class TwitterService implements IService<Blog> {

    @Autowired
    private BlogMapper blogMapper;

    public TwitterService(){
        //this.blogMapper = blogMapper;

    }

    @Override
    public boolean add(Blog blog) {
         return blogMapper.insert(blog) == 1;
    }

    @Override
    public boolean update(Blog blog) {
        return blogMapper.updateByPrimaryKey(blog) == 1;
    }

    @Override
    public boolean delete(Blog blog) {
        return blogMapper.deleteByPrimaryKey(blog.getBlogId()) == 1;
    }

    @Override
    public List<Blog> find(String pattern) {
        return blogMapper.find(pattern);
    }

    public boolean like(User user, Blog blog) {
        return true;
    }

    public List<User> likedUser(Blog blog) {
        return null;
    }
}
