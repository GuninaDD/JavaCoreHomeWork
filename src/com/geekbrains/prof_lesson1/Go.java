package com.geekbrains.prof_lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Go {
    public static void main(String[] args) {

        Integer[] arr_int = new Integer[]{0, 1, 2, 3};
        String[] arr_str = new String[]{"A", "B", "C", "D"};

        restoreArray(arr_int, 1, 3);
        restoreArray(arr_str, 0, 2);

        ArrayList arr_list = convertToArrayList(arr_int);

        System.out.println();
        for (Object o : arr_list) {
            System.out.print(o + " ");
        }
        System.out.println();

        // Задание 3
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();
        Apple apple5 = new Apple();
        Apple apple6 = new Apple();
        Apple apple7 = new Apple();

        Box<Apple> appleBox = new Box<>(apple1, apple2, apple3, apple4, apple5, apple6);

        appleBox.addFruit(apple7);
        System.out.println(appleBox.getWeight());

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();

        Box<Orange> orangeBox = new Box<>(orange1, orange2, orange3);
        System.out.println(appleBox.compareTo(orangeBox));

        appleBox.removeFruit(apple7);

        Box<Orange> newOrangeBox = new Box<>(orange1);

        orangeBox.transferFruit(newOrangeBox);
        System.out.println(newOrangeBox.getWeight());
        System.out.println(appleBox.compareTo(newOrangeBox));


    }

    // Задание 1
    public static void restoreArray(Object[] arr, int indexFirst, int indexSecond) {
        Object tmp = arr[indexFirst];
        arr[indexFirst] = arr[indexSecond];
        arr[indexSecond] = tmp;
        System.out.println();
        for (Object o : arr) {
            System.out.print(o + " ");
        }
    }

    // Задание 2
    public static <T> ArrayList convertToArrayList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

}
