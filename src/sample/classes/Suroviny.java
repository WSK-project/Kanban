package sample.classes;

public class Suroviny {
    private String nazev;
    private int mnozstvi;
    private boolean dostupny;

    public Suroviny(String nazev, int mnozstvi, boolean dostupny) {
        this.nazev = nazev;
        this.mnozstvi = mnozstvi;
        this.dostupny = dostupny;
    }


    //getters and setters
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public boolean isDostupny() {
        return dostupny;
    }

    public void setDostupny(boolean dostupny) {
        this.dostupny = dostupny;
    }
}
