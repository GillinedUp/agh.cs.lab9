package agh.cs.lab9;

import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yurii on 12/18/16.
 */
public class Kadencja {

    private Map<String, Posel> poselMap = new ConcurrentHashMap<>();
    private String urlKadencja = "https://api-v3.mojepanstwo.pl/dane/poslowie.json";
    private String nextUrl;
    private String lastUrl;
    private String poselUrl = "https://api-v3.mojepanstwo.pl/dane/poslowie/";

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

            // put first page into map
            for (int i = 0; i < page.getPosly().length; i++) {
                posel = page.getPosly()[i];
                poselMap.put((posel.getImiePierwsze() + posel.getNazwisko()), posel);
            }
            this.lastUrl = page.getLastUrl();
            this.nextUrl = page.getNextUrl();

            // put next pages into map
            while (!this.nextUrl.equals(this.lastUrl)) {
                json = urlReader.readFromUrl(nextUrl);
                page = gsonBuilder.create().fromJson(json, Page.class);
                for (int i = 0; i < page.getPosly().length; i++) {
                    posel = page.getPosly()[i];
                    poselMap.put((posel.getImiePierwsze() + posel.getNazwisko()), posel);
                }
                this.nextUrl = page.getNextUrl();
            }

            // put last page into map
            json = urlReader.readFromUrl(lastUrl);
            page = gsonBuilder.create().fromJson(json, Page.class);
            for (int i = 0; i < page.getPosly().length; i++) {
                posel = page.getPosly()[i];
                poselMap.put((posel.getImiePierwsze() + posel.getNazwisko()), posel);
            }
        } catch (Exception e){
            System.out.print(e);
        }
    }

    public void fillAdd(){
        ExecutorService executor = Executors.newFixedThreadPool(30);
        for (String key : poselMap.keySet()) {
            Runnable worker = new FillAddRunner(poselMap, key, poselUrl);
            executor.execute(worker);
        }
        executor.shutdown();
        // Wait until all threads are finish
        while (!executor.isTerminated()) {

        }
    }

    public double averageSpendings(){
        double sum = 0;
        int poselCount = 0;
        for (String key : poselMap.keySet()) {
            Posel posel = poselMap.get(key);
            sum += posel.spendingsSum();
            poselCount++;
        }
        return sum/poselCount;
    }

    public String mostTours(){
        int maxNumOfTours = 0;
        String name = "error has occurred";
        for (String key : poselMap.keySet()) {
            Posel posel = poselMap.get(key);
            if(posel.getWyjazdy() != null) {
                if(posel.getWyjazdy().length > maxNumOfTours) {
                    maxNumOfTours = posel.getWyjazdy().length;
                    name = posel.getImiePierwsze() + " " + posel.getNazwisko();
                }
            }
        }
        return name + ": " + maxNumOfTours + " wyjazdy.";
    }

    public String mostTimeSpentAbroad(){
        int maxDays = 0;
        int currentDays = 0;
        String name = "error has occurred";
        for (String key : poselMap.keySet()) {
            Posel posel = poselMap.get(key);
            if(posel.getWyjazdy() != null) {
                for (int i = 0; i < posel.getWyjazdy().length; i++) {
                    currentDays += posel.getWyjazdy()[i].getLiczba_dni();
                }
                if(currentDays > maxDays) {
                    maxDays = currentDays;
                    name = posel.getImiePierwsze() + " " + posel.getNazwisko();
                }
                currentDays = 0;
            }
        }
        return name + ": " + maxDays + " dni.";
    }

    public String mostExpensiveTour(){
        float maxSum = 0;
        float curSum = 0;
        String name = "error has occurred";
        for (String key : poselMap.keySet()) {
            Posel posel = poselMap.get(key);
            if(posel.getWyjazdy() != null) {
                for (int i = 0; i < posel.getWyjazdy().length; i++) {
                    curSum = posel.getWyjazdy()[i].getKoszt_suma();
                    if(curSum > maxSum) {
                        maxSum = curSum;
                        name = posel.getImiePierwsze() + " " + posel.getNazwisko();
                    }
                }
            }
        }
        return name + ": " + maxSum + " złotych.";
    }

    public List<String> visitedIT(){
        List<String> poselList = new LinkedList<>();
        boolean visited = false;
        for (String key : poselMap.keySet()) {
            Posel posel = poselMap.get(key);
            if(posel.getWyjazdy() != null) {
                for (int i = 0; i < posel.getWyjazdy().length; i++) {
                    if(posel.getWyjazdy()[i].getKraj().equals("Włochy")){
                        visited = true;
                    }
                }
                if(visited) {
                    poselList.add(posel.getImiePierwsze() + " " + posel.getNazwisko());
                    visited = false;
                }
            }
        }
        return poselList;
    }

    public Map<String, Posel> getPoselMap() {
        return poselMap;
    }

}
