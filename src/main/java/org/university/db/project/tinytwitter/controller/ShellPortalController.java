package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.service.TwitterContext;

@Controller
public class ShellPortalController extends AbstractMenuController {

    private final BlogController blogController;

    @Autowired
    protected ShellPortalController(BlogController blogController) {
        super("Login");
        this.blogController = blogController;
    }

    @Override
    protected void registerMenu(TwitterContext context) {
        register("Browse Blogs", this::browseBlogs);//blogController);
        register("My Blogs", this::browseMyBlogs);
        register("My Collections", this::browseMyCollections);//collectionController);
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
        context.getBlogSearchContext().setUser(context.getUser().getName());
        context.getBlogSearchContext().setIsCollected(true);
        return blogController.run(context);
    }
}
