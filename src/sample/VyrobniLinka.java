package sample;

public class VyrobniLinka {
    private String nazev;
    private volatile boolean dostupny;

    public VyrobniLinka(String nazev, boolean dostupny) {
        this.nazev = nazev;
        this.dostupny = dostupny;
    }


    //getters and setters
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public synchronized boolean isDostupny() {
        return dostupny;
    }

    public synchronized void setDostupny (boolean dostupny) {
        this.dostupny = dostupny;
    }

    @Override
    public String toString() {
        return "VyrobniLinka{" +
                "nazev='" + nazev + '\'' +
                ", dostupny=" + dostupny +
                '}';
    }
}
