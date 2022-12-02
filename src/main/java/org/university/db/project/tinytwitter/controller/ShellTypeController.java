package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.entity.User;

@Controller
public class ShellTypeController implements IShellController<User> {
    @Override
    public int run(User context) {
        return 0;
    }
}
