package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

public class End implements IstruzioniRobot{

    @Override
    public void esegui(Azioni_Robot robot){
        robot.Terminate();
    }

    @Override
    public String getTipo(){
        return "END";
    }

}
