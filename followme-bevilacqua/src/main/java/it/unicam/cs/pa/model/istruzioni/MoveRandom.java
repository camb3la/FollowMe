package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

import java.util.Random;

public class MoveRandom implements IstruzioniRobot{

    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double velocita;


    private final Random random = new Random();

    public MoveRandom(double[] parametri){
        this.x1 = parametri[0];
        this.x2 = parametri[1];
        this.y1 = parametri[2];
        this.y2 = parametri[3];
        this.velocita = parametri[4];
    }

    @Override
    public void esegui(Azioni_Robot robot) {
        double x = cordinateRandom(x1, x2);
        double y = cordinateRandom(y1, y2);
        robot.Move(x, y, velocita);
    }


    private double cordinateRandom(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }


    @Override
    public String getTipo() {
        return "MOVE";
    }

}
