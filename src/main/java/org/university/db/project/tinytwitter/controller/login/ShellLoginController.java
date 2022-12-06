package org.university.db.project.tinytwitter.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.*;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.controller.base.IMenuController;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.service.RegisterService;
import org.university.db.project.tinytwitter.service.TwitterContext;

@Controller("login")
public class ShellLoginController extends AbstractMenuController implements IMenuController {

    @Autowired
    RegisterService registerService;

    @Autowired
    ShellRegisterController registerController;

    @Autowired
    CollectionController collectionController;

    @Autowired
    BlogController blogController;

    protected ShellLoginController() {
        super("Login");
    }

    @Override
    protected void registerMenu() {
        register("Blogs", c -> ControllerResult.RETURN);//blogController);
        register("Collections", c -> ControllerResult.RETURN);//collectionController);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        System.out.print("Username: ");
        String username = context.getIn().next();
        System.out.print("Password: ");
        String password = context.getIn().next();

        User user = registerService.login(username, password);
        if (user == null) {
            System.out.println("Invalid Username or Password");
            return ControllerResult.RETURN;
        } else {
            context.setUser(user);
        }
        return ControllerResult.NORMAL;
    }
}
