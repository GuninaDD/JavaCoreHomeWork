package com.geekbrains.testing;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 5, 3, 4, 2, 4, 3, 4, 3, 5, 3, 7};
        int[] array2 = {1, 1, 1, 4, 4, 1, 4, 4};
        Method method = new Method();
        System.out.println(Arrays.toString(method.myMethodOne(array)));
        System.out.println(method.myMethodTwo(array2));

    }
}
