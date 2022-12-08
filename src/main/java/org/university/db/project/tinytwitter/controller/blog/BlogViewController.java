package org.university.db.project.tinytwitter.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.ControllerResult;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.service.BlogService;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Objects;

@Controller
public class BlogViewController extends AbstractMenuController {
    @Autowired
    private BlogService blogService;

    protected BlogViewController() {
        super("View Blog");
    }

    @Override
    protected void registerMenu() {
        register("like", this::like);
        register("collect", this::collect);
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
        blogService.like(context.getUser(), context.getBlog());
        return ControllerResult.NORMAL;
    }

    private ControllerResult collect(TwitterContext context) {
        if (Objects.equals(context.getBlog().getUser().getUserId(), context.getUser().getUserId())) {
            System.out.println("You cannot collect your own blog");
        }
        blogService.collect(context.getUser(), context.getBlog());
        return ControllerResult.NORMAL;
    }
}
