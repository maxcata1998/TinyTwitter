package org.university.db.project.tinytwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.BlogMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService implements IService<Blog> {

    private final BlogMapper blogMapper;

    @Autowired
    public BlogService(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public boolean add(Blog blog) {
        blogMapper.insert(blog);
        blog.setBlogId(blogMapper.getIdByTitle(blog.getTitle()));
        return true;
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

    @Override
    public List<Blog> getAll() {
        return null;
    }

    public boolean like(User user, Blog blog) {
        return true;
    }

    public List<User> likedUser(Blog blog) {
        return null;
    }

    public List<Blog> searchBlog(TwitterContext context) {
        if (context.getBlogSearchContext().getBlogTitle() == null) {
            return blogMapper.selectAll();
        } else {
            return blogMapper.find(context.getBlogSearchContext().getBlogTitle());
        }
    }
}
