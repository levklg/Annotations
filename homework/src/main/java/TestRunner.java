import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {

    public static void run(Class clazz) {

        int notpassed = 0;
        int passed = 0;


       Method[] methods = clazz.getDeclaredMethods();


        try {


            for(Method m : methods){


                if(m.isAnnotationPresent(Test.class)){
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    Object object = constructor.newInstance();
                    for(Method mt : methods){
                        if(mt.isAnnotationPresent(Before.class)){

                        if(TestRunner.runMeyhod(mt,object)){
                            System.out.println(mt.getName() + " - Passed");
                            passed++;

                        }
                        else {
                            System.out.println(mt.getName() + " -  Not Passed");
                            notpassed++;
                        }

                        }


                    }
                    if(TestRunner.runMeyhod(m,object )){
                        System.out.println(m.getName() + " -  Passed");
                        passed++;
                    }
                    else {
                        System.out.println(m.getName() + " - Not Passed");
                        notpassed++;
                    }


                    for(Method mt : methods){
                        if(mt.isAnnotationPresent(After.class)){
                            if(TestRunner.runMeyhod(mt,object)){
                                System.out.println(mt.getName() + " - Passed");
                                passed++;

                            }
                            else {
                                System.out.println(mt.getName() + " - Not Passed");
                                notpassed++;
                            }

                        }

                    }

                }



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

    private static   boolean runMeyhod(Method method, Object object){
       boolean  result = false;
        if(object != null){
            method.setAccessible(true);
            result = true;
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
              //  e.printStackTrace();
                result = false;

            } catch (InvocationTargetException e) {
             //   e.printStackTrace();
                result = false;

            }
        }
        return result;
    }
}