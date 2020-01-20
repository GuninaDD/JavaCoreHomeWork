package com.geekbrains.threads;

import java.util.Arrays;

public class Go {

    static final int SIZE = 10000000;
    static final int H = SIZE / 2;

    public static void main(String[] args) {
        doCalculationOne(createArray());
        doCalculationTwo(createArray());
    }

    private static float[] createArray() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        return arr;
    }


    private static void doCalculationOne(float[] arr) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("---------------------------------");
        System.out.print("Время выполнения однопоточного метода: ");
        System.out.println(System.currentTimeMillis() - a);
        System.out.println("---------------------------------");
    }


    private static void doCalculationTwo(float[] arr) {
        float[] arrOne = new float[H];
        float[] arrTwo = new float[H];
        long b = System.currentTimeMillis();
        System.arraycopy(arr, 0, arrOne, 0, H);
        System.arraycopy(arr, H, arrTwo, 0, H);
        Thread calcOne = new Thread(() -> {
            for (int i = 0; i < arrOne.length; i++) {
                arrOne[i] = (float) (arrOne[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        calcOne.start();
        Thread calcTwo = new Thread(() -> {
            for (int i = 0; i < arrTwo.length; i++) {
                arrTwo[i] = (float) (arrTwo[i] * Math.sin(0.2f + (i + H) / 5) * Math.cos(0.2f + (i + H) / 5) * Math.cos(0.4f + (i + H) / 2));
            }
        });
        calcTwo.start();

        try {
            calcOne.join();
            calcTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arrOne, 0, arr, 0, H);
        System.arraycopy(arrTwo, 0, arr, H, H);
        System.out.println("---------------------------------");
        System.out.print("Время выполнения двухпоточного метода: ");
        System.out.println(System.currentTimeMillis() - b);
        System.out.println("---------------------------------");
    }
}
