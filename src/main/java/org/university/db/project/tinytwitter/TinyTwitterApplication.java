package org.university.db.project.tinytwitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.university.db.project.tinytwitter.controller.ControllerResult;
import org.university.db.project.tinytwitter.controller.PortalController;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Scanner;

@SpringBootApplication
public class TinyTwitterApplication implements CommandLineRunner {

    private final PortalController portalController;

    @Autowired
    public TinyTwitterApplication(PortalController portalController) {
        this.portalController = portalController;
    }

    public static void main(String[] args) {
        SpringApplication.run(TinyTwitterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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
            result = portalController.run(twitterContext);
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
