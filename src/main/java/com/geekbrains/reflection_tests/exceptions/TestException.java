package com.geekbrains.reflection_tests.exceptions;

public class TestException extends Exception {
    public TestException() {
    }

    public TestException(String msg) {
        System.out.println(msg);
    }

}
