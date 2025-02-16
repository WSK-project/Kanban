package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.concurrent.Semaphore;


public class Vyrobky extends Thread {

    private String nazev;
    private int delkaVyroby;
    private int casPotrebnyNaKontrolu;
    private Useky momentalniUsek;
    private List<Suroviny> potrebneSuroviny;

    public Vyrobky(String nazev,
                   int delkaVyroby,
                   int casPotrebnyNaKontrolu,
                   Useky momentalniUsek,
                   List<Suroviny> potrebneSuroviny) {
        super(nazev);
        this.nazev = nazev;
        this.delkaVyroby = delkaVyroby;
        this.casPotrebnyNaKontrolu = casPotrebnyNaKontrolu;
        this.momentalniUsek = momentalniUsek;
        this.potrebneSuroviny = potrebneSuroviny;
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
        try {
            //Zacatek kdy se vyrobky prehodi do stavy TO_DO
            Utils.zmenUsek(this);

            //Je to v utils aby se to dalo volat opakovane ten proces
            Utils.runScenario(this);

            //zmena do stavu DONE
            Utils.zmenUsek(this);

            //posledni zprava ze je vyrobek pripraven k preprave k zakaznikovi
            Controller.addPrubehList("############### Vyrobeno: " + this.getNazev() + ".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //getters and setters
    public String getNazev() {
        return nazev;
    }

    public int getDelkaVyroby() {
        return delkaVyroby;
    }

    public int getCasPotrebnyNaKontrolu() {
        return casPotrebnyNaKontrolu;
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
