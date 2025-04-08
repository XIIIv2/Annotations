package app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("app.ArrayUtils");
            Object arrayUtils = clazz.getConstructor().newInstance();
            Method mergeSort = clazz.getDeclaredMethod("mergeSort", int[].class);
            Method binarySearch = clazz.getDeclaredMethod("binarySearch", int[].class, int.class);

            int[] unsortedArray = new DataProvider().getData();
            int[] sortedArray = (int[]) mergeSort.invoke(arrayUtils, (Object) unsortedArray);

            System.out.println("Unsorted array: " + Arrays.toString(unsortedArray));
            System.out.println("Sorted array: " + Arrays.toString(sortedArray));

            int target = 7;
            int position = (int) binarySearch.invoke(arrayUtils, sortedArray, target);
            if (position < 0) {
                System.out.printf("Element %d not found in %s, position=%d.%n",
                        target,
                        Arrays.toString(sortedArray),
                        position);
            } else {
                System.out.printf("Search element (%d) in %s: position=%d, value=%d%n",
                        target,
                        Arrays.toString(sortedArray),
                        position,
                        sortedArray[position]);
            }

            System.out.println("--------------------------------");

            System.out.println("Class: " + clazz.getCanonicalName());
            System.out.println("*********************************");
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(MethodInfo.class)) {
                    MethodInfo methodInfo = m.getAnnotation(MethodInfo.class);

                    System.out.printf("Method name: %s%nReturns: %s%nDescription: %s%n",
                            methodInfo.name(),
                            methodInfo.returnType(),
                            methodInfo.desc());
                }

                if (m.isAnnotationPresent(Author.class)) {
                    Author author = m.getAnnotation(Author.class);

                    System.out.printf("Author: %s %s%n",
                            author.firstName(),
                            author.lastName());
                }

                System.out.println("--------------------------------");
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
