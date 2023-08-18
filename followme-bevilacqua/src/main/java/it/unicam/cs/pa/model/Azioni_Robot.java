package it.unicam.cs.pa.model;

import it.unicam.cs.pa.model.istruzioni.IstruzioniRobot;

import java.util.List;

public interface Azioni_Robot {

    void Move(double x, double y, double velocita);

    void Signal(String label);

    void Unsignal(String label);

    void Stop();

    void ContinueFor(int seconds);

    void Follow(String label, double distanza, double velocita);

    void Repeat(int times, List<IstruzioniRobot> instructions);

    void Until(String label, List<IstruzioniRobot> instructions);

    void DoForever(List<IstruzioniRobot> instructions);

    void Terminate();

}
