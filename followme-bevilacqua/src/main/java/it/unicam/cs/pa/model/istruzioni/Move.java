package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

public class Move implements IstruzioniRobot{

    private final double x;
    private final double y;
    private final double velocita;

    public Move(double[] args) {
        this.x = args[0];
        this.y = args[1];
        this.velocita = args[2];
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.Move(x, y, velocita);
    }

    @Override
    public String getTipo(){
        return "MOVE";
    }

}
