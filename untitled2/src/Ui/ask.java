package Ui;

import amadeusFunctions.Exceptions.nonAlphanumericalException;
import amadeusFunctions.Lookup.weatherFunction;
import amadeusFunctions.Lookup.wikiSearch;
import tk.plogitech.darksky.forecast.ForecastException;

import java.io.IOException;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class ask {

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
            if (function == "Wiki"){
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
                    System.out.println("You used a non-alphanumeric character");
                    printStackTrace();
                    wiki.parseWikiJson(wiki.aquireJsonfromWeb(this.topic.replaceAll("[\\W]|_", "")));
                }

            }
            else if (function == "Weather"){
                weatherFunction weather = new weatherFunction();
                try {
                    weather.callClass();
                    System.out.println(weather.filterForecast(weather.getWeatherLocal()));
                } catch (ForecastException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("No function specified");
            }
        }
    }

    public class UserFunctions {
    }

}
