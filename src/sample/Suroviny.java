package sample;

public class Suroviny {
    private String nazev;

    public Suroviny(String nazev) {
        this.nazev = nazev;
    }

    //getters and setters
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return "Suroviny{" +
                "nazev='" + nazev + '\'' +
                '}';
    }
}
