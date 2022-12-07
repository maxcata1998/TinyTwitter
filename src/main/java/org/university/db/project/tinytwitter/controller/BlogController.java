package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.service.TwitterContext;
import org.university.db.project.tinytwitter.service.BlogService;

import java.util.Date;
import java.util.List;

@Controller
public class BlogController extends AbstractMenuController {
    @Autowired
    BlogService blogService;

    protected BlogController() {
        super("Browse Blogs");
    }

    @Override
    protected void registerMenu() {
        register("Create Blog", this::addBlog);
        register("Update Blog", this::updateBlog);
        register("Search Blog", this::searchBlog);
        register("Delete Blog", this::deleteBlog);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        List<Blog> blogList = blogService.searchBlog(context);
        context.setBlogList(blogList);

        for (int i = 0; i < blogList.size(); i++) {
            System.out.println((i+1) + ". " + blogList.get(i).getTitle());
        }
        return ControllerResult.NORMAL;
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
        blogService.add(blog);
        context.setBlog(blog);
        System.out.println("Blog \"" + blog.getTitle() + "\" created");

        return ControllerResult.RETURN;
    }

    public ControllerResult updateBlog(TwitterContext context) {
        System.out.print("Enter blog number: ");
        int num = context.getIn().nextInt();
        if (num < 0 || num >= context.getBlogList().size()) {
            System.out.println("Invalid blog number");
            return ControllerResult.RETURN;
        }

        Blog blog = context.getBlogList().get(num);
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
            blogService.update(blog);
            System.out.println("Blog " + blog.getTitle() + " updated");
        }
        return ControllerResult.RETURN;
    }

    private ControllerResult searchBlog(TwitterContext context) {
        if (doSpecify("user", context.getIn())) {
            System.out.print("user: ");
            context.getBlogSearchContext().setUser(context.getIn().next());
        }
        if (doSpecify("title", context.getIn())) {
            System.out.print("title: ");
            context.getBlogSearchContext().setBlogTitle(context.getIn().next());
        }
        if (doSpecify("content", context.getIn())) {
            System.out.print("content: ");
            context.getBlogSearchContext().setBlogContent(context.getIn().next());
        }
        if (doSpecify("type", context.getIn())) {
            System.out.print("type: ");
            context.getBlogSearchContext().setBlogContent(context.getIn().next());
        }
        return ControllerResult.RETURN;
    }

    private ControllerResult deleteBlog(TwitterContext context) {
        System.out.print("Enter blog number: ");
        int num = context.getIn().nextInt();
        if (num < 0 || num >= context.getBlogList().size()) {
            System.out.println("Invalid blog number");
            return ControllerResult.RETURN;
        }

        Blog blog = context.getBlogList().get(num);
        blogService.delete(blog);
        System.out.println("Blog " + blog.getTitle() + " deleted");

        return ControllerResult.RETURN;
    }
}
