package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.service.TwitterContext;
import org.university.db.project.tinytwitter.service.UserService;

@Controller
public class ShellUserController extends AbstractMenuController {

    private final UserService userService;

    private final ShellPortalController portalController;

    protected ShellUserController(UserService userService, ShellPortalController portalController) {
        this.userService = userService;
        this.portalController = portalController;
    }

    @Override
    protected void registerMenu(TwitterContext context) {
        register("Login", this::login);
        register("Register", this::register);
    }

    private ControllerResult login(TwitterContext context) {
        System.out.print("Username: ");
        String username = context.getIn().next();
        System.out.print("Password: ");
        String password = context.getIn().next();

        User user = userService.login(username, password);
        if (user == null) {
            System.out.println("Invalid Username or Password");
            return ControllerResult.RETURN;
        } else {
            context.setUser(user);
        }
        ControllerResult result = portalController.run(context);
        if (result == ControllerResult.RETURN) {
            return ControllerResult.NORMAL;
        }
        if (result == ControllerResult.LOGOUT) {
            context.setUser(null);
            return ControllerResult.NORMAL;
        }
        return result;
    }

    private ControllerResult register(TwitterContext context) {
        User user = new User();
        System.out.print("Username: ");
        String username = context.getIn().next();
        if (userService.exist(username)) {
            System.out.println("Username \"" + username + "\" already exist");
            return ControllerResult.NORMAL;
        }

        user.setName(username);
        System.out.print("Password: ");
        user.setPassword(context.getIn().next());

        System.out.print("Email   : ");
        user.setEmail(context.getIn().next());

        userService.add(user);
        context.setUser(user);
        return ControllerResult.RETURN;
    }
}
