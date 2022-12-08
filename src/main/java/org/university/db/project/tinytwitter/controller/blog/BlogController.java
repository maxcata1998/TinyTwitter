package org.university.db.project.tinytwitter.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.ControllerResult;
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

    @Autowired
    BlogViewController blogViewController;

    protected BlogController() {
        super("Browse Blogs");
    }

    @Override
    protected void registerMenu() {
        register("Create Blog", this::addBlog);
        register("Update Blog", this::updateBlog);
        register("Search Blog", this::searchBlog);
        register("Delete Blog", this::deleteBlog);
        register("View Blog", blogViewController);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        List<Blog> blogList = blogService.searchBlog(context);
        context.setBlogList(blogList);

        for (int i = 0; i < blogList.size(); i++) {
            System.out.println((i + 1) + ". " + blogList.get(i).getTitle());
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
        blog.setAuthor(context.getUser().getUserId());
        blogService.add(blog);
        context.setBlog(blog);
        System.out.println("Blog \"" + blog.getTitle() + "\" created");

        return ControllerResult.NORMAL;
    }

    public ControllerResult updateBlog(TwitterContext context) {
        System.out.print("Enter blog number: ");
        int num = context.getIn().nextInt();
        if (num < 1 || context.getBlogList().size() < num) {
            System.out.println("Invalid blog number");
            return ControllerResult.NORMAL;
        }

        Blog blog = context.getBlogList().get(num - 1);
        boolean modified = queryModifyString("title", context.getIn(), blog::setTitle)
                || queryModifyString("content", context.getIn(), blog::setTitle);

        if (modified) {
            blog.setUpdateDate(new Date());
            blogService.update(blog);
            System.out.println("Blog " + blog.getTitle() + " updated");
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult searchBlog(TwitterContext context) {
        querySpecifyString("user", context.getIn(), context.getBlogSearchContext()::setUser);
        querySpecifyString("title", context.getIn(), context.getBlogSearchContext()::setBlogTitle);
        querySpecifyString("content", context.getIn(), context.getBlogSearchContext()::setBlogContent);
        querySpecifyString("type", context.getIn(), context.getBlogSearchContext()::setBlogType);
        return ControllerResult.NORMAL;
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

        return ControllerResult.NORMAL;
    }
}
