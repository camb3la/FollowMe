package it.unicam.cs.pa.controller;

import it.unicam.cs.pa.model.Robot;
import it.unicam.cs.pa.model.istruzioni.IstruzioniRobot;

import java.util.ArrayList;
import java.util.List;

public class RobotController {

    private final List<Robot> robots;


    public RobotController(int robots) {

        this.robots = new ArrayList<>();

        for (int i = 0; i < robots; i++) {
            this.robots.add(new Robot());
        }
    }

    public List<Robot> getRobots() {
        return robots;
    }


    public void programSwarm(List<IstruzioniRobot> istruzioni){
        robots.forEach(r -> r.Esecuzione(istruzioni));
    }


    public boolean isSwarmDone() {
        return robots.stream().allMatch(Robot::isTerminato);
    }


}
