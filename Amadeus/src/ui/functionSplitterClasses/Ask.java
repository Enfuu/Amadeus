package ui.functionSplitterClasses;

import amadeusFunctions.exceptions.nonAlphanumericalException;
import amadeusFunctions.lookUp.WeatherFunction;
import amadeusFunctions.lookUp.WikiSearch;
import tk.plogitech.darksky.forecast.ForecastException;
import java.io.IOException;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class Ask {

    /**
     * Code made by Lian-D 2018 for Amadeus-Voice-Assistant
     * This class takes handles the execution of the functions, the object will be instantiated with the "Topic" and function. then run through lookUpFunct method.
     *
     * */

    public static class lookUp {
        public String topic;
        public String function;

        //Constructor
        public lookUp(String topic, String function) {
            this.topic = topic;
            this.function = function;

        }

        //requires: Topic be alphanumerical
        //modifies: This
        //effects: Determines the function to run, if the function is a wiki, and exception is thrown that corrects the non-alphanumerical
        //topic should you have entered one
        public String lookUpFunct() throws IOException, nonAlphanumericalException {
            String functionResult = null;
            if (function == "Wiki") {
                WikiSearch wiki = new WikiSearch();
                try {
                    //tries to topic
                    functionResult = wiki.parseWikiJson(wiki.aquireJsonfromWeb(this.topic));
                } catch (IOException e) {
                    //replaces the non-alphanumerical characters with ""
                    //This also catches errors that occur when something goes wrong
                    System.out.println("System input is fucked");
                    printStackTrace();
                } catch (nonAlphanumericalException e) {
                    //replaces the non-alphanumerical characters with ""
                    System.out.println("You used a non-alphanumeric character or a space");
                    printStackTrace();
                    functionResult = wiki.parseWikiJson(wiki.aquireJsonfromWeb(this.topic.replaceAll("[\\W]|_", "")));
                }

            } else if (function == "Weather") {
                WeatherFunction weather = new WeatherFunction();
                try {
                    weather.callClass();
                    functionResult = weather.filterForecast(weather.getWeatherLocal());;
                } catch (ForecastException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No function specified");
                return "I'm sorry, I don't know how to do that yet";
            }
            return functionResult;
        }
    }
}

