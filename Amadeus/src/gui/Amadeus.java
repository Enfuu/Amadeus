package gui;

import amadeusFunctions.FunctionDecider;
import amadeusFunctions.audioClasses.JavaSoundRecorder;
import amadeusFunctions.audioClasses.TextToSpeech;
import amadeusFunctions.audioClasses.Transcription;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Amadeus implements Initializable{
    @FXML
    private ImageView amadeus;
    @FXML
    private Label amadeusox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("react/idle.png");
        Image image = new Image(file.toURI().toString());
        amadeus.setImage(image);
    }
    @FXML
    private void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.UP) {
                setListen();
                search();
        }
    }

    public void search() {
        JavaSoundRecorder.record10sAudio();
        String search = Transcription.parseData();
        String result = (FunctionDecider.decideFunctionality(search));
        if (result.contains("Weather") ) {
            amadeusox.setText(result);
            TextToSpeech tts = new TextToSpeech();;
            tts.speak(result, 0.2f, false, true);
            setAnswer();
        }
        else if (result.contains("date") || (result.contains("time"))) {
            amadeusox.setText(result);
            TextToSpeech tts = new TextToSpeech();;
            tts.speak(result, 0.2f, false, true);
            setIdle();
        }
        else if (result.contains("error") || (result.contains("I don't understand"))) {
            amadeusox.setText(result);
            TextToSpeech tts = new TextToSpeech();;
            tts.speak(result, 0.2f, false, true);
            setError();
        }
        else {
            TextToSpeech tts = new TextToSpeech();
            tts.speak(result, 0.2f, false, true);
            setListen();
        }
    }

    public void setListen() {
        File file = new File("react/listen.png");
        Image image = new Image(file.toURI().toString());
        amadeus.setImage(image);

    }


    public void setAnswer() {
        File file = new File("react/answer.png");
        Image image = new Image(file.toURI().toString());
        amadeus.setImage(image);
    }

    public void setError() {
        File file = new File("react/error.png");
        Image image = new Image(file.toURI().toString());
        amadeus.setImage(image);

    }
    public void setIdle() {
        File file = new File("react/idle.png");
        Image image = new Image(file.toURI().toString());
        amadeus.setImage(image);

    }

}
