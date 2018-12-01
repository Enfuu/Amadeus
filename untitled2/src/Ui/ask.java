package Ui;

import amadeusFunctions.Exceptions.nonAlphanumericalException;
import amadeusFunctions.Lookup.weatherFunction;
import amadeusFunctions.Lookup.wikiSearch;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import tk.plogitech.darksky.forecast.ForecastException;

import java.io.File;
import java.io.IOException;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class ask {

    public static void transcribedata() throws Exception {
        IamOptions options = new IamOptions.Builder()
                .apiKey("aGUZ4JpGbf7WNYnmcIh4YiVqjFleXafwwDdbmCqqtAif") //Requires API key here from IBM Watson
                .build();

        SpeechToText speechToText = new SpeechToText(options);
        speechToText.setEndPoint("https://gateway-wdc.watsonplatform.net/speech-to-text/api");

        File audio = new File("RecordAudio.wav");

        RecognizeOptions roptions = new RecognizeOptions.Builder()
                .audio(audio)
                .contentType(HttpMediaType.AUDIO_WAV)
                .build();

        SpeechRecognitionResults transcript = speechToText.recognize(roptions).execute();
        System.out.println(transcript);
    }


    public static void record10sAudio() {
        final JavaSoundRecorder recorder = new JavaSoundRecorder();

        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
        stopper.start();

        recorder.start();
    }


    public static class LookUp {
        public String topic;
        public String function;

        //Constructor
        public LookUp(String topic, String function) {
            this.topic = topic;
            this.function = function;

        }

        //requires: Topic be alphanumerical
        //modifies: This
        //effects: Determines the function to run, if the function is a wiki, and exception is thrown that corrects the non-alphanumerical
        //topic should you have entered one
        public void lookUpFunct() throws IOException, nonAlphanumericalException {
            if (function == "Wiki") {
                wikiSearch wiki = new wikiSearch();
                try {
                    //tries to topic
                    wiki.parseWikiJson(wiki.aquireJsonfromWeb(this.topic));
                } catch (IOException e) {
                    //replaces the non-alphanumerical characters with ""
                    //This also catches errors that occur when something goes wrong
                    System.out.println("System input is fucked");
                    printStackTrace();
                    wiki.parseWikiJson(wiki.aquireJsonfromWeb(this.topic.replaceAll("[\\W]|_", "")));
                } catch (nonAlphanumericalException e) {
                    //replaces the non-alphanumerical characters with ""
                    System.out.println("You used a non-alphanumeric character or a space");
                    printStackTrace();
                    wiki.parseWikiJson(wiki.aquireJsonfromWeb(this.topic.replaceAll("[\\W]|_", "")));
                }

            } else if (function == "Weather") {
                weatherFunction weather = new weatherFunction();
                try {
                    weather.callClass();
                    System.out.println(weather.filterForecast(weather.getWeatherLocal()));
                } catch (ForecastException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No function specified");
            }
        }
    }
}

