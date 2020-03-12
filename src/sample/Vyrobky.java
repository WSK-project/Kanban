package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.concurrent.Semaphore;


public class Vyrobky extends Thread {

    private String nazev;
    private int delkaVyroby;
    private int casPotrebnyNaKontrolu;
    private Useky momentalniUsek;
    private List<Suroviny> potrebneSuroviny;

    public Vyrobky(String nazev, int delkaVyroby, int casPotrebnyNaKontrolu, Useky momentalniUsek, List<Suroviny> potrebneSuroviny) {
        super(nazev);
        this.nazev = nazev;
        this.delkaVyroby = delkaVyroby;
        this.casPotrebnyNaKontrolu = casPotrebnyNaKontrolu;
        this.momentalniUsek = momentalniUsek;
        this.potrebneSuroviny = potrebneSuroviny;
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
     * Metoda pro simulaci vyroby
     * 1, prevede vsechny vyrobku do stavu/useku TO_DO
     * 2, zapta na mnozstvi na skladech, tzn. ziska si potrebne mnozstvi surovin
     * prvni zkusi lokalni sklad pokud neni koukne do vzdaleneho
     * 3, pokud ziska suroviny zapocne vyrobu podle casu u vyrobku a prevede ho do stavu INPROGRESS
     * 4, nasimuluje kontrolora a checkne jestli je vyrobek ok pokud ne jde znovu do skladu
     * pokud v pohode preda ho do stavu/useku DONE
     * 5, vsechno se zapisuje do listu v utils ktery do dava do textfieuldu aby bylo videl co se deje
     */
    @Override
    public void run() {
        Semaphore s = new Semaphore(2);
        try {
            Utils.zmenUsek(this);
            Utils.addPrubehList("Vyrobek: " + this.getNazev() + " pridan do fronty na vyrobu. Momentalni usek: " + this.getMomentalniUsek());
            Thread.sleep(2000);

            Utils.kontrolaVyrLinky(this);

            Utils.addPrubehList("Zacina vyroba vyrobku: " + this.getNazev() + ", bude to trvat: " + this.getDelkaVyroby() + "milisekund.");
            Thread.sleep(this.getDelkaVyroby());

            Utils.addPrubehList("Vyrobeno: " + this.getNazev());
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



    public List<Suroviny> getSurVyrobkyByNazev(final String nazevvyr) {
        return getPotrebneSuroviny().stream()
                .filter(Vyrobky -> nazevvyr.equals(Vyrobky.getNazev()))
                .collect(Collectors.toList());
    }

    public String textForPane() {
        StringBuilder ss = new StringBuilder();
        ss.append(this.getNazev()).append(" -> ");
        potrebneSuroviny.forEach(surovina -> ss.append(surovina.getNazev()).append(", "));
        return ss.toString();
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
