package org.university.db.project.tinytwitter.controller.base;

import org.university.db.project.tinytwitter.controller.ControllerResult;
import org.university.db.project.tinytwitter.service.TwitterContext;

public interface IShellController {
    ControllerResult run(TwitterContext context);

    IShellController DEFAULT_NORMAL = context -> ControllerResult.NORMAL;
}
