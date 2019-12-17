package com.geekbrains.obstacle_course.course;

import com.geekbrains.obstacle_course.team.Opponents;

public class Racetrack extends Barrier{
    public Racetrack(double distance) {
        if (distance >= 0) this.distance = distance;
        else this.distance = 0;
    }

    double distance;

    @Override
    String doIt(Opponents opponents) {
        return opponents.run(distance);
    }

    @Override
    void info() {
        System.out.println("Забег на " + this.distance + " м.");
    }
}
