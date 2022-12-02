package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.entity.User;

import java.util.Scanner;

@Controller
public class ShellEntrypoint implements IShellController<Void> {

    @Autowired
    private ShellLoginController loginController;

    @Autowired
    private ShellTypeController typeController;

    public int run(Void context) {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        System.out.println("Welcome to Tiny Twitter");
        while (!stop) {
            if (loginController.run(null) == -1) {
                break;
            }
            User user = loginController.getCurrentUser();
            System.out.println("Please select your option:");
            System.out.println("1. Blogs");
            System.out.println("2. Likes");
            System.out.println("3. Collections");
            System.out.println("------------------------------");
            System.out.println("9. Logout");
            System.out.println("0. Exit");
            int selection;
            try {
                selection = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Incorrect option, try again");
                System.out.println();
                continue;
            }

            switch (selection) {
                // TODO: implement other cases
                case 3:
                    typeController.run(user); break;
                case 9:
                    loginController.logout(); break;
                case 0:
                    stop = true; break;
                default:
                    System.out.println("Incorrect option, try again");
            }
        }
        System.out.println("See you");
        return 0;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.university.db.project.tinytwitter");

        ShellEntrypoint entrypoint = context.getBean(ShellEntrypoint.class);
        entrypoint.run(null);
    }
}
