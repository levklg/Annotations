import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
   static int notpassed = 0;
    static int passed = 0;
    public static void run(Class clazz) {




        try {
            Method[] methods = clazz.getDeclaredMethods();
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object object = constructor.newInstance();

            TestRunner.runAnnotation(Before.class, methods, object);


            TestRunner.runAnnotation(Test.class, methods, object);

           TestRunner.runAnnotation(After.class, methods, object);




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

    private static void runAnnotation(Class<?> annotation,  Method[] methods, Object object ){
        try {
            for (Method m : methods) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) annotation)) {
                    if (TestRunner.runMeyhod(m, object)) {
                        System.out.println(m.getName() + " - Passed");
                        if (annotation == Test.class) passed++;
                    } else {
                        System.out.println(m.getName() + " - Not Passed");
                        if (annotation == Test.class) notpassed++;
                    }

                }
        }

        }catch (Exception exception){
        throw exception;

        }

    }
}