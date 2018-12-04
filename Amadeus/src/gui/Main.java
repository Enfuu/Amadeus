package gui;

import amadeusFunctions.audioClasses.JavaSoundRecorder;
import amadeusFunctions.audioClasses.TextToSpeech;
import amadeusFunctions.audioClasses.Transcription;
import amadeusFunctions.exceptions.nonAlphanumericalException;
import amadeusFunctions.exceptions.nullDataException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import amadeusFunctions.FunctionDecider;

import javax.sound.sampled.*;
import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Amadeus.fxml"));
        primaryStage.setTitle("Voice Assistant");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 300, 400, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getRoot().requestFocus();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
