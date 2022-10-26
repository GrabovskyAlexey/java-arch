package ru.grabovsky.handler;

import ru.grabovsky.ResponseSerializer;
import ru.grabovsky.SocketService;
import ru.grabovsky.config.Config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodHandlerFactory {
    public static MethodHandler create(final SocketService socketService, final ResponseSerializer responseSerializer, final Config config) {
        // Хардкод классов с хендлерами
        String[] classNames = new String[]{
                "ru.grabovsky.handler.GetMethodHandler",
                "ru.grabovsky.handler.PutMethodHandler",
                "ru.grabovsky.handler.PostMethodHandler",
                "ru.grabovsky.handler.PatchMethodHandler"
        };

        List<Class<?>> aClasses = new ArrayList<>();
        for (String className : classNames) {
            try {
                Class<?> aClass = Class.forName(className);
                if (aClass.isAnnotationPresent(Handler.class)) {
                    aClasses.add(aClass);
                }
            } catch (ClassNotFoundException ignored) {
            }
        }
        aClasses.sort(Comparator.comparingInt(c -> c.getAnnotation(Handler.class).order()));

        MethodHandler handler = null;
        for (int i = aClasses.size() - 1; i >= 0; i--) {
            try {
                handler = (MethodHandler) aClasses.get(i).getDeclaredConstructors()[0].newInstance(handler, socketService, responseSerializer, config);
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return handler;
    }
}
