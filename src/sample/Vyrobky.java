package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class Vyrobky extends Thread {

    private String nazev;
    private int delkaVyroby;
    private int casPotrebnyNaKontrolu;
    private Useky momentalniUsek;
    private List<Suroviny> potrebneSuroviny;

    public Vyrobky(String nazev, int delkaVyroby, int casPotrebnyNaKontrolu, Useky momentalniUsek, Set<Suroviny> potrebneSuroviny) {
        super(nazev);
        this.nazev = nazev;
        this.delkaVyroby = delkaVyroby;
        this.casPotrebnyNaKontrolu = casPotrebnyNaKontrolu;
        this.momentalniUsek = momentalniUsek;
        this.potrebneSuroviny = (List<Suroviny>) potrebneSuroviny;
    }

    /**
     * Metoda nahodne vygeneruju jestli je vyrobek vadny nebo ne
     * Existuje 5% sance ze je vadny
     */
    public boolean jeVadny() {
        Random rdm = new Random();
        int result = rdm.nextInt(100);
        return result > 95;
    }

    /**
     * Metoda pro zmenu useky ve kterem se vyrobek nachazi
     *
     * @param vyrobek
     */
    public void zmenUsek(Vyrobky vyrobek) {
        if (vyrobek.getMomentalniUsek() == Useky.BACKLOG) {
            vyrobek.setMomentalniUsek(Useky.TO_DO);
        } else if (vyrobek.getMomentalniUsek() == Useky.TO_DO) {
            vyrobek.setMomentalniUsek(Useky.IN_PROGRESS);
        } else if (vyrobek.getMomentalniUsek() == Useky.IN_PROGRESS) {
            vyrobek.setMomentalniUsek(Useky.DONE);
        }
    }

    @Override
    public void run() {
        try {
            Utils.vyrob(Vyrobky.this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public List<Suroviny> getPotrebneSuroviny() {
        return potrebneSuroviny;
    }

    public void setPotrebneSuroviny(List<Suroviny> potrebneSuroviny) {
        this.potrebneSuroviny = potrebneSuroviny;
    }

    public List<Suroviny> getSurVyrobkyByNazev(final String nazevvyr){
        return getPotrebneSuroviny().stream()
                .filter(Vyrobky -> nazevvyr.equals(Vyrobky.getNazev()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Vyrobky{" +
                "nazev='" + nazev + '\'' +
                ", delkaVyroby=" + delkaVyroby +
                ", casPotrebnyNaKontrolu=" + casPotrebnyNaKontrolu +
                ", momentalniUsek=" + momentalniUsek +
                ", potrebneSuroviny=" + potrebneSuroviny +
                '}';
    }
}
