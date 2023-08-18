package it.unicam.cs.pa.model.labels;

public class Rettangolo implements Area {

    private final String label;

    private final double x;

    private final double y;

    private final double altezza;

    private final double larghezza;

    public Rettangolo(String label, double x, double y, double altezza, double larghezza){
        this.label = label;
        this.x = x;
        this.y = y;
        this.altezza = altezza;
        this.larghezza = larghezza;
    }

    @Override
    public String getLabel(){
        return this.label;
    }

    @Override
    public TipoArea getShapeType() {
        return TipoArea.RECTANGLE;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getAltezza() {
        return this.altezza;
    }

    public double getLarghezza() {
        return this.larghezza;
    }

    public boolean contiene(double x, double y){
        double distanzaX = Math.abs(x - this.x);
        double distanzaY = Math.abs(y - this.y);
        return (distanzaX <= larghezza / 2) && (distanzaY <= altezza / 2);
    }
}
