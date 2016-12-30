package agh.cs.lab9;

public class Page {

    private Posel[] posly;
    private String nextUrl;
    private String lastUrl;

    public Page(Posel[] posly) {
        this.posly = posly;
    }

    public Posel[] getPosly() {
        return posly;
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
