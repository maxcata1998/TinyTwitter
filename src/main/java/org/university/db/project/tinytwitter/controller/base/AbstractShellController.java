package org.university.db.project.tinytwitter.controller.base;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractShellController implements IShellController {
    private final String label;

    protected AbstractShellController(String label) {
        this.label = label;
    }

    protected AbstractShellController() {
        this.label = this.getClass().getSimpleName();
    }

    public String getLabel() {
        return label;
    }

    protected boolean doSpecify(String param, Scanner scanner) {
        System.out.print("Specify " + param + " ? [y/n]: ");
        return scanner.next().toLowerCase().charAt(0) == 'y';
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

    protected void querySpecifyString(String param, Scanner scanner, Consumer<String> consumer) {
        queryAndSet("Modify", param, scanner::next, scanner::next, consumer);
    }

    protected boolean querySpecifyInt(String param, Scanner scanner, Consumer<Integer> consumer) {
        return queryAndSet("Modify", param, scanner::next, scanner::nextInt, consumer);
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
        return queryAndSet("Specify", param, scanner::next, scanner::next, consumer);
    }

    protected boolean queryModifyInt(String param, Scanner scanner, Consumer<Integer> consumer) {
        return queryAndSet("Specify", param, scanner::next, scanner::nextInt, consumer);
    }
}
