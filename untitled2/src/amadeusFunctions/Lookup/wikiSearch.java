package amadeusFunctions.Lookup;

import amadeusFunctions.Exceptions.nonAlphanumericalException;
import amadeusFunctions.function;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class wikiSearch extends function {

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

    public String aquireJsonfromWeb(String topic) throws MalformedURLException, IOException, nonAlphanumericalException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();;

        try {
            if (topic.matches("^.*[^a-zA-Z0-9].*$")){
                throw new nonAlphanumericalException();
            }

            String theURL = "https://en.wikipedia.org/w/api.php?action=query&generator=search&gsrsearch="+topic+"&gsrnamespace=0&gsrlimit=1&origin=*&prop=extracts&exchars=5000&exlimit=max&explaintext=1&exintro=1&format=json";
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

    public void parseWikiJson(String jsonfile){
        String json = jsonfile;
        Root root = new Gson().fromJson(json, Root.class);
        for (Page page : root.query.pages.values()) {
            System.out.println(page.title);
            System.out.println("  " + page.extract);
        }
    }

}
