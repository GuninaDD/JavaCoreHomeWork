package com.geekbrains.custom_exceptions;

public class MyArrayDataException extends RuntimeException{
    public MyArrayDataException(int i, int j) {
        super("Ошибка преобразования данных в ячейке " + i + " " + j); //для отладки оставляем изначальные индексы
        this.i =  i;
        this.j =  j;
    }

    // прибавляем 1 к индексам ячейки, чтобы нумерация была с 1, а не с 0, для удобства чтения пользователя
    public int getI() {
        return i + 1;
    }

    public int getJ() {
        return j + 1;
    }

    int i;
    int j;

}
