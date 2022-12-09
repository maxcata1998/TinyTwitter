package org.university.db.project.tinytwitter.controller.base;

import org.university.db.project.tinytwitter.controller.ControllerResult;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.*;

public abstract class AbstractMenuController extends AbstractShellController {

    private Map<String, IShellController> controllerMap;

    private List<String> controllerNames;

    private boolean refresh;

    protected AbstractMenuController(String label) {
        this(label, false);
    }

    protected AbstractMenuController(String label, boolean refresh) {
        super(label);
        this.refresh = refresh;
    }

    @Override
    final public ControllerResult run(TwitterContext context) {
        if (controllerNames == null || refresh) {
            if (controllerNames== null) {
                controllerNames = new ArrayList<>();
                controllerMap = new HashMap<>();
            }
            registerMenu(context);
        }

        ControllerResult result = ControllerResult.NORMAL;
        while (result == ControllerResult.NORMAL) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            result = process(context);
            if (result != ControllerResult.NORMAL) {
                return result;
            }

            int selection = menu(context);
            if (selection == controllerMap.size() + 1) {
                context.setUser(null);
                return ControllerResult.LOGOUT;
            }
            if (selection == controllerMap.size() + 2) {
                return ControllerResult.NORMAL;
            }
            if (selection == controllerMap.size() + 3) {
                return ControllerResult.EXIT;
            }
            IShellController controller = controllerMap.get(controllerNames.get(selection - 1));
            if (controller != this) {
                result = controller.run(context);
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
        System.out.println();
        System.out.println("Please select your option: ");
        for (int i = 1; i <= controllerNames.size(); i++) {
            System.out.println(i + ". " + controllerNames.get(i-1));
        }
        System.out.println("----------------------------------------");

        System.out.println((controllerNames.size() + 1) + ". Logout");
        System.out.println((controllerNames.size() + 2) + ". Return");
        System.out.println((controllerNames.size() + 3) + ". Exit");
        System.out.print("Selection: ");
        int selection;
        while (true) {
            try {
                selection = context.getIn().nextInt();
                if ( 1 <= selection && selection <= controllerNames.size() + 3) {
                    return selection;
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid option, select again: ");
            }
        }
    }
}
