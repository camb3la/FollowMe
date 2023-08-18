package it.unicam.cs.pa.controller;

import it.unicam.cs.pa.model.istruzioni.IstruzioniRobot;

import java.util.List;
import java.util.Stack;

public class Parsing {

    private Stack<List<IstruzioniRobot>> stack;

    public Parsing(){
        this.stack = new Stack<>();
    }

    public void Inizio(List<IstruzioniRobot> istruzioni) {
        this.stack.push(istruzioni);
    }

    public List<IstruzioniRobot> Fine() {
        return this.stack.pop();
    }

    public List<IstruzioniRobot> Top() {
        return this.stack.peek();
    }

    public void clear() {
        this.stack.clear();
    }

    public boolean Vuoto() {
        return !stack.isEmpty();
    }













}
