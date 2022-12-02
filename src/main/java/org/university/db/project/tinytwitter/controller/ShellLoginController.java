package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.entity.User;

@Controller
public class ShellLoginController implements IShellController<Void> {

    private User user;

    @Override
    public int run(Void context) {
        // Already login
        if (user != null) {
            return 0;
        }
        // TODO: Not Login or Log out
        return 0;
    }

    public void logout() {
        user = null;
    }

    public User getCurrentUser() {
        return user;
    }
}
