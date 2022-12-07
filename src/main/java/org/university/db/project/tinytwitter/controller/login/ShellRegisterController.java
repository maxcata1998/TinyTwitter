package org.university.db.project.tinytwitter.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.ShellPortalController;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.controller.ControllerResult;
import org.university.db.project.tinytwitter.controller.base.IMenuController;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.service.RegisterService;
import org.university.db.project.tinytwitter.service.TwitterContext;

@Controller
public class ShellRegisterController extends AbstractMenuController implements IMenuController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ShellPortalController portalController;

    protected ShellRegisterController() {
        super("Register");
    }

    @Override
    protected void registerMenu() {
        register("Login", this::login);
        register("Register", this::register);
    }

    private ControllerResult login(TwitterContext context) {
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
        return portalController.run(context);
    }

    private ControllerResult register(TwitterContext context) {
        User user = new User();
        System.out.print("Username: ");
        String username = context.getIn().next();
        if (registerService.exist(username)) {
            System.out.println("Username \"" + username + "\" already exist");
            return ControllerResult.NORMAL;
        }

        user.setName(username);
        System.out.print("Password: ");
        user.setPassword(context.getIn().next());

        System.out.print("Email   : ");
        user.setEmail(context.getIn().next());

        registerService.add(user);
        context.setUser(user);
        return ControllerResult.RETURN;
    }
}
