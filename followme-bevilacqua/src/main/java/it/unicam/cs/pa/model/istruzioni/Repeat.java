package it.unicam.cs.pa.model.istruzioni;

import it.unicam.cs.pa.model.Azioni_Robot;

import java.util.List;

public class Repeat implements IstruzioniRobot{

    private final int cicli;
    private final List<IstruzioniRobot> istruzioni;

    public Repeat(int cicli, List<IstruzioniRobot> istruzioni) {
        this.cicli = cicli;
        this.istruzioni = istruzioni;
    }

    @Override
    public void esegui(Azioni_Robot robot){
        robot.Repeat(cicli, istruzioni);
    }

    @Override
    public String getTipo() {
        return "REPEAT";
    }

}
