package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.service.TwitterContext;
import org.university.db.project.tinytwitter.service.UserService;

import java.util.Stack;

@Controller
public class PortalController extends AbstractMenuController {

    private final BlogController blogController;

    private final UserService userService;

    private final StatisticController statisticController;

    @Autowired
    protected PortalController(BlogController blogController, UserService userService,
                               StatisticController statisticController) {
        super();
        this.blogController = blogController;
        this.userService = userService;
        this.statisticController = statisticController;
    }

    @Override
    protected void registerMenu(TwitterContext context) {
        if (context.getUser() == null) {
            register("Login", this::login);
            register("Register", this::register);
        }
        register("Explore Blogs", this::browseBlogs);
        if (context.getUser() != null) {
            register("My Blogs", this::browseMyBlogs);
            register("My Collections", this::browseMyCollections);
        }
        register("Statistics", statisticController);
    }

    private ControllerResult login(TwitterContext context) {
        System.out.print("Username: ");
        String username = context.getIn().next().trim();
        System.out.print("Password: ");
        String password = context.getIn().next().trim();

        User user = userService.login(username, password);
        if (user == null) {
            System.out.println("Invalid Username or Password");
        } else {
            context.setUser(user);
        }

        return ControllerResult.NORMAL;
    }

    private ControllerResult register(TwitterContext context) {
        User user = new User();
        System.out.print("Username: ");
        String username = context.getIn().next().trim();
        if (userService.exist(username)) {
            System.out.println("Username \"" + username + "\" already exist");
            return ControllerResult.NORMAL;
        }

        user.setName(validateStrLen(username, 45));
        System.out.print("Password: ");
        user.setPassword(validateStrLen(context.getIn().next(), 20));

        System.out.print("Email   : ");
        user.setEmail(validateStrLen(context.getIn().next(), 45));

        userService.add(user);
        context.setUser(user);
        return ControllerResult.NORMAL;
    }

    private ControllerResult browseBlogs(TwitterContext context) {
        context.getBlogSearchContext().clear();
        return blogController.run(context);
    }

    private ControllerResult browseMyBlogs(TwitterContext context) {
        context.getBlogSearchContext().clear();
        context.getBlogSearchContext().setUser(context.getUser().getName());
        return blogController.run(context);
    }

    private ControllerResult browseMyCollections(TwitterContext context) {
        context.getBlogSearchContext().clear();
//        context.getBlogSearchContext().setUser(context.getUser().getName());
        context.getBlogSearchContext().setIsCollect(true);
        return blogController.run(context);
    }
}
