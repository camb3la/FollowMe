package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

public interface IstruzioniRobot {

    void esegui(Azioni_Robot robot);

    String getTipo();
}
