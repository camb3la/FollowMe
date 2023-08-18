package it.unicam.cs.pa.model.labels;

public interface Area {

    String getLabel();

    TipoArea getShapeType();

    boolean contiene(double x, double y);

}
