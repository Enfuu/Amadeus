package amadeusFunctions;

import Ui.JavaSoundRecorder;
import Ui.ask;
import amadeusFunctions.Exceptions.nonAlphanumericalException;
import amadeusFunctions.Lookup.weatherFunction;
import amadeusFunctions.Lookup.wikiSearch;
import tk.plogitech.darksky.forecast.ForecastException;


import java.io.IOException;

public class main {
    public static void main(String[] args) throws Exception {
        ask.transcribedata();
//        ask.record10sAudio();
        ask.LookUp lookUp = new ask.LookUp("Communism", "Wiki");
        lookUp.lookUpFunct();
    }
}