package com.geekbrains.obstacle_course.team;

public class Team {
    public Team(String team_name, Opponents... opponents) {
        this.team_name = team_name;
        this.opponents = opponents;
        this.results = new StringBuilder();
    }

    private Opponents[] opponents;
    private StringBuilder results;
    String team_name;

    public Opponents[] getActions() {
        return opponents;
    }

    public void setResult(String result) {
        results.append(result).append("\n");
    }

    public void showResults() {
        System.out.println("Результаты команды " + team_name);
        System.out.println("--------------------------------");
        System.out.println(results);
        System.out.println("--------------------------------");
    }

    public void infoTeam() {
        System.out.println("Команда " + team_name + " ");
        for (Opponents o: opponents) {
            o.info();
        }
        System.out.println("--------------------------------");
    }

    public void infoWin() {
        System.out.println("Команда " + team_name + " справилась с перпятствиями следующим составом:");
        for (Opponents o: opponents) {
            if (o.isOnDistance())
                o.info();
        }
        System.out.println("--------------------------------");
    }
}
