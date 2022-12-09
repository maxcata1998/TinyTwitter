package org.university.db.project.tinytwitter.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Scanner;

public class ShellEntrypoint {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.university.db.project.tinytwitter");
        context.refresh();

        ShellUserController registerController = context.getBean(ShellUserController.class);

        System.out.println("########################################");
        System.out.println("##      Welcome to Tiny Twitter       ##");
        System.out.println("##       Released on Dec.9 2022       ##");
        System.out.println("##           Version 1.2.2            ##");
        System.out.println("##  Co founder: Tianrun Yan, Xiao Ma  ##");
        System.out.println("########################################");


        TwitterContext twitterContext = new TwitterContext();
        twitterContext.setIn(new Scanner(System.in));

        ControllerResult result = ControllerResult.NORMAL;
        while (result != ControllerResult.EXIT) {
            result = registerController.run(twitterContext);
            if (result == ControllerResult.LOGOUT) {
                System.out.println("Logging out");
                twitterContext.setUser(null);
            }
        }

        System.out.println("########################################");
        System.out.println("##    Wish you a nice day. See you    ##");
        System.out.println("########################################");
    }
}
