package agh.cs.lab9;

public class Main {

    public static void main(String[] args) {
        try {
            ArgumentParser parser = new ArgumentParser(args);
            parser.parseArgs();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
