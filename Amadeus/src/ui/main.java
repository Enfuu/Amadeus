package ui;

import amadeusFunctions.audioClasses.JavaSoundRecorder;
import amadeusFunctions.audioClasses.TextToSpeech;
import amadeusFunctions.audioClasses.Transcription;
import ui.functionSplitterClasses.FunctionDecider;

public class main {
    //todo: implememt Words API for definitions
    //todo: advice slips API
    //todo: implement Youtube background API for music i guess
    //todo: implement Time and date, calendar stuff
    public static void main(String[] args) throws Exception {
        JavaSoundRecorder.record10sAudio();
        String search = Transcription.parseData();
        TextToSpeech tts = new TextToSpeech();
        tts.speak((FunctionDecider.decideFunctionality(search)), 0.2f,false,true);


    }
}