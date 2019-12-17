package com.geekbrains.obstacle_course.course;

import com.geekbrains.obstacle_course.team.Opponents;
import com.geekbrains.obstacle_course.team.Team;

public class Course {
    private Barrier[] barriers;

    public Course(Barrier... barriers) {
        this.barriers = barriers;
    }

    public void barriersInfo() {
        System.out.println("Команды должны преодолеть следующие препятствия: ");
        for (Barrier b: barriers) {
            b.info();
        }
        System.out.println("--------------------------------");
    }

    public void doIt(Team team) {
        Opponents[] opponents = team.getActions();
        if (opponents.length == 0) {
            System.out.println("В этой команде никого нет");
            return;
        }
        for (Opponents o: opponents) {
            for (Barrier b: barriers) {
                team.setResult(b.doIt(o));
                if (!o.isOnDistance()) {
                    break;
                }
            }
        }

    }
}
