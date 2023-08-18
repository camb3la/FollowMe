package it.unicam.cs.pa.controller;

import it.unicam.cs.pa.model.istruzioni.*;
import it.unicam.cs.pa.model.labels.Area;
import it.unicam.cs.pa.model.labels.Cerchio;
import it.unicam.cs.pa.model.labels.Rettangolo;
import it.unicam.cs.pa.model.labels.TipoArea;
import it.unicam.cs.pa.utilities.FollowMeParserHandler;
import it.unicam.cs.pa.utilities.ShapeData;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaRobot implements FollowMeParserHandler {

    private final List<IstruzioniRobot> istruzioni;

    private final List<Area> Aree;

    private final Parsing parsing;

    public ProgrammaRobot(){
        this.istruzioni = new ArrayList<>();
        this.Aree = new ArrayList<>();
        this.parsing = new Parsing();
    }


    @Override
    public void parsingStarted(){
        parsing.clear();
        istruzioni.clear();

        parsing.Inizio(istruzioni);

    }

    @Override
    public void parsingDone() {
        parsing.Fine();

        if (parsing.Vuoto()) {
            throw new IllegalStateException("Body tracker detected orphaned body.");
        }
    }

    @Override
    public void moveCommand(double[] parametri){
        IstruzioneCorrente().add(new Move(parametri));
    }

    @Override
    public void moveRandomCommand(double[] parametri){
        IstruzioneCorrente().add(new MoveRandom(parametri));
    }


    @Override
    public void signalCommand(String label) {
        IstruzioneCorrente().add(new Signal(label));
    }

    @Override
    public void unsignalCommand(String label) {
        IstruzioneCorrente().add(new Unsignal(label));
    }

    @Override
    public void followCommand(String label, double[] args) {
        IstruzioneCorrente().add(new Follow(label, args[0], args[1]));
    }

    @Override
    public void stopCommand() {
        IstruzioneCorrente().add(new Stop());
    }

    @Override
    public void continueCommand(int s) {
        IstruzioneCorrente().add(new Continue(s));
    }

    @Override
    public void repeatCommandStart(int n) {
        
    }

    @Override
    public void untilCommandStart(String label) {

    }

    @Override
    public void doForeverStart() {

    }

    @Override
    public void doneCommand() {

    }


    private List<IstruzioniRobot> IstruzioneCorrente() {
        return parsing.Top();
    }

    public void AggiungiArea(List<ShapeData> aree){
        Aree.clear();
        for (ShapeData area: aree) {
            TipoArea tipo = TipoArea.valueOf(area.shape().toUpperCase());
            switch (tipo){
                case CIRCLE -> AggiungiCerchio(area);
                case RECTANGLE -> AggiungiRettangolo(area);
                default -> throw new IllegalArgumentException("Tipo area invalido");
            }

        }
    }

    private void AggiungiCerchio(ShapeData area){
        double[] parametri = area.args();
        if (parametri.length == 3){
            Aree.add(new Cerchio(area.label(), parametri[0], parametri[1], parametri[2]));
        }
        else {
            throw new IllegalArgumentException("Parametri errati o insufficienti per il cerchio");
        }
    }

    private void AggiungiRettangolo(ShapeData area){
        double[] parametri = area.args();
        if (parametri.length == 4){
            Aree.add(new Rettangolo(area.label(), parametri[0], parametri[1], parametri[2], parametri[3]));
        }
        else {
            throw new IllegalArgumentException("Parametri errati o insufficienti per il rettangolo");
        }
    }











}
