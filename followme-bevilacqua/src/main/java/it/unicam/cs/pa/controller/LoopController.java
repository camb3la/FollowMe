package it.unicam.cs.pa.controller;

import it.unicam.cs.pa.model.istruzioni.IstruzioniRobot;

import java.util.*;

public class LoopController {

    private Stack<List<IstruzioniRobot>> istruzioni;

    private Map<List<IstruzioniRobot>, Integer> mappaPuntatori;

    private Map<List<IstruzioniRobot>, IstruzioniRobot> mappaLoop;

    public LoopController(){
        this.istruzioni = new Stack<>();
        this.mappaPuntatori = new HashMap<>();
        this.mappaLoop = new HashMap<>();
    }

    public void Start(List<IstruzioniRobot> istruzione, int valore, IstruzioniRobot loop){
        istruzioni.push(istruzione);
        mappaPuntatori.put(istruzione,valore);
        mappaLoop.put(istruzione,loop);
    }

    public List<IstruzioniRobot> End(){
        List<IstruzioniRobot> istruzioni = this.istruzioni.pop();
        mappaPuntatori.remove(this.istruzioni);
        mappaLoop.remove(this.istruzioni);

        return istruzioni;
    }

    public List<IstruzioniRobot> Top(){
        return this.istruzioni.peek();
    }

    public int getPuntatore(List<IstruzioniRobot> istruzione){
        return this.mappaPuntatori.get(istruzione);
    }

    public int TopPuntatore(){
        List<IstruzioniRobot> istruzione = Top();
        return this.mappaPuntatori.get(istruzione);
    }

    public int TopPuntatore(List<IstruzioniRobot> istruzione){
        return this.mappaPuntatori.get(istruzione);
    }

    public IstruzioniRobot TopLoop(){
        List<IstruzioniRobot> istruzione = Top();
        return this.mappaLoop.get(istruzione);
    }

    public IstruzioniRobot TopLoop(List<IstruzioniRobot> istruzione) {
        return this.mappaLoop.get(istruzione);
    }

    public void Icrementa(List<IstruzioniRobot> istruzione){
        int puntatoreIstruzione = TopPuntatore(istruzione);
        mappaPuntatori.replace(istruzione, puntatoreIstruzione, puntatoreIstruzione + 1);
    }

    public void Icrementa() {
        List<IstruzioniRobot> istruzione = Top();
        int puntatoreIstruzione = TopPuntatore(istruzione);

        if (puntatoreIstruzione >= istruzione.size()) {
            throw new IllegalStateException("Impossibile incrementare il puntatore");
        }

        mappaPuntatori.replace(istruzione, puntatoreIstruzione, puntatoreIstruzione + 1);
    }

    public void Restart() {
        List<IstruzioniRobot> istruzione = Top();
        int puntatoreIstruzione = TopPuntatore(istruzione);
        mappaPuntatori.replace(istruzione, puntatoreIstruzione, 0);
    }

    public void Pulisci() {
        this.istruzioni.clear();
        this.mappaPuntatori.clear();
        this.mappaLoop.clear();
    }

    public boolean Contiene() {
        return !istruzioni.isEmpty();
    }

















}
