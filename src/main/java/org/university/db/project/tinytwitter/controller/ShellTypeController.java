package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.IShellController;
import org.university.db.project.tinytwitter.service.TwitterContext;

@Controller
public class ShellTypeController implements IShellController {
    @Override
    public ControllerResult run(TwitterContext context) {
        return null;
    }
}