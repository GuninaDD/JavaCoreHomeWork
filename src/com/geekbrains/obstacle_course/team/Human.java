package com.geekbrains.obstacle_course.team;

public class Human implements Opponents{
    public Human(String name, double max_distance, double max_height) {
        this.name = name;
        if (max_distance >= 0) this.max_distance = max_distance;
        else this.max_distance = 0;
        if (max_height >= 0) this.max_height = max_height;
        else this.max_height = 0;
        this.onTeam = true;
    }

    String name;
    double max_distance;
    double max_height;
    boolean onTeam;

    @Override
    public String run(double distance) {
        String result;
        if (distance <= max_distance) {
            result = name + " справился с забегом.";
        } else {
            onTeam = false;
            result = name + " не может столько пробежать и выбывает.";
        }
        return result;
    }

    @Override
    public String jump(double height) {
        String result;
        if (height <= max_height) {
            result = name + " перепрыгнул препятствие.";
        } else {
            onTeam = false;
            result = name + " не может так высоко прыгнуть и выбывает.";
        }
        return result;
    }

    @Override
    public boolean isOnDistance() {
        return onTeam;
    }

    @Override
    public void info() {
        if (onTeam)
            System.out.println(this.name + " в отличной форме, он может пробежать " + this.max_distance + " м. и перепрыгнуть через " + max_height + " м.");
        else System.out.println(name + " выбыл.");
    }
}
