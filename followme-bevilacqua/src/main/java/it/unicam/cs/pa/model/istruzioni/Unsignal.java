package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

public class Unsignal implements IstruzioniRobot {

    private final String label;

    public Unsignal(String label) {
        this.label = label;
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.Unsignal(label);
    }

    @Override
    public String getTipo() {
        return "UNSIGNAL";
    }
}