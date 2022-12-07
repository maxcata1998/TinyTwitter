package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.service.UserService;

@Controller
public class ShellPortalController extends AbstractMenuController {

    @Autowired
    UserService userService;

    @Autowired
    private CollectionController collectionController;

    @Autowired
    private BlogController blogController;

    protected ShellPortalController() {
        super("Login");
    }

    @Override
    protected void registerMenu() {
        register("Blogs", blogController);//blogController);
        register("Collections", collectionController);//collectionController);
    }
}
