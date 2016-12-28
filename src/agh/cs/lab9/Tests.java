package agh.cs.lab9;


import com.google.gson.GsonBuilder;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yurii on 12/18/16.
 */
public class Tests {

    @Test
    public void readerTest(){
        try {
            UrlReader urlReader = new UrlReader();
            String json = urlReader.readFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie/22.json");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Posel.class, new PoselDeserializer());
            Posel posel = gsonBuilder.create().fromJson(json, Posel.class);
            assertEquals(posel.getImiePierwsze(), "Piotr");
            assertEquals(posel.getImieDrugie(), "Paweł");
            assertEquals(posel.getNazwisko(), "Bauć");
        } catch (Exception e){
            System.out.print(e);
        }
    }

    @Test
    public void pageDeserializerTest(){
        try {
            UrlReader urlReader = new UrlReader();
            String json = urlReader.readFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Posel.class, new PoselDeserializer());
            gsonBuilder.registerTypeAdapter(Page.class, new PageDeserializer());
            Page page = gsonBuilder.create().fromJson(json, Page.class);
            for (int i = 0; i < page.getPosly().length; i++) {
                System.out.println(page.getPosly()[i].getImiePierwsze() + " " +
                        page.getPosly()[i].getNazwisko());
            }
            System.out.println(page.getNextUrl());
        } catch (Exception e){
            System.out.print(e);
        }
    }

    @Test
    public void kadencjaTest(){
        Kadencja kadencja = new Kadencja();
        kadencja.fillInfo(7);
        for (Posel key : kadencja.getPoselMap().keySet()) {
            assertEquals(key, kadencja.getPoselMap().get(key));
        }
    }


    @Test
    public void AddPoselDeserializerTest(){
        try {
            UrlReader urlReader = new UrlReader();
            String json = urlReader.readFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie/22.json?layers[]=wyjazdy&layers[]=wydatki");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Posel.class, new AddPoselDeserializer());
            //gsonBuilder.registerTypeAdapter(Wydatki.class, new WydatkiDeserializer());
            Posel posel = gsonBuilder.create().fromJson(json, Posel.class);
            Wyjazdy[] wyjazdy = posel.getWyjazdy();
            for (int i = 0; i < wyjazdy.length; i++) {
                System.out.println("kraj: " + wyjazdy[i].getKraj());
                System.out.println("liczba dni: " + wyjazdy[i].getLiczba_dni());
                System.out.println("koszt: " + wyjazdy[i].getKoszt_suma());
            }
        } catch (Exception e){
            System.out.print(e);
        }
    }
}
