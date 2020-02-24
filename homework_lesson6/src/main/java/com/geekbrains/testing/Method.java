package com.geekbrains.testing;

import org.apache.commons.lang3.ArrayUtils;

public class Method {
    public int[] myMethodOne(int[] array) {

        if (array.length != 0 && isFor(array)) {
            return ArrayUtils.subarray(array,
                    ArrayUtils.lastIndexOf(array, 4) + 1,
                    ArrayUtils.getLength(array) + 1);
        } else {
            System.out.println("Массив пуст");
            return array;
        }
    }

    public boolean myMethodTwo(int[] array) {
        for (int value: array){
            if(value != 1 && value != 4) {
                return false;
            }
        }
        return (ArrayUtils.contains(array, 1) && ArrayUtils.contains(array, 4));
    }

    public boolean isFor (int[] array) {
        for (int value : array) {
            if (value == 4) {
                return true;
            }
        }
        throw new RuntimeException("В массиве отсутствуют цифры 4");
        //return false;
    }
}
