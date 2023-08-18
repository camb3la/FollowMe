package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;
import it.unicam.cs.pa.model.Robot;

public class Continue implements IstruzioniRobot {
    private final int secondi;

    public Continue(int secondi){
        this.secondi = secondi;
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.ContinueFor(secondi);
    }

    @Override
    public String getTipo(){
        return "CONTINUE";
    }
}
