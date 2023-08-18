package it.unicam.cs.pa.model;

import it.unicam.cs.pa.controller.LoopController;
import it.unicam.cs.pa.model.istruzioni.*;
import it.unicam.cs.pa.model.labels.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Robot extends Oggetto implements Azioni_Robot {

    private List<String> signals;

    private List<Robot> robotSegnalanti;

    private List<IstruzioniRobot> istruzioni;

    private final LoopController loopController;

    private long durataEsecuzione;

    private boolean terminato;

    private Optional<Move> ultimoMovimento;



    public Robot(){
        super();

        signals = new ArrayList<>();
        robotSegnalanti = new ArrayList<>();
        istruzioni = new ArrayList<>();
        loopController = new LoopController();
        durataEsecuzione = 0;
        terminato = false;
    }

    public List<String> getSignals(){
        return this.signals;
    }

    public List<Robot> getRobotSegnalanti(){
        return this.robotSegnalanti;
    }

    public void robotSegnalanti(List<Robot> robots){
        robots = robots.stream().filter(robot -> !robot.equals(this)).collect(Collectors.toList());
        robotSegnalanti.clear();
        robotSegnalanti.addAll(robots);
    }


    @Override
    public void Move(double x, double y, double velocita){
        this.direzioneX = getX() + x;
        this.direzioneY = getY() + y;
        this.velocitaCorrente = velocita;


    }

    @Override
    public void Signal(String label){
        this.signals.add(label);
    }

    @Override
    public void Unsignal(String label){
        if(!signals.contains(label)){
            throw new IllegalArgumentException("Non puo essere rimossa una label inesistente");
        }
        else{
            signals.remove(label);
        }
    }

    @Override
    public void Stop(){
        this.velocitaPrecedente = this.velocitaCorrente;
        this.velocitaCorrente = 0;
    }

    @Override
    public void ContinueFor(int s){
        durataEsecuzione = s * 1000L;
    }

    @Override
    public void Follow(String label, double distanza, double velocita){
        List<Robot> robotValidi = robotValidi(label,distanza);

        if (robotValidi.isEmpty()){
            MuoviRandomFollow(distanza,velocita);
        }
        else {
            double[] direzione = CalcolaDirezione(robotValidi);
            double[] cordinateArrivo = CalcolaCordinate(direzione,distanza);
            Move(cordinateArrivo[0], cordinateArrivo[1], velocita);

            return;
        }
    }

    private List<Robot>  robotValidi(String label, double distanza){
        return robotSegnalanti.stream().filter(robot -> robot.getSignals().contains(label))
                .filter(robot -> CalcolaDistanza(robot)<= distanza).toList();
    }

    private double CalcolaDistanza(Robot robot) {
        double x = robot.getX() - getX();
        double y = robot.getY() - getY();
        return Math.sqrt(x * x + y * y);
    }

    private void MuoviRandomFollow(double distanza, double velocita){
        double randomX = getX() + (Math.random() * 2 - 1) * distanza;
        double randomY = getY() + (Math.random() * 2 - 1) * distanza;
        Move(randomX, randomY, velocita);
    }

    private double[] CalcolaDirezione(List<Robot> robotValidi){

        double mediaX = 0 ;
        double mediaY = 0;
        double contatore = 0;
        for (Robot r: robotValidi) {
            mediaX += ((Robot) robotValidi).getX();
            mediaY += ((Robot) robotValidi).getY();
            contatore++;
        }
        mediaX = mediaX / contatore;
        mediaY = mediaY / contatore;
        return Direzione(CalcolaDirezioneX(mediaX),CalcolaDirezioneY(mediaY));
    }

    private double[] CalcolaCordinate(double[] direction, double distance) {
        double x = getX() + distance * direction[0];
        double y = getY() + distance * direction[1];
        return new double[]{x, y};
    }

    private double[] Direzione(double x, double y){
        double direzione = Math.sqrt(x * x + y + y);
        if (direzione !=0){
            x /= direzione;
            y /= direzione;
        }
        return new double[]{x,y};
    }


    public double CalcolaDirezioneX(double mediax){
        return mediax - getX();
    }

    public double CalcolaDirezioneY(double mediay){
        return mediay - getY();
    }

    @Override
    public void Repeat(int volte, List<IstruzioniRobot> istruzioni){
        loopController.Icrementa();
        List<IstruzioniRobot> buffer = new ArrayList<>();

        for (int i = 0; i < volte; i++){
            buffer.addAll(istruzioni);
        }

        loopController.Start(buffer, -1, new Repeat(volte, istruzioni));
    }

    @Override
    public void Until(String label, List<IstruzioniRobot> istruzioni) {
        loopController.Icrementa();

        boolean condizione = controllaUntilCondizione(label);
        if (!condizione){
            loopController.Start(istruzioni, -1, new Until(label,istruzioni));
        }
    }

    @Override
    public void DoForever(List<IstruzioniRobot> istruzioni) {
        loopController.Start(istruzioni, -1, new DoForever(istruzioni));
    }

    @Override
    public void Terminate() {
        terminato = true;
    }


    public void Esecuzione(List<IstruzioniRobot> istruzioni){
        terminato = false;
        this.istruzioni.clear();

        this.istruzioni.addAll(istruzioni);
        loopController.Start(istruzioni,0, new End());

    }

    public boolean isTerminato(){
        return terminato;
    }

    public void ultimoMovimento(){
        if (!ultimoMovimento.isEmpty()){
            ultimoMovimento.get().esegui(this);
        }
    }

    private void eseguiIstruzioni(int puntatoreCorrente){
        IstruzioniRobot istruzioneCorrente = loopController.Top().get(puntatoreCorrente);

        if (!istruzioneCorrente.getTipo().equals("MOVE")){
            ultimoMovimento();
        }
        else {
            eseguiIncrementa(istruzioneCorrente);
        }
    }

    public void eseguiIncrementa(IstruzioniRobot istruzione){
        istruzione.esegui(this);
        loopController.Icrementa();
    }

    public void fineCiclo(){
        IstruzioniRobot istruzioneLoop = loopController.TopLoop();
        loopController.End();

        switch (istruzioneLoop.getTipo()){
            case "REPEAT" -> {
                return;
            }
            case "END" ->{
                istruzioneLoop.esegui(this);
                return;
            }
        }
        eseguiIncrementa(istruzioneLoop);
    }

    private void aggiornaUltimoMovimento(double[] parametri){
        ultimoMovimento = Optional.of(new Move(parametri));
    }

    private boolean controllaUntilCondizione(String label) {
        Optional<String> labelAmbiente = getLabelAmbiente();
        return labelAmbiente.isPresent() && labelAmbiente.get().equals(label);
    }

















}
