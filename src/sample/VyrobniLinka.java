package sample;

public class VyrobniLinka {
    private String nazev;
    private boolean dostupny;

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

    public boolean isDostupny() {
        return dostupny;
    }

    public void setDostupny(boolean dostupny) {
        this.dostupny = dostupny;
    }
}
