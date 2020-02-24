package com.geekbrains.tests;

import com.geekbrains.testing.Method;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMethodTwo {
    private static Method method;


    @Before
    public void init() {
        System.out.println("init Method");
        method = new Method();
    }

    @Test
    public void test1() {
        Assert.assertTrue(method.myMethodTwo(new int[]{1, 1, 1, 4, 4, 1, 4, 4}));
    }

    @Test
    public void test2() {
        Assert.assertFalse(method.myMethodTwo(new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void test3() {
        Assert.assertFalse(method.myMethodTwo(new int[]{4, 4, 4, 4}));
    }

    @Test
    public void test4() {
        Assert.assertFalse(method.myMethodTwo(new int[]{1, 4, 4, 1, 1, 4, 3}));
    }
}
