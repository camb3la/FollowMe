package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

import java.util.List;

public class Until implements IstruzioniRobot{

    private final String label;

    private final List<IstruzioniRobot> istruzioni;

    public Until(String label, List<IstruzioniRobot> istruzioni){
        this.label = label;
        this.istruzioni = istruzioni;
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.Until(label, istruzioni);
    }

    @Override
    public String getTipo(){
        return "UNTIL";
    }


}
