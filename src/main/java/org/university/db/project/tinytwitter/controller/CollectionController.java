package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;

@Controller
public class CollectionController extends AbstractMenuController {
    protected CollectionController() {
        super("Collections");
    }
}
