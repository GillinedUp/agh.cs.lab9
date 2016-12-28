package agh.cs.lab9;

import com.google.gson.GsonBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yurii on 12/18/16.
 */
public class Kadencja {

    private Map<Posel, Posel> poselMap = new LinkedHashMap<>();
    private String urlKadencja = "https://api-v3.mojepanstwo.pl/dane/poslowie.json";
    private String nextUrl;
    private String lastUrl;


    public void fillInfo (int kadencja) {
        this.urlKadencja += "?conditions[poslowie.kadencja]=" + kadencja;
        try {

            // initialize first page to get next and last url's
            UrlReader urlReader = new UrlReader();
            String json = urlReader.readFromUrl(urlKadencja);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Posel.class, new PoselDeserializer());
            gsonBuilder.registerTypeAdapter(Page.class, new PageDeserializer());
            Page page = gsonBuilder.create().fromJson(json, Page.class);

            Posel posel;
            int debugCount = 0;

            // put first page into map
            for (int i = 0; i < page.getPosly().length; i++) {
                posel = page.getPosly()[i];
                poselMap.put(posel, posel);
            }

            this.lastUrl = page.getLastUrl();
            this.nextUrl = page.getNextUrl();

            // put next pages into map
            while (!this.nextUrl.equals(this.lastUrl)) {
                json = urlReader.readFromUrl(nextUrl);
                page = gsonBuilder.create().fromJson(json, Page.class);

                for (int i = 0; i < page.getPosly().length; i++) {
                    posel = page.getPosly()[i];
                    poselMap.put(posel, posel);
                }

                this.nextUrl = page.getNextUrl();

                debugCount++;
            }

            // put last page into map
            json = urlReader.readFromUrl(lastUrl);
            page = gsonBuilder.create().fromJson(json, Page.class);

            for (int i = 0; i < page.getPosly().length; i++) {
                posel = page.getPosly()[i];
                poselMap.put(posel, posel);
            }

        } catch (Exception e){
            System.out.print(e);
        }
    }

    public void fillAdd(){

    }


    public Map<Posel, Posel> getPoselMap() {
        return poselMap;
    }

    public void setPoselMap(Map<Posel, Posel> poselMap) {
        this.poselMap = poselMap;
    }

    public String getUrlKadencja() {
        return urlKadencja;
    }

    public void setUrlKadencja(String urlKadencja) {
        this.urlKadencja = urlKadencja;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

}
