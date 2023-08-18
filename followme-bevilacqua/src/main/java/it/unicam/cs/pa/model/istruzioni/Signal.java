package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

public class Signal implements IstruzioniRobot{

    private final String label;

    public Signal(String label){
        this.label = label;
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.Signal(label);
    }

    @Override
    public String getTipo(){
        return "SIGNAL";
    }


}
