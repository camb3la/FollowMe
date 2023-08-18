package it.unicam.cs.pa.followmebevilacqua;

import it.unicam.cs.pa.utilities.FollowMeParser;
import it.unicam.cs.pa.utilities.FollowMeParserHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FollowmeApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FollowmeApp.class.getResource("FollowmeAppView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        

        launch();
    }
}