package com.geekbrains.custom_exceptions;

public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException() {
        super("Неверное значение размерности массива.");

    }
}
