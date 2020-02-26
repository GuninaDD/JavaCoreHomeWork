package com.geekbrains.reflection_tests.tests;

import com.geekbrains.reflection_tests.annotations.AfterSuite;
import com.geekbrains.reflection_tests.annotations.BeforeSuite;
import com.geekbrains.reflection_tests.annotations.Test;

public class Tester {
    @AfterSuite
    public void afterTest() {
        System.out.println("After test");
    }

    @Test(priority = 10)
    public void test1() {
        System.out.println("Test 1 priority 10");
    }

    @Test()
    public void test2() {
        System.out.println("Test 2 priority 1");
    }

    @Test(priority = 7)
    public void test3() {
        System.out.println("Test 3 priority 7");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("Test 4 priority 4");
    }

    @Test(priority = 15)
    public void test5() {
        System.out.println("Test 5 priority 15");
    }

    @BeforeSuite
    public void beforeTest() {
        System.out.println("Before test");
    }
}
