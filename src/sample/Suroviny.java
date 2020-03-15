package sample;

public class Suroviny {
    private String nazev;

    public Suroviny(String nazev) {
        this.nazev = nazev;
    }

    //getter
    public String getNazev() {
        return nazev;
    }

    @Override
    public String toString() {
        return "Suroviny{" +
                "nazev='" + nazev + '\'' +
                '}';
    }
}
