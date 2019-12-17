package com.geekbrains.obstacle_course.team;

public interface Opponents {
    String run(double distance);
    String jump(double height);

    boolean isOnDistance();

    void info();
}
