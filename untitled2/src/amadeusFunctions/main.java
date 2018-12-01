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
//        ask.record10sAudio();
//        ask.transcribedata();
        ask.LookUp lookUp = new ask.LookUp("Lightning Thief", "Wiki");
        lookUp.lookUpFunct();
    }
}