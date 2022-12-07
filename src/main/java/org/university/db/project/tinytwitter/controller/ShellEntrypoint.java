package org.university.db.project.tinytwitter.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.university.db.project.tinytwitter.TinyTwitterApplication;
import org.university.db.project.tinytwitter.controller.login.ShellRegisterController;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Scanner;

//@SpringBootApplication
public class ShellEntrypoint {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.getEnvironment().addActiveProfile("application.properties");
        context.scan("org.university.db.project.tinytwitter");
        context.refresh();

//        CalculateResultHandlerService calculateResultHandlerService = applicationContext.getBean(CalculateResultHandlerService.class);
//        calculateResultHandlerService.handlerBand();
//        context.getEnvironment()./
        SpringApplication application = new SpringApplication(TinyTwitterApplication.class);
//        application.

        ShellRegisterController registerController = context.getBean(ShellRegisterController.class);

        System.out.println("Welcome to Tiny Twitter");

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

        System.out.println("See you");
    }
}
