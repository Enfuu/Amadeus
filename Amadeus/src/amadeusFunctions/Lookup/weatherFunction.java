package amadeusFunctions.lookUp;

import amadeusFunctions.function;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

import java.util.ArrayList;;
import java.util.List;

public class WeatherFunction extends function {
    /**
     * Code made by Lian-D 2018 for Amadeus-Voice-Assistant
     * This class handles the weather search using the api provided by DarkSky
     *
     * */

    public ForecastRequest request;
    private String key;

    public WeatherFunction() {
        ForecastRequest request;
        key = "<API key>"; //Requires Darksky API key here
    }

    //requires: connection to internet
    //effects: retrieves the data from the Dark Sky weather api as a raw JSON file from vancouver geocoordinates
    public String getWeatherLocal() throws ForecastException {
        this.request = new ForecastRequestBuilder()
                .key(new APIKey(key))
                .location(new GeoCoordinates(new Longitude(-123.1207), new Latitude(49.2827)))
                .language(ForecastRequestBuilder.Language.en)
                .build();
        DarkSkyClient client = new DarkSkyClient();
        String forecast = client.forecastJsonString(request);
        return forecast;
    }
    //requires: connection to internet
    //effects: retrieves the data from the Dark Sky weather api as a raw JSON file given the geocoordinate
    //and language
    public String getWeatherLocations(double longitude, double latitude) throws ForecastException {
        this.request = new ForecastRequestBuilder()
                .key(new APIKey(key))
                .location(new GeoCoordinates(new Longitude(longitude), new Latitude(latitude)))
                .language(ForecastRequestBuilder.Language.en)
                .build();
        DarkSkyClient client = new DarkSkyClient();
        String forecast = client.forecastJsonString(request);
        return forecast;
    }

    //effects: filters the forecast json so that we isolate the timezone, summary data and temperature
    public String filterForecast(String forecast){
        List<String> extracteddata = new ArrayList<>();
        String parts[] = forecast.split(",");
        String timezonedata = parts[2];
        String summarydata = parts[4];
        String temperaturedata = parts[11];

        String timezoneGetter[] = timezonedata.split(":");
        String timezone = timezoneGetter[1];

        String summaryGetter[] =  summarydata.split(":");
        String summary = summaryGetter[1];

        String temperatureGetter[] = temperaturedata.split(":");
        String temperature = temperatureGetter[1];

        return "timezone is " + timezone + ", " + "weather is " + summary + " and temperature is " + temperature;
    }

}

