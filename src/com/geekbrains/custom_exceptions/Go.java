package com.geekbrains.custom_exceptions;

import java.util.Scanner;

public class Go {

    private static final int SIZE = 4;

    public static void main(String[] args) {
        String[][] arr;
        try {
            arr = initArray(SIZE, SIZE);
            sumRefactorArray(arr);
        } catch (NegativeArraySizeException eN) {
            System.out.println("Отрицательное значение размерности массива, массив не инициализирован.");
            eN.printStackTrace();
            System.out.println("Программа завершена.");
        } catch (MyArraySizeException eS) {
            System.out.println("Неверное значение размерности массива, массив не инициализирован.");
            eS.printStackTrace();
            System.out.println("Программа завершена.");
        } catch (MyArrayDataException eD) {
            System.out.println("Ошибка преобразования данных в ячейке " + eD.getI() + " " + eD.getJ());
            eD.printStackTrace();
            System.out.println("Программа завершена.");
        }
    }

    public static boolean checkArraySize(String[][] arr) {
        boolean result = true;
        for (String[] strings : arr) {
            if ((arr.length != strings.length) || (arr.length != 4)) {
                result = false;
                throw new MyArraySizeException();
            }
        }
        return result;
    }

    public static String[][] initArray(int row, int col) {
        String[][] arr = new String[row][col];
        if (checkArraySize(arr)) {
            System.out.println("Введите элементы массива: ");
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < row; i++) {
                System.out.println();
                for (int j = 0; j < col; j++) {
                    arr[i][j] = scanner.nextLine();
                }

            }
            scanner.close();
            printArray(arr);
        }
        return arr;
    }

    public static void printArray(String[][] arr) {
        System.out.println("Массив содержит данные: ");

        for (String[] strings : arr) {
            System.out.println();
            for (String string : strings) {
                System.out.print(string + " ");
            }
        }

        System.out.println();
    }

    public static void sumRefactorArray(String[][] sArr) {
        int[][] iArr = new int[sArr.length][sArr.length];

        int sum = 0;

        for (int i = 0; i < iArr.length; i++) {
            for (int j = 0; j < iArr[i].length; j++) {
                try {
                    iArr[i][j] = Integer.parseInt(sArr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
                sum += iArr[i][j];
            }
        }

        System.out.println("Сумма элементов массива = " + sum);
    }
}
