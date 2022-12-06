package org.university.db.project.tinytwitter.controller;

import org.university.db.project.tinytwitter.controller.base.AbstractShellController;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldUpdateController extends AbstractShellController {
    private Class<?> clazz;
    private String fieldName;
    private Function<String, Object> convertor;

    public FieldUpdateController(Class<?> clazz, String field) {
        this(clazz, field, o -> o);
    }

    public FieldUpdateController(Class<?> clazz, String field, Function<String, Object> convertor) {
        super(field);
        this.convertor = convertor;
        this.clazz = clazz;
        this.fieldName = field;
    }

    @Override
    public ControllerResult run(TwitterContext context) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            System.out.print(field + " : ");
            field.set(context.getUpdateObj(), convertor.apply(context.getIn().next()));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Update field " + fieldName + "failed");
            System.out.println(e);
        }
        return ControllerResult.NORMAL;
    }
}
