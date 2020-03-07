package sample;

import java.util.Set;

public class Sklady {
    private String nazev;
    private TypSkladu typSkladu;
    private int casVydaniSurovin;
    private Set<Suroviny> surovinyNaSklade;

    public Sklady(String nazev, TypSkladu typSkladu, int casVydaniSurovin, Set<Suroviny> surovinyNaSklade) {
        this.nazev = nazev;
        this.typSkladu = typSkladu;
        this.casVydaniSurovin = casVydaniSurovin;
        this.surovinyNaSklade = surovinyNaSklade;
    }




    //getters and setters
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public TypSkladu getTypSkladu() {
        return typSkladu;
    }

    public void setTypSkladu(TypSkladu typSkladu) {
        this.typSkladu = typSkladu;
    }

    public int getCasVydaniSurovin() {
        return casVydaniSurovin;
    }

    public void setCasVydaniSurovin(int casVydaniSurovin) {
        this.casVydaniSurovin = casVydaniSurovin;
    }

    public Set<Suroviny> getSurovinyNaSklade() {
        return surovinyNaSklade;
    }

    public void setSurovinyNaSklade(Set<Suroviny> surovinyNaSklade) {
        this.surovinyNaSklade = surovinyNaSklade;
    }
}
