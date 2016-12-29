package agh.cs.lab9;

/**
 * Created by yurii on 12/18/16.
 */
public class Posel {

    private int id;
    private String imiePierwsze;
    private String imieDrugie;
    private String nazwisko;
    private int[] kadencja;
    private Wyjazdy[] wyjazdy;
    private Wydatki[] wydatki;
    private String noInfo;

    @Override
    public int hashCode() {
        return (imiePierwsze + nazwisko).hashCode();
    }

    public String getNoInfo() {
        return noInfo;
    }

    public void setNoInfo(String noInfo) {
        this.noInfo = noInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wyjazdy[] getWyjazdy() {
        return wyjazdy;
    }

    public void setWyjazdy(Wyjazdy[] wyjazdy) {
        this.wyjazdy = wyjazdy;
    }

    public int[] getKadencja() {
        return kadencja;
    }

    public void setKadencja(int[] kadencja) {
        this.kadencja = kadencja;
    }

    public Wydatki[] getWydatki() {
        return wydatki;
    }

    public void setWydatki(Wydatki[] wydatki) {
        this.wydatki = wydatki;
    }

    public String getImiePierwsze(){
        return this.imiePierwsze;
    }

    public String getImieDrugie(){
        return this.imieDrugie;
    }

    public String getNazwisko(){
        return this.nazwisko;
    }

    public void setImiePierwsze(String imiePierwsze) {
        this.imiePierwsze = imiePierwsze;
    }

    public void setImieDrugie(String imieDrugie) {
        this.imieDrugie = imieDrugie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
