package com.geekbrains.prof_lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    @SafeVarargs
    public Box(T... fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    @SafeVarargs
    public final void addFruit(T... fruits) {
        this.fruits.addAll(Arrays.asList(fruits));
    }

    @SafeVarargs
    public final void removeFruit(T... fruits) {
        for (T fruit : fruits) {
            this.fruits.remove(fruit);
        }
    }

   //public ArrayList<T> getFruits() {
   //    return new ArrayList<>(fruits);
   //}

    public void clearBox() {
        fruits.clear();
    }

    public float getWeight() {
        if (fruits.size() == 0) return 0;
        float weight = 0;
        for (T fruit : fruits) weight += fruit.getWeight();
        return weight;
    }

    public boolean compareTo(Box box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001f;
    }

    public void transferFruit(Box<? super T> box) {
        box.fruits.addAll(this.fruits);
        clearBox();
    }

}
