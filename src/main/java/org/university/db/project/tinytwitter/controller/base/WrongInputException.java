package org.university.db.project.tinytwitter.controller.base;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String msg) {
        super(msg);
    }

    public WrongInputException(Exception e) {
        super("Invalid Input Format: " + e.getMessage());
    }
}
