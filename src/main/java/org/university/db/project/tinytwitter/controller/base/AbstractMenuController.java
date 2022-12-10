package org.university.db.project.tinytwitter.controller.base;

import org.university.db.project.tinytwitter.controller.ControllerResult;
import org.university.db.project.tinytwitter.controller.PortalController;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.*;

public abstract class AbstractMenuController extends AbstractShellController {

    private static final int INVALID = 0;

    private static final int LOGOUT = -1;

    private static final int RETURN = -2;

    private static final int EXIT = -3;

    private Map<String, IShellController> controllerMap;

    private List<String> controllerNames;

    @Override
    final public ControllerResult run(TwitterContext context) {
        ControllerResult result = ControllerResult.NORMAL;
        while (result == ControllerResult.NORMAL) {
            if (controllerNames == null) {
                controllerNames = new ArrayList<>();
                controllerMap = new HashMap<>();
            } else {
                controllerNames.clear();
            }
            registerMenu(context);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            result = process(context);
            if (result != ControllerResult.NORMAL) {
                return result;
            }

            int selection = menu(context);
            switch (selection) {
                case INVALID:
                    System.out.print("Invalid option, please select again");
                    continue;
                case LOGOUT:
                    return ControllerResult.LOGOUT;
                case RETURN:
                    return ControllerResult.RETURN;
                case EXIT:
                    return ControllerResult.EXIT;
                default:
                    IShellController controller = controllerMap.get(controllerNames.get(selection - 1));
                    if (controller != this) {
                        result = controller.run(context);
                    }
                    break;
            }

        }
        return result;
    }

    protected void registerMenu(TwitterContext context) {
    }

    protected void onReturn(TwitterContext context) {
    }

    protected ControllerResult process(TwitterContext context) {
        return ControllerResult.NORMAL;
    }

    final protected void register(String name, IShellController controller) {
        this.controllerMap.put(name, controller);
        this.controllerNames.add(name);
    }


    private int menu(TwitterContext context) {
//        System.out.println();
        System.out.println("Please select your option: ");
        for (int i = 1; i <= controllerNames.size(); i++) {
            System.out.println(i + ". " + controllerNames.get(i - 1));
        }
        System.out.println("----------------------------------------");

        if (context.getUser() != null) {
            System.out.println("l. Logout");
        }
        if (!this.getClass().equals(PortalController.class)) {
            System.out.println("r. Return");
        }
        System.out.println("e. Exit");
        System.out.print("Selection: ");
        int selection;
        if (context.getIn().hasNextInt()) {
            selection = context.getIn().nextInt();
            if (1 <= selection && selection <= controllerNames.size()) {
                return selection;
            } else {
                return INVALID;
            }
        } else {
            char ch = context.getIn().next().toLowerCase().charAt(0);
            switch (ch) {
                case 'l':
                    return LOGOUT;
                case 'r':
                    return RETURN;
                case 'e':
                    return EXIT;
                default:
                    return INVALID;
            }
        }
    }
}
