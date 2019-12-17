package com.geekbrains.obstacle_course;

import com.geekbrains.obstacle_course.course.Course;
import com.geekbrains.obstacle_course.course.Racetrack;
import com.geekbrains.obstacle_course.course.Wall;
import com.geekbrains.obstacle_course.team.Cat;
import com.geekbrains.obstacle_course.team.Human;
import com.geekbrains.obstacle_course.team.Robot;
import com.geekbrains.obstacle_course.team.Team;

import java.util.Random;

public class Go {
    public static final int DISTANCE = 10000;
    public static final int HEIGHT = 20;
    public static final int WALL = 10;
    public static final int RACETRACK = 5000;
    public static Random random = new Random();

    public static void main(String[] args) {

        Course course = new Course(new Racetrack(random.nextInt(RACETRACK)), new Wall(random.nextInt(WALL)));

        Team team = new Team("Ромашки", new Human("Вася", random.nextInt(DISTANCE), random.nextInt(HEIGHT)),
                new Cat("Барсик", random.nextInt(DISTANCE), random.nextInt(HEIGHT)),
                new Robot("R2D2", random.nextInt(DISTANCE), random.nextInt(HEIGHT)),
                new Human("Петя", random.nextInt(DISTANCE), random.nextInt(HEIGHT)));

        team.infoTeam();
        course.barriersInfo();
        course.doIt(team);
        team.showResults();
        team.infoWin();
    }
}
