package ui;

import amadeusFunctions.audioClasses.JavaSoundRecorder;
import amadeusFunctions.audioClasses.TextToSpeech;
import amadeusFunctions.audioClasses.Transcription;
import ui.functionSplitterClasses.FunctionDecider;

public class main {
    public static void main(String[] args) throws Exception {
        JavaSoundRecorder.record10sAudio();
        String search = Transcription.parseData();
        TextToSpeech tts = new TextToSpeech();
        String result = (FunctionDecider.decideFunctionality(search));
        tts.speak(result, 0.2f,false,true);
    }
}