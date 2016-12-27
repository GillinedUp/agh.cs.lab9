package agh.cs.lab9;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yurii on 27.12.16.
 */
public class Page {

    private String urlKadencja = "https://api-v3.mojepanstwo.pl/dane/poslowie.json";
    private String nextUrl;
    private String lastUrl;
    private Map<Posel, Posel> poselMap = new LinkedHashMap<Posel, Posel>();
    private Posel[] posly;



    // make url string dependent on chosen "kadencja"
    public Page(int kadencja){
        this.urlKadencja += "conditions[poslowie.kadencja]=" + kadencja;
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

    public Map<Posel, Posel> getPoselMap() {
        return poselMap;
    }

    public void setPoselMap(Map<Posel, Posel> poselMap) {
        this.poselMap = poselMap;
    }

    public Posel[] getPosly() {
        return posly;
    }

    public void setPosly(Posel[] posly) {
        this.posly = posly;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }
}
