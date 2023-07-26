package it.unicam.cs.pa.followmebevilacqua;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;

public class FollowmeAppController implements Initializable {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> logger.info("tick")));
    private static final Logger logger = Logger.getLogger("it.unicam.cs.pa.followmebevilacqua");

    @FXML private JFXToggleButton runButton;
    @FXML private JFXSlider speedSlider;
    @FXML private AnchorPane mainPane;

    @FXML
    protected void runButtonAction() {
        if (runButton.isSelected())
            timeline.play();
        else
            timeline.pause();
    }
    @FXML
    protected void sliderMouseReleased() {
        timeline.setRate(speedSlider.getValue());
    }

    @FXML
    protected void loadButtonAction() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        logger.info(selectedFile.getPath());
    }

    /**
     * Eseguito al caricamento della finestra. Inizializza i componenti.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        speedSlider.setValue(3.0);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(speedSlider.getValue());

        // Aspetta che il layout venga calcolato correttamente prima di creare e aggiungere il Canvas
        // TODO crea un metodo a parte "initCanvas"
        Platform.runLater(() -> {
            Canvas canvas = new Canvas(mainPane.getWidth(), mainPane.getHeight() - 50);
            mainPane.getChildren().add(canvas);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.web("aaaaaa"));
            gc.fillRect(0, 0, canvas.getWidth(),  canvas.getHeight());

            Stage stage = (Stage) mainPane.getScene().getWindow();
            // Aggiungi un ChangeListener alla larghezza e altezza della Stage (finestra)
            stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
                double canvasWidth = newWidth.doubleValue();
                canvas.setWidth(canvasWidth);
                gc.clearRect(0, 0, canvasWidth, canvas.getHeight());
                gc.fillRect(0, 0, canvasWidth, canvas.getHeight());
            });

            stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
                double canvasHeight = newHeight.doubleValue();
                canvas.setHeight(canvasHeight - 50);
                gc.clearRect(0, 0, canvas.getWidth(), canvasHeight);
                gc.fillRect(0, 0, canvas.getWidth(), canvasHeight);
            });
        });
    }
}