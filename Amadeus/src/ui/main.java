package ui;

import amadeusFunctions.audioClasses.JavaSoundRecorder;
import amadeusFunctions.audioClasses.Transcription;
import ui.functionSplitterClasses.FunctionDecider;

public class main {
    //todo: implememt Words API for definitions
    //todo: advice slips API
    //todo: implement Youtube background API for music i guess
    public static void main(String[] args) throws Exception {
        JavaSoundRecorder.record10sAudio();
        String search = Transcription.parseData();
        System.out.println(FunctionDecider.decideFunctionality(search));

    }
}