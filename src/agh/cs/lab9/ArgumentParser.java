package agh.cs.lab9;

import java.util.List;

public class ArgumentParser {

    String[] args;
    Kadencja kadencja;

    public ArgumentParser(String[] args) {
        this.args = args;
    }

    public void parseArgs() {
        if (args.length == 0) {
            System.out.print("\nNie podano żadnych argumentów.\n" +
                    "Dla wyświetlenia informacji o konkretnym pośle, użyj -p,\n" +
                    "potem dla wyświetlenia informacji o sumie jego wydatków -w,\n" +
                    "lub -n dla wyświetlenia informacji o wydatkach na drobne naprawy\n" +
                    "i remonty biura poselskiego;\n" +
                    "potem podaj osobno imie i nazwisko interesującego ci posła.\n" +
                    "Dla wyświetlenia informacji o średniej wartości sumy wydatków\n" +
                    "wszystkich posłów użyj polecenia -średnia." +
                    "Dla wyświetlenia informacji o pośle/poślance, ktory/która:\n " +
                    "- wykonał najwięcej podróży zagranicznych: -a;\n" +
                    "- najdłużej przebywał za granicą: -b;\n" +
                    "- odbył najdroższą podróż zagraniczną: -c.\n" +
                    "Żeby otrzymać listę wszystkich posłów, którzy odwiedzili Włochy\n" +
                    "użyj polecenia -wlochy. Na końcu podaj numer kadencji.\n");
        } else if (args.length == 1) {
            System.out.println("Za mało argumentów. Podaj co najmniej 2 argumenty.");
        } else if (args.length > 5) {
            System.out.println("Za dużo argumentów. Podaj nie więcej niż 5 argumentów.");
        }
        else parseKad();
    }

    public void parseKad() {
        switch (args[1]) {
            case "7":
                parseFirstArg();
                break;
            case "8":
                parseFirstArg();
                break;
            default:
                System.out.print("Podano niepoprawny numer kadencji.\n" +
                        "Dostępne kadencję: 7 lub 8.\n");
        }
    }

    public void parseFirstArg() {
        switch (args[0]) {
            case "-p": parseP();
                break;
            case "-srednia": parseAv();
                break;
            case "-a": parseAv();
                break;
            case "-b": parseAv();
                break;
            case "-c": parseAv();
                break;
            case "-wlochy": parseAv();
                break;
            default: System.out.print("\nNiepoprawny argument.\n" +
                    "Dla wyświetlenia informacji o konkretnym pośle, użyj -p,\n" +
                    "potem dla wyświetlenia informacji o sumie jego wydatków -w,\n" +
                    "lub -n dla wyświetlenia informacji o wydatkach na drobne naprawy\n" +
                    "i remonty biura poselskiego;\n" +
                    "na końcu podaj osobno imie i nazwisko interesującego ci posła.\n" +
                    "Dla wyświetlenia informacji o średniej wartości sumy wydatków\n" +
                    "wszystkich posłów użyj polecenia -srednia." +
                    "Dla wyświetlenia informacji o pośle/poślance, ktory/która:\n " +
                    "- wykonał najwięcej podróży zagranicznych: -a;\n" +
                    "- najdłużej przebywał za granicą: -b;\n" +
                    "- odbył najdroższą podróż zagraniczną: -c.\n" +
                    "Żeby otrzymać listę wszystkich posłów, którzy odwiedzili Włochy\n" +
                    "użyj polecenia -wlochy. Na końcu podaj numer kadencji.\n");
        }
    }

    public void parseP() {
        switch (args[2]) {
            case "-w":
                showP();
                break;
            case "-n":
                showP();
                break;
            default:
                System.out.print("Podano niepoprawny argument.\n" +
                        "Dla wyświetlenia informacji o sumie jego wydatków -w,\n" +
                        "lub -n dla wyświetlenia informacji o wydatkach na drobne naprawy\n" +
                        "i remonty biura poselskiego.\n");
        }
    }

    public void showP() throws IllegalArgumentException {
        kadencja = makeKadencja(args[1]);
        if (kadencja.getPoselMap().get(args[3]+args[4]) == null) {
            System.out.println("Nie znaleziono posła o określonym imienu i nazwisku.");
        }
        kadencja.fillAdd();
        Posel posel = kadencja.getPoselMap().get(args[3]+args[4]);
        switch (args[2]) {
            case "-w":
                System.out.println("Suma wydatków: " + posel.spendingsSum());
                break;
            case "-n":
                System.out.println("Wysokość wydatków na \'drobne naprawy i remonty biura poselskiego\': " + posel.spendings13());
                break;
            default:
                throw new IllegalArgumentException("Coś poszło nie tak.");
        }
    }

    private void parseAv() throws IllegalArgumentException {
        kadencja = makeKadencja(args[1]);
        kadencja.fillAdd();
        switch (args[0]) {
            case "-srednia": System.out.println("Średnia wartość sumy wydatków wszystkich posłów: "
                    + kadencja.averageSpendings());
                break;
            case "-a":
                System.out.println("Poseł, który wykonał najwięcej podróży zagranicznych: "
                        + kadencja.mostTours());
                break;
            case "-b":
                System.out.println("Poseł, który najdłużej przebywał za granicą: "
                        + kadencja.mostTimeSpentAbroad());
                break;
            case "-c":
                System.out.println("Poseł, który odbył najdroższą podróż zagraniczną: "
                        + kadencja.mostExpensiveTour());
                break;
            case "-wlochy":
                List<String> list = kadencja.visitedIT();
                for (String name: list) {
                    System.out.println(name);
                }
                break;
            default:
                throw new IllegalArgumentException("Coś poszło nie tak.");
        }
    }

    public Kadencja makeKadencja(String numKadencja){
        Kadencja kadencja = new Kadencja();
        kadencja.fillInfo(Integer.parseInt(numKadencja));
        return kadencja;
    }

}
