import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {
    private static int notpassed = 0;
    private static int passed = 0;

    public static void run(Class<?> clazz) {
        List<Method> beforeMethods = getAnnotatedMethods(clazz, Before.class);
        List<Method> testMethods = getAnnotatedMethods(clazz, Test.class);
        List<Method> afterMethods = getAnnotatedMethods(clazz, After.class);

        for (Method testMethod : testMethods) {
            Object testInstance = createTestInstance(clazz);
            if (testInstance != null) {
                executeMethods(beforeMethods, testInstance);
                executeTestMethod(testMethod, testInstance);
                executeMethods(afterMethods, testInstance);
            }
        }

        printTestResults();
    }

    private static List<Method> getAnnotatedMethods(Class<?> clazz, Class<?> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent((Class<? extends Annotation>) annotation))
                .collect(Collectors.toList());
    }

    private static Object createTestInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void executeMethods(List<Method> methods, Object testInstance) {
        for (Method method : methods) {
            runMethod(method, testInstance);
        }
    }

    private static void executeTestMethod(Method testMethod, Object testInstance) {
        boolean result = runMethod(testMethod, testInstance);
        if (result) {
            System.out.println(testMethod.getName() + " - Passed");
            passed++;
        } else {
            System.out.println(testMethod.getName() + " - Not Passed");
            notpassed++;
        }
    }

    private static boolean runMethod(Method method, Object testInstance) {
        try {
            method.setAccessible(true);
            method.invoke(testInstance);
            return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    private static void printTestResults() {
        System.out.println("Number of tests - " + (passed + notpassed) + " : Passed test - " + passed
                + ", Not Passed test - " + notpassed);
    }
}
