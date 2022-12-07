package org.university.db.project.tinytwitter.controller.base;

import java.util.Scanner;

public abstract class AbstractShellController  implements IShellController {
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
}
