package amadeusFunctions.lookUp;

import amadeusFunctions.exceptions.nonAlphanumericalException;
import amadeusFunctions.exceptions.nullDataException;
import amadeusFunctions.function;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class WikiSearch extends function {
    /**
     * Code made by Lian-D 2018 for Amadeus-Voice-Assistant
     * This class handles the wikipedia search using the REST api provided by wikimedia,
     *@Author Lian Duan
     * */

    public class Root {
        String batchcomplete;
        Query query;
    }

    public class Query {
        Map<String, Page> pages;
    }

    public class Page {
        int    pageid;
        int    ns;
        String title;
        String extract;
    }

    public String aquireJsonfromWeb(String topic) throws MalformedURLException, IOException, nonAlphanumericalException{
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();;

        try {
            if (topic.matches("^.*[^a-zA-Z0-9].*$")){
                throw new nonAlphanumericalException();
            }

            String theURL = "https://en.wikipedia.org/w/api.php?action=query&generator=search&gsrsearch="+topic+"&gsrnamespace=0&gsrlimit=1&origin=*&prop=extracts&exchars=1200&exlimit=max&explaintext=1&exintro=1&format=json";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }
        finally {
            if (br != null) {
                br.close();
            }
        }
            return sb.toString();
    }

    public String parseWikiJson(String jsonfile){
        if (jsonfile.toString().contains("{\"batchcomplete\":\"\",\"limits\":{\"extracts\":20}}")){
            try {
                throw new nullDataException();
            } catch (nullDataException e) {
                return "sorry, I don't know";
            }
        }
        String json = jsonfile;
        String returnedExtract = null;
        Root root = new Gson().fromJson(json, Root.class);
        for (Page page : root.query.pages.values()) {
            System.out.println(page.title);
            returnedExtract = page.extract.substring(0, page.extract.indexOf(".")+1);

        }
        return returnedExtract;
    }

}
