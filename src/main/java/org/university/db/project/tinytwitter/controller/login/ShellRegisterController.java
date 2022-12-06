package org.university.db.project.tinytwitter.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private ShellLoginController loginController;

    protected ShellRegisterController() {
        super("Register");
    }

    @Override
    protected void registerMenu() {
        register("Login",loginController);
        register("Register",this);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        User user = new User();
        System.out.print("Username: ");
        String username = context.getIn().next();
        if (registerService.exist(username)) {
            System.out.println("Username \"" + username + "\" already exist");
            return ControllerResult.NORMAL;
        }

        user.setName(username);
        System.out.print("Password: ");
//        user.set;

        System.out.print("Email   : ");
        user.setEmail(context.getIn().next());

        registerService.add(user);
        context.setUser(user);
        return ControllerResult.NORMAL;
    }
}
