package it.unicam.cs.pa.model.labels;

public class Cerchio implements Area {

    private final String label;

    private final double x;

    private final double y;

    private final double raggio;

    public Cerchio(String label, double x, double y, double r) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.raggio = r;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public TipoArea getShapeType() {
        return TipoArea.CIRCLE;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRaggio() {
        return this.raggio;
    }


    public boolean contiene(double x, double y) {
        double distanza = Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));
        return distanza <= raggio;
    }
}
