package agh.cs.lab9;

public class Posel {

    private int id;
    private String imiePierwsze;
    private String imieDrugie;
    private String nazwisko;
    private int[] kadencja;
    private Wyjazdy[] wyjazdy;
    private Wydatki[] wydatki;
    private String noInfo;

    public float spendingsSum(){
        float sum = 0;
        for (int i = 0; i < wydatki.length; i++) {
            for (int j = 0; j < wydatki[i].pola.length; j++) {
                sum += wydatki[i].pola[j];
            }
        }
        return sum;
    }

    public float spendings13(){
        float sum = 0;
        for (int i = 0; i < wydatki.length; i++) {
            sum += wydatki[i].pola[12];
        }
        return sum;
    }

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
