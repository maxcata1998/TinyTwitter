package org.university.db.project.tinytwitter.controller.base;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractShellController implements IShellController {

    protected AbstractShellController() {
    }

    protected <T> boolean queryAndSet(String action, String param, Supplier<String> choice, Supplier<T> supplier, Consumer<T> consumer) {
        System.out.print(action + " " + param + " ? [y/n]: ");
        if (choice.get().toLowerCase().charAt(0) == 'y') {
            System.out.print(param + ": ");
            consumer.accept(supplier.get());
            return true;
        }
        return false;
    }

    protected boolean readSelection(Scanner scanner) {
        System.out.print(" [y/n]: ");
        return scanner.next().toLowerCase().charAt(0) == 'y';
    }

    protected void querySpecifyString(String param, Scanner scanner, Consumer<String> consumer) {
        queryAndSet("Specify", param, scanner::next, scanner::next, consumer);
    }

    protected boolean querySpecifyInt(String param, Scanner scanner, Consumer<Integer> consumer) {
        return queryAndSet("Specify", param, scanner::next, scanner::nextInt, consumer);
    }

    protected void querySpecifyBool(String param, Scanner scanner, Consumer<Boolean> consumer) {
        System.out.print("Specify " + param + " ? [y/n]: ");
        if (scanner.next().toLowerCase().charAt(0) == 'y') {
            System.out.print(param + " ([y/n]): ");
            consumer.accept(scanner.next().toLowerCase().charAt(0) == 'y');
        } else {
            consumer.accept(null);
        }
    }

    protected boolean queryModifyString(String param, Scanner scanner, Consumer<String> consumer) {
        return queryAndSet("Modify", param, scanner::next, scanner::next, consumer);
    }

    protected boolean queryModifyLine(String param, Scanner scanner, Consumer<String> consumer) {
        return queryAndSet("Modify", param, scanner::next, () -> {
            scanner.nextLine(); return scanner.nextLine();
        }, consumer);
    }

    protected boolean queryModifyInt(String param, Scanner scanner, Consumer<Integer> consumer) {
        return queryAndSet("Modify", param, scanner::next, scanner::nextInt, consumer);
    }
}
