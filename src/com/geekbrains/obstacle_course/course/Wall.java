package com.geekbrains.obstacle_course.course;

import com.geekbrains.obstacle_course.team.Opponents;

public class Wall extends Barrier{
    public Wall(double height) {
        if (height >= 0) this.height = height; else this.height = 0;
    }

    double height;

    @Override
    String doIt(Opponents opponents) {
        return opponents.jump(height);
    }

    @Override
    void info() {
        System.out.println("Прыжок через стену, высотой " + this.height + " м.");
    }

}
