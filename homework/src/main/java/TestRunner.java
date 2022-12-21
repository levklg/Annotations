import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {
    static int notpassed = 0;
    static int passed = 0;


    public static void run(Class clazz) {


        try {
            Method[] methods = clazz.getDeclaredMethods();

            var beforeList = Arrays.stream(methods).filter(method -> method.isAnnotationPresent(Before.class)).collect(Collectors.toList());
            var testeList = Arrays.stream(methods).filter(method -> method.isAnnotationPresent(Test.class)).collect(Collectors.toList());
            var afterList = Arrays.stream(methods).filter(method -> method.isAnnotationPresent(After.class)).collect(Collectors.toList());

            List<Method> methodList = new ArrayList<>(Arrays.stream(clazz.getDeclaredMethods()).toList());

            Constructor<?> constructor = clazz.getDeclaredConstructor();

            for (int i = 0; i < beforeList.size(); i++) {
                Object object = constructor.newInstance();
                runAnnotation(Before.class, beforeList.get(i), object);
                runAnnotation(Test.class, testeList.get(i), object);
                runAnnotation(After.class, afterList.get(i), object);
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println("Number of tests - " + (passed + notpassed) + " : Passed test - " + passed
                + ", Not Passed test - " + notpassed);

    }

    private static boolean runMeyhod(Method method, Object object) {
        boolean result = false;
        if (object != null) {
            method.setAccessible(true);
            result = true;
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                result = false;

            } catch (InvocationTargetException e) {
                result = false;

            }
        }
        return result;
    }

    private static void runAnnotation(Class<?> annotation, Method method, Object object) {

        if (TestRunner.runMeyhod(method, object)) {
            System.out.println(method.getName() + " - Passed");
            if (annotation == Test.class) passed++;
        } else {
            System.out.println(method.getName() + " - Not Passed");
            if (annotation == Test.class) notpassed++;
        }

    }

}