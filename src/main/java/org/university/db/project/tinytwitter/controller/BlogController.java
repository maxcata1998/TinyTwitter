package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.service.BlogService;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Date;
import java.util.List;

@Controller
public class BlogController extends AbstractMenuController {

    private final BlogService blogService;

    private final BlogViewController blogViewController;

    protected BlogController(BlogService blogService, BlogViewController blogViewController) {
        super();
        this.blogService = blogService;
        this.blogViewController = blogViewController;
    }

    @Override
    protected void registerMenu(TwitterContext context) {
        if (context.getUser() != null) {
            register("Create Blog", this::addBlog);
        }
        register("Search Blog", this::searchBlog);
        register("View Blog", this::viewBlog);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        TwitterContext.BlogSearchContext searchContext = context.getBlogSearchContext();
        List<Blog> blogList = blogService.searchBlog(context.getUser(), searchContext);
        context.setBlogList(blogList);

        for (int i = 0; i < blogList.size(); i++) {
            Blog blog = blogList.get(i);
            System.out.printf("%2d. | Author: %s\n", i + 1, blog.getUser().getName());
            System.out.println("    | Title : " + blog.getTitle());

            if (searchContext.getBlogContent() != null) {
                printSearchContextResult(searchContext.getBlogContent(), context.getBlog().getContent());
            }
        }
        return ControllerResult.NORMAL;
    }

    private void printSearchContextResult(String keyword, String content) {
        String[] pieces = content.split(keyword);
        StringBuilder sb = new StringBuilder();
        if (pieces[0].length() <= 10) {
            sb.append(pieces[0]);
        } else {
            sb.append("... ").append(pieces[0].substring(pieces.length - 10));
        }
        sb.append(keyword);

        for (int j = 1; j < pieces.length - 1; j++) {
            if (pieces[j].length() <= 20) {
                sb.append(pieces[j]);
            } else {
                sb.append(pieces[j], 0, 10)
                        .append(" ... ")
                        .append(pieces[j].substring(pieces[j].length() - 10));
            }
            sb.append(keyword);
        }
        if (pieces[pieces.length - 1].length() <= 10) {
            sb.append(pieces[pieces.length - 1]);
        } else {
            sb.append(pieces[pieces.length - 1], 0, 10).append(" ...");
        }
        System.out.println("    | Content: " + sb);
    }

    public ControllerResult addBlog(TwitterContext context) {
        Blog blog = new Blog();

        System.out.print("title  : ");
        blog.setTitle(nextLine(context.getIn()));
        System.out.print("content: ");
        blog.setContent(nextLine(context.getIn()));

        blog.setAuthor(context.getUser().getUserId());
        blog.setCreateDate(new Date());
        blog.setUpdateDate(blog.getCreateDate());
        blog.setAuthor(context.getUser().getUserId());
        blog.setUser(context.getUser());
        blogService.add(blog);
        context.setBlog(blog);
        System.out.println("Blog \"" + blog.getTitle() + "\" created");

        return ControllerResult.NORMAL;
    }

    private ControllerResult searchBlog(TwitterContext context) {
        TwitterContext.BlogSearchContext searchContext = context.getBlogSearchContext();
        searchContext.clear();
        querySpecifyLine("user", context.getIn(), searchContext::setUser);
        querySpecifyLine("title", context.getIn(), searchContext::setBlogTitle);
        querySpecifyLine("content", context.getIn(), searchContext::setBlogContent);
        querySpecifyBool("like", context.getIn(), searchContext::setIsLike);
        querySpecifyBool("collection", context.getIn(), searchContext::setIsCollect);
        return ControllerResult.NORMAL;
    }



    private ControllerResult viewBlog(TwitterContext context) {
        System.out.print("Enter blog number: ");
        int num = context.getIn().nextInt();
        if (num < 1 || context.getBlogList().size() < num) {
            System.out.println("Invalid blog number");
            return ControllerResult.NORMAL;
        }
        context.setBlog(context.getBlogList().get(num - 1));
        return blogViewController.run(context);
    }
}
