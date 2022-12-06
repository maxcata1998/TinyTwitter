package org.university.db.project.tinytwitter.controller.base;

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
}
