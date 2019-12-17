package com.geekbrains.obstacle_course.course;

import com.geekbrains.obstacle_course.team.Opponents;

public abstract class Barrier {
    abstract String doIt(Opponents opponents);

    abstract void info();
}
