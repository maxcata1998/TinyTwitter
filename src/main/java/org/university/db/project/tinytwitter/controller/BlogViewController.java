package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.service.BlogService;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class BlogViewController extends AbstractMenuController {
    private final BlogService blogService;

    private final CommentController commentController;

    @Autowired
    protected BlogViewController(BlogService blogService, CommentController commentController) {
        this.blogService = blogService;
        this.commentController = commentController;
    }

    @Override
    protected void registerMenu(TwitterContext context) {
        if (context.getUser() != null) {
            if (context.getBlog().getUser().equals(context.getUser())) {
                register("Update Blog", this::updateBlog);
                register("Delete Blog", this::deleteBlog);
            }

            if (!context.getBlog().getUser().equals(context.getUser())) {
                if (blogService.isLike(context.getUser(), context.getBlog())) {
                    register("Unlike", this::unlike);
                } else {
                    register("Like", this::like);
                }

                if (blogService.isCollect(context.getUser(), context.getBlog())) {
                    register("Un-collect", this::unCollect);
                } else {
                    register("Collect", this::collect);
                }
            }
        }
        register("View Comments", commentController);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println();
        System.out.println("  Title      : " + context.getBlog().getTitle());
        System.out.println("  Author     : " + context.getBlog().getUser().getName());
        System.out.println("  Created on : " + format.format(context.getBlog().getCreateDate()));
        System.out.println("  Update  on : " + format.format(context.getBlog().getUpdateDate()));
        System.out.println("  Likes      : " + context.getBlog().getLikes());
        System.out.println("  Collects   : " + context.getBlog().getCollects());
        System.out.println();
        System.out.println(context.getBlog().getContent());
        System.out.println("----------------------------------------");

        return ControllerResult.NORMAL;
    }

    @Override
    protected void onReturn(TwitterContext context) {
        context.setBlog(null);
    }

    private ControllerResult updateBlog(TwitterContext context) {
        Blog blog = context.getBlog();

        if (queryModifyLine("title", context.getIn(), (s) -> blog.setTitle(validateStrLen(s, 45))) ||
                queryModifyLine("content", context.getIn(), blog::setContent)) {
            String title = blog.getTitle().trim();
            if (title.length() > 45) {
                System.out.println("Title too long, length > 45");
                return ControllerResult.NORMAL;
            }
            blog.setUpdateDate(new Date());
            blogService.update(blog);
            System.out.println("Blog " + blog.getTitle() + " updated");
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult deleteBlog(TwitterContext context) {
        Blog blog = context.getBlog();
        System.out.print("Are you sure?");
        if (readSelection(context.getIn())) {
            blogService.delete(blog);
            System.out.println("Blog " + blog.getTitle() + " deleted");
            return ControllerResult.RETURN;
        }

        return ControllerResult.NORMAL;
    }

    private ControllerResult like(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot like your own blog");
        } else {
            blogService.like(context.getUser(), context.getBlog(), true);
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult unlike(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot unlike your own blog");
        } else {
            blogService.like(context.getUser(), context.getBlog(), false);
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult collect(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot collect your own blog");
        } else {
            blogService.collect(context.getUser(), context.getBlog(), true);
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult unCollect(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot un-collect your own blog");
        } else {
            blogService.collect(context.getUser(), context.getBlog(), false);
        }
        return ControllerResult.NORMAL;
    }
}
