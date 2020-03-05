package sample.classes;

import java.util.Random;
import java.util.Set;

public class Vyrobky {

    private String nazev;
    private int delkaVyroby;
    private int casPotrebnyNaKontrolu;
    private Useky momentalniUsek;
    private Set<Suroviny> potrebneSuroviny;

    public Vyrobky(String nazev, int delkaVyroby, int casPotrebnyNaKontrolu, Useky momentalniUsek, Set<Suroviny> potrebneSuroviny) {
        this.nazev = nazev;
        this.delkaVyroby = delkaVyroby;
        this.casPotrebnyNaKontrolu = casPotrebnyNaKontrolu;
        this.momentalniUsek = momentalniUsek;
        this.potrebneSuroviny = potrebneSuroviny;
    }

    /*
    Metoda nahodne vygeneruju jestli je vyrobek vadny nebo ne
    Existuje 5% sance ze je vadny
    */
    public boolean jeVadny() {
        Random rdm = new Random();
        int result = rdm.nextInt(100);
        return result > 95;
    }



    //getters and setters
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getDelkaVyroby() {
        return delkaVyroby;
    }

    public void setDelkaVyroby(int delkaVyroby) {
        this.delkaVyroby = delkaVyroby;
    }

    public int getCasPotrebnyNaKontrolu() {
        return casPotrebnyNaKontrolu;
    }

    public void setCasPotrebnyNaKontrolu(int casPotrebnyNaKontrolu) {
        this.casPotrebnyNaKontrolu = casPotrebnyNaKontrolu;
    }

    public Useky getMomentalniUsek() {
        return momentalniUsek;
    }

    public void setMomentalniUsek(Useky momentalniUsek) {
        this.momentalniUsek = momentalniUsek;
    }

    public Set<Suroviny> getPotrebneSuroviny() {
        return potrebneSuroviny;
    }

    public void setPotrebneSuroviny(Set<Suroviny> potrebneSuroviny) {
        this.potrebneSuroviny = potrebneSuroviny;
    }
}
