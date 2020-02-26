package com.geekbrains.reflection_tests;

import com.geekbrains.reflection_tests.exceptions.TestException;
import com.geekbrains.reflection_tests.tests.TestHandler;
import com.geekbrains.reflection_tests.tests.Tester;


public class Go {
    public static void main(String[] args) {
        System.out.println("!!!Если приоритет теста выходит за рамки допустимых пределов (от 1 до 10 включительно) - он не будет выполнен");
        System.out.println("-------------------------------------------------");
        Tester tester = new Tester();
        try {
            TestHandler.start(tester.getClass());
        } catch (TestException e) {
            e.printStackTrace();
        }
    }
}
