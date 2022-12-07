package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.*;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.controller.base.IMenuController;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.service.RegisterService;
import org.university.db.project.tinytwitter.service.TwitterContext;

@Controller
public class ShellPortalController extends AbstractMenuController {

    @Autowired
    RegisterService registerService;

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
