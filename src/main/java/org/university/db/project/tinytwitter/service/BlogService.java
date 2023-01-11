package org.university.db.project.tinytwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.BlogMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.User;

import java.util.Iterator;
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

    public boolean isLike(User user, Blog blog) {
        return blogMapper.isLike(user, blog);
    }

    public boolean isCollect(User user, Blog blog) {
        return blogMapper.isCollect(user, blog);
    }

    public void like(User user, Blog blog, boolean isLike) {
        if (isLike) {
            blogMapper.addLike(user.getUserId(), blog.getBlogId());
        } else {
            blogMapper.deleteLike(user.getUserId(), blog.getBlogId());
        }
    }

    public boolean collect(User user, Blog blog, boolean doCollect) {
        if (doCollect) {
            blogMapper.addCollect(user.getUserId(), blog.getBlogId());
        } else {
            blogMapper.deleteCollect(user.getUserId(), blog.getBlogId());
        }
        return true;
    }

    public List<Blog> getAllBlog() {
        return blogMapper.selectAll();
    }

    public List<Blog> searchBlog(User user, TwitterContext.BlogSearchContext searchContext) {
        return blogMapper.selectByFilter(wrapLike(searchContext.getUser()), wrapLike(searchContext.getBlogTitle()),
                wrapLike(searchContext.getBlogContent()), searchContext.getIsLike(), searchContext.getIsCollect(),
                user == null ? null : user.getUserId());
//        return blogMapper.selectAll();
    }

    private String wrapLike(String str) {
        if (str == null) {
            return null;
        }
        return "%" + str + "%";
    }
}
