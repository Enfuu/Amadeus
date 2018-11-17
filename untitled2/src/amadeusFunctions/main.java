package amadeusFunctions;

import Ui.ask;
import amadeusFunctions.Exceptions.nonAlphanumericalException;
import amadeusFunctions.Lookup.weatherFunction;
import amadeusFunctions.Lookup.wikiSearch;
import tk.plogitech.darksky.forecast.ForecastException;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, nonAlphanumericalException {
        ask.LookUp lookUp = new ask.LookUp("Communism", "Wiki");
        lookUp.lookUpFunct();
    }
}