module it.unicam.cs.pa.followmebevilacqua {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.logging;


    opens it.unicam.cs.pa.followmebevilacqua to javafx.fxml;
    exports it.unicam.cs.pa.followmebevilacqua;
}