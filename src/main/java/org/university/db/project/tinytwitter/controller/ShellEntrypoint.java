package org.university.db.project.tinytwitter.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.controller.login.ShellLoginController;
import org.university.db.project.tinytwitter.controller.login.ShellRegisterController;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Scanner;

@Controller

public class ShellEntrypoint extends AbstractMenuController {

    @Autowired
    private ShellLoginController loginController;

    @Autowired
    private ShellRegisterController registerController;

    protected ShellEntrypoint() {
        super("Entrypoint");
    }

    @Override
    protected void registerMenu() {
        register("Login", loginController);
        register("Register", registerController);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.university.db.project.tinytwitter");
//        context.
        context.refresh();

        ShellEntrypoint entrypoint = context.getBean(ShellEntrypoint.class);

        System.out.println("Welcome to Tiny Twitter");

        TwitterContext twitterContext = new TwitterContext();
        twitterContext.setIn(new Scanner(System.in));

        ControllerResult result = ControllerResult.NORMAL;
        while (result != ControllerResult.EXIT) {
            result = entrypoint.run(twitterContext);
            if (result == ControllerResult.LOGOUT) {
                System.out.println("Logging out");
                twitterContext.setUser(null);
            }
        }

        System.out.println("See you");
    }
}
