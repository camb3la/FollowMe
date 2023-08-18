package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

public class Follow implements IstruzioniRobot{

    private final String label;

    private final double distanza;

    private final double velocita;

    public Follow(String label, double distanza, double velocita){
        this.label = label;
        this.distanza = distanza;
        this.velocita = velocita;
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.Follow(label,distanza,velocita);
    }

    @Override
    public String getTipo(){
        return "FOLLOW";
    }



}
