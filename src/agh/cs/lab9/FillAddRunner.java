package agh.cs.lab9;

import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * Created by yurii on 29.12.16.
 */
public class FillAddRunner implements Runnable {

    private Map<String, Posel> poselMap;
    private String key;
    private String poselUrl;

    public FillAddRunner(Map<String, Posel> map, String key, String poselUrl) {
        this.poselMap = map;
        this.key = key;
        this.poselUrl = poselUrl;
    }

    @Override
    public void run(){
        try {
            String url = this.poselUrl + poselMap.get(key).getId() + ".json?layers[]=wyjazdy&layers[]=wydatki";
            UrlReader urlReader = new UrlReader();
            String json = urlReader.readFromUrl(url);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Posel.class, new AddPoselDeserializer());
            Posel posel = gsonBuilder.create().fromJson(json, Posel.class);
            poselMap.get(key).setWyjazdy(posel.getWyjazdy());
            poselMap.get(key).setWydatki(posel.getWydatki());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

}
