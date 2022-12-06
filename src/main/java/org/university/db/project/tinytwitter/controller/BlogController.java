package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.controller.base.AbstractShellController;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.service.TwitterContext;
import org.university.db.project.tinytwitter.service.TwitterService;

import java.util.Date;

@Controller
public class BlogController extends AbstractMenuController {
    @Autowired
    TwitterService twitterService;

    protected BlogController() {
        super("Browse Blogs");
    }

    @Override
    protected void registerMenu() {
        register("Create Blog", this::addBlog);
        register("Update Blog", this::updateBlog);
    }

    public ControllerResult run1(TwitterContext context) {
        return null;
    }

    public ControllerResult addBlog(TwitterContext context) {
        Blog blog = new Blog();

        System.out.print("title  : ");
        blog.setTitle(context.getIn().next());
        System.out.print("content: ");
        blog.setContent(context.getIn().next());
        blog.setAuthor(context.getUser().getUserId());
        blog.setCreateDate(new Date());
        blog.setUpdateDate(blog.getCreateDate());
        twitterService.add(blog);
        context.setBlog(blog);
        System.out.println("Blog \"" + blog.getTitle() + "\" created");

        return ControllerResult.RETURN;
    }

    public ControllerResult updateBlog(TwitterContext context) {
        Blog blog = context.getBlog();
        boolean modified = false;
        System.out.print("Modify title? [y/n]: ");
        if (context.getIn().next().toLowerCase().charAt(0) == 'y') {
            System.out.print("title: ");
            blog.setTitle(context.getIn().next());
            modified = true;
        }

        System.out.print("Modify content? [y/n]: ");
        if (context.getIn().next().toLowerCase().charAt(0) == 'y') {
            System.out.print("content: ");
            blog.setContent(context.getIn().next());
            modified = true;
        }

        if (modified) {
            blog.setUpdateDate(new Date());
            twitterService.update(blog);
            System.out.println("Blog " + blog.getTitle() + " updated");
        }
        return ControllerResult.RETURN;
    }
}
