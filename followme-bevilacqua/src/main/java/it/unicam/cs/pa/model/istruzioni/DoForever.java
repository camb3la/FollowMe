package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

import java.util.List;

public class DoForever implements IstruzioniRobot{

    private final List<IstruzioniRobot> istruzioni;

    public DoForever(List<IstruzioniRobot> istruzioni){
        this.istruzioni = istruzioni;
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.DoForever(istruzioni);
    }

    @Override
    public String getTipo(){
        return "DOFOREVER";
    }

}
