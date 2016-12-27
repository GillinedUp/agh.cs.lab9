package agh.cs.lab9;

/**
 * Created by yurii on 27.12.16.
 */
public class Page {

    private Posel[] posly;
    private String nextUrl;
    private String lastUrl;

    public Page(Posel[] posly) {
        this.posly = posly;
    }

    public void setPosly(Posel[] posly) {
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
