package sample;

import java.util.List;

public class Sklady {
    private String nazev;
    private TypSkladu typSkladu;
    private int casVydaniSurovin;
    private List<ZaznamuSkladu> surovinyNaSklade;

    public Sklady(String nazev, TypSkladu typSkladu, int casVydaniSurovin, List<ZaznamuSkladu> surovinyNaSklade) {
        this.nazev = nazev;
        this.typSkladu = typSkladu;
        this.casVydaniSurovin = casVydaniSurovin;
        this.surovinyNaSklade = surovinyNaSklade;
    }

    public ZaznamuSkladu vratZaznamSkladu(Suroviny sur) {
        return surovinyNaSklade.stream()
                .filter(dd -> dd.getSurovina().getNazev().equals(sur.getNazev()))
                .findFirst()
                .get();
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

    public List<ZaznamuSkladu> getSurovinyNaSklade() {
        return surovinyNaSklade;
    }

    public void setSurovinyNaSklade(List<ZaznamuSkladu> surovinyNaSklade) {
        this.surovinyNaSklade = surovinyNaSklade;
    }

    @Override
    public String toString() {
        return "Sklady{" +
                "nazev='" + nazev + '\'' +
                ", typSkladu=" + typSkladu +
                ", casVydaniSurovin=" + casVydaniSurovin +
                ", surovinyNaSklade=" + surovinyNaSklade +
                '}';
    }
}
