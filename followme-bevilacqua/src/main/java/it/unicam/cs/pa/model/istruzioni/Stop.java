package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

public class Stop implements IstruzioniRobot{

    @Override
    public void esegui(Azioni_Robot robot) {
        robot.Stop();
    }

    @Override
    public String getTipo() {
        return "STOP";
    }

}
