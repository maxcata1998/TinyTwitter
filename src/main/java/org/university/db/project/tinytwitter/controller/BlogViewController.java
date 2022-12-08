package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.service.BlogService;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Objects;

@Controller
public class BlogViewController extends AbstractMenuController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentController commentController;

    protected BlogViewController() {
        super("View Blog");
    }

    @Override
    protected void registerMenu() {
        register("Like", this::like);
        register("Unlike", this::unlike);
        register("Collect", this::collect);
        register("Un-collect", this::unCollect);
        register("Comments", commentController);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        System.out.println();
        System.out.println("\t Title      : " + context.getBlog().getTitle());
        System.out.println("\t Author     : " + context.getBlog().getUser().getName());
        System.out.println("\t Created on : " + context.getBlog().getCreateDate());
        System.out.println("\t Last update: " + context.getBlog().getUpdateDate());
        System.out.println();
        System.out.println(context.getBlog().getContent());

        return ControllerResult.NORMAL;
    }

    private ControllerResult like(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot like your own blog");
        }
        blogService.like(context.getUser(), context.getBlog(), true);
        return ControllerResult.NORMAL;
    }

    private ControllerResult unlike(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot unlike your own blog");
        }
        blogService.like(context.getUser(), context.getBlog(), false);
        return ControllerResult.NORMAL;
    }

    private ControllerResult collect(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot collect your own blog");
        }
        blogService.collect(context.getUser(), context.getBlog(), true);
        return ControllerResult.NORMAL;
    }

    private ControllerResult unCollect(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot un-collect your own blog");
        }
        blogService.collect(context.getUser(), context.getBlog(), false);
        return ControllerResult.NORMAL;
    }
}
