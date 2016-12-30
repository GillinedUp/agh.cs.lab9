package agh.cs.lab9;

import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

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
        for (String key : kadencja.getPoselMap().keySet()) {
            assertEquals(key, (kadencja.getPoselMap().get(key).getImiePierwsze()
                    + kadencja.getPoselMap().get(key).getNazwisko()));
        }
    }

    @Test
    public void AddPoselDeserializerTest(){
        try {
            UrlReader urlReader = new UrlReader();
            String json = urlReader.readFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie/23.json?layers[]=wyjazdy&layers[]=wydatki");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Posel.class, new AddPoselDeserializer());
            Posel posel = gsonBuilder.create().fromJson(json, Posel.class);
            Wyjazdy[] wyjazdy = posel.getWyjazdy();
            if (wyjazdy != null) {
                for (int i = 0; i < wyjazdy.length; i++) {
                    System.out.println("kraj: " + wyjazdy[i].getKraj());
                    System.out.println("liczba dni: " + wyjazdy[i].getLiczba_dni());
                    System.out.println("koszt: " + wyjazdy[i].getKoszt_suma());
                }
            } else {
                System.out.println(posel.getNoInfo());
            }
        } catch (Exception e){
            System.out.print(e);
        }
    }

    @Test
    public void kadencjaTest2(){
        Kadencja kadencja = new Kadencja();
        kadencja.fillInfo(7);
        kadencja.fillAdd();
        Map<String, Posel> map = kadencja.getPoselMap();
        Posel posel = map.get("MagdalenaKochan");
        assertEquals(posel.getImiePierwsze(), posel.getImiePierwsze());
        assertEquals(posel.getNazwisko(), posel.getNazwisko());
        assertEquals(posel.getWyjazdy()[0].getKraj(), "Cypr");
        assertEquals(Integer.toString(posel.getWydatki()[0].getDokument_id()), "451981");
    }

    @Test
    public void kadencjaHashTest(){
        Kadencja kadencja = new Kadencja();
        kadencja.fillInfo(7);
        Map<String, Posel> map = kadencja.getPoselMap();
        Posel posel = map.get("MagdalenaKochan");
        assertEquals(posel.getImiePierwsze(), "Magdalena");
        assertEquals(posel.getNazwisko(), "Kochan");
    }

    @Test
    public void serviceTest(){
        Kadencja kadencja = new Kadencja();
        kadencja.fillInfo(7);
        kadencja.fillAdd();
        System.out.println("Średnia wartość sumy wydatków wszystkich posłów: "
                + kadencja.averageSpendings());
        System.out.println("Poseł, który wykonał najwięcej podróży zagranicznych: "
                + kadencja.mostTours());
        System.out.println("Poseł, który najdłużej przebywał za granicą: "
                + kadencja.mostTimeSpentAbroad());
        System.out.println("Poseł, który odbył najdroższą podróż zagraniczną: "
                + kadencja.mostExpensiveTour());
        System.out.println("Posły, którzy odwiedzili Włochy: ");
        List<String> list = kadencja.visitedIT();
        for (String name: list) {
            System.out.println(name);
        }
    }

    @Test
    public void kadencjaTadeuszTest(){
        Kadencja kadencja = new Kadencja();
        kadencja.fillInfo(7);
        Map<String, Posel> map = kadencja.getPoselMap();
        Posel posel = map.get("TadeuszIwiński");
        System.out.println(posel.getId());
    }

    @Test
    public void kadencjaAdamTest(){
        Kadencja kadencja = new Kadencja();
        kadencja.fillInfo(7);
        Map<String, Posel> map = kadencja.getPoselMap();
        Posel posel = map.get("AdamSzejnfeld");
        System.out.println(posel.getId());
    }

}
