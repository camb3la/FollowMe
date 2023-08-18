package it.unicam.cs.pa.model;

import it.unicam.cs.pa.model.labels.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Oggetto {
    protected double x;
    protected double y;
    protected double direzioneX;
    protected double direzioneY;
    protected double velocitaCorrente;
    protected double velocitaPrecedente;
    protected double angolo;
    private List<Area> Ambiente;

    public Oggetto(){
        Ambiente = new ArrayList<>();
        x = 0;
        y = 0;
        direzioneX = 0;
        direzioneY = 0;
        velocitaCorrente = 0;
        velocitaPrecedente = 0;
        angolo = 0;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getVelocitaCorrente() {
        return velocitaCorrente;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public Optional<String> getLabelAmbiente(){
        for (Area a: Ambiente) {
            if (a.contiene(x,y)){
                return Optional.of(a.getLabel());
            }
        }

        return Optional.empty();
    }

}
