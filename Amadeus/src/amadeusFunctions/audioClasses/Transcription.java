package amadeusFunctions.audioClasses;

import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Transcription {

    private static String transcribedata() {
        IamOptions options = new IamOptions.Builder()
                .apiKey("<API KEY>") //Requires API key here from IBM Watson
                .build();

        SpeechToText speechToText = new SpeechToText(options);
        speechToText.setEndPoint("https://gateway-wdc.watsonplatform.net/speech-to-text/api");

        File audio = new File("RecordAudio.wav");

        RecognizeOptions roptions = null;
        try {
            roptions = new RecognizeOptions.Builder()
                    .audio(audio)
                    .contentType(HttpMediaType.AUDIO_WAV)
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SpeechRecognitionResults transcript = speechToText.recognize(roptions).execute();
        return transcript.toString();
    }

    public static String parseData() {
        String json = transcribedata();
        List<String> extracteddata = new ArrayList<>();
        String parts[] = json.split(",");
        String transcriptExtract = parts[1];

        String parts2[] = transcriptExtract.split(":");
        transcriptExtract = parts2[2];

        transcriptExtract = transcriptExtract.replaceAll("\"", "").trim();
        System.out.println("you said: " + transcriptExtract);

        return transcriptExtract;

    }
}

