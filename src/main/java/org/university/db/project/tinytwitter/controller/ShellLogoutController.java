package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.IShellController;
import org.university.db.project.tinytwitter.service.TwitterContext;

@Controller("logout")
public class ShellLogoutController implements IShellController {
    @Override
    public ControllerResult run(TwitterContext context) {
        context.setUser(null);
        return ControllerResult.NORMAL;
    }
}
