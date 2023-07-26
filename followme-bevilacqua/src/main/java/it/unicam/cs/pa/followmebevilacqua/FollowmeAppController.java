package it.unicam.cs.pa.followmebevilacqua;


import java.io.File;
import java.net.URL;
import java.util.Random;
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
    Timeline timeline;
    GraphicsContext graphicsContext;
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
        initializeCanvas();

        speedSlider.setValue(2.0);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> drawRandomCircle()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(speedSlider.getValue());
    }

    protected void initializeCanvas() {
        // Aspetta che il layout venga calcolato correttamente prima di creare e aggiungere il Canvas
        Platform.runLater(() -> {
            Canvas canvas = new Canvas(mainPane.getWidth(), mainPane.getHeight() - 50);
            mainPane.getChildren().add(canvas);
            graphicsContext = canvas.getGraphicsContext2D();
            graphicsContext.setFill(Color.web("aaaaaa"));
            graphicsContext.fillRect(0, 0, canvas.getWidth(),  canvas.getHeight());

            addPaneResizeListener(canvas);

            canvas.toBack();
        });
    }

    protected void addPaneResizeListener(Canvas canvas) {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        // Aggiunge un ChangeListener alla larghezza e altezza dello Stage (finestra)
        stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double canvasWidth = newWidth.doubleValue();
            canvas.setWidth(canvasWidth);
            graphicsContext.clearRect(0, 0, canvasWidth, canvas.getHeight());
            graphicsContext.fillRect(0, 0, canvasWidth, canvas.getHeight());
        });
        stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double canvasHeight = newHeight.doubleValue();
            canvas.setHeight(canvasHeight - 50);
            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvasHeight);
            graphicsContext.fillRect(0, 0, canvas.getWidth(), canvasHeight);
        });
    }

    protected void drawRandomCircle() {
        Random random = new Random();
        double canvasWidth = graphicsContext.getCanvas().getWidth();
        double canvasHeight = graphicsContext.getCanvas().getHeight();

        double centerX = random.nextDouble() * (canvasWidth - 10) + 5; // Evita il bordo
        double centerY = random.nextDouble() * (canvasHeight - 10) + 5; // Evita il bordo
        double radius = 5;

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }
}