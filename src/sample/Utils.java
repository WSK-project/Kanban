package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Utils {
    private static String lockMn = "lockMnozstvi";

    /**
     * neni zadane ze se musi odebirat nejak zvlastne,
     * takze kazdy vyrobek bude mit spotrebu dane suroviny ve vysi 100
     * cimz docilime i toho ze se bude volat vzdaleny sklad
     */
    public static final int KONSTANTA_PRO_ODEBIRANI_MNOZSTVI = 100;

    /**
     * Neco jako LOGGER at to muzeme zobrazovat na pejne co se deje... Jenom napad :D
     */
    public static List<String> prubehList = new ArrayList<>();

    public String getPrubehList() {
        StringBuilder ss = new StringBuilder();
        prubehList.forEach(ss::append);
        return ss.toString();
    }

    public static void addPrubehList(String s) {
        prubehList.add(s);
        System.out.println(s);
    }

    /**
     * Metoda ktera mi vygeneruje nahodne cislo ze zadaneho intervalu
     */
    public static int getSchwiftyBejbe(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1000) + min;
    }

    /**
     * Metoda nahodne vygeneruju jestli je vyrobek vadny nebo ne
     * Existuje 5% sance ze je vadny
     */
    public static boolean jeVadny() {
        Random rdm = new Random();
        int result = rdm.nextInt(100);
        return result > 95;
    }

    /**
     * Metoda pro zmenu useky ve kterem se vyrobek nachazi
     *
     * @param vyrobek
     */
    public static void zmenUsek(Vyrobky vyrobek) {
        if (vyrobek.getMomentalniUsek() == Useky.BACKLOG) {
            vyrobek.setMomentalniUsek(Useky.TO_DO);
        } else if (vyrobek.getMomentalniUsek() == Useky.TO_DO) {
            vyrobek.setMomentalniUsek(Useky.IN_PROGRESS);
        } else if (vyrobek.getMomentalniUsek() == Useky.IN_PROGRESS) {
            vyrobek.setMomentalniUsek(Useky.DONE);
        }
    }




    
    //vyrobni proces
    /**
     * Hlavni metoda ktera simuluje RUN pro vsechny vyrobky
     * bohuzel se muze zacyklit ale to by ten vyrobek musel byt hodne velky smolar aby byl vzdycky vadny :D
     */
    public static void runScenario(Vyrobky vvv) throws InterruptedException {
        Utils.addPrubehList("Výrobek: " + vvv.getNazev() + " přidán do fronty na výrobu. Momentální úsek: " + vvv.getMomentalniUsek() + ".");
        Thread.sleep(2000);


        //Vyrobek po vyrobku se zkontroluje zdali jsou suroviny pro vyrobu
        //Nemuzou se kontrolovat 2 vyrobky soucasne
        Utils.kontrolaMnoszstvi(vvv);


        //pote si kazdy vyrobek "najde" vyrobni linku
        Utils.kontrolaVyrLinky(vvv);

        //Po uspesnem nalezeni vyrobni linky a zkontrolovani surovin se jde na vyrobu
        Utils.zmenUsek(vvv);
        Utils.addPrubehList("Začíná výroba výrobku: " + vvv.getNazev() + ", bude to trvat: " + vvv.getDelkaVyroby() + " milisekund.");
        Thread.sleep(vvv.getDelkaVyroby());
        Instance.vyrobniLinky.release();

        //Posledni krok a to je kontrola
        if (Utils.kontroloreVolimSiTebe(vvv)) {
            Utils.addPrubehList("Výrobek: " + vvv.getNazev() + " je vadný, musí se vyrobit znovu.");
            vvv.setMomentalniUsek(Useky.TO_DO);
            Instance.pracovnik.release();
            runScenario(vvv);
        } else {
            Utils.addPrubehList("Výrobek " + vvv.getNazev() + " je v pořádku.");
            Instance.pracovnik.release();
        }
    }


    /**
     * Metoda pro kontrolu mnoztsvi na sklade jednotlivych surovin
     * Kazda surovina potrebuje 100 jednotek mnostvi (v zadani neni nic o tom
     * ze by to mela mit kazdy vyrobek jinak tak si to aspon trosku
     * ulehcime)
     */
    public static void kontrolaMnoszstvi(Vyrobky vvv) throws InterruptedException {
        List<Suroviny> potrebneSuroviny = vvv.getPotrebneSuroviny();
        synchronized (lockMn) {
            Utils.addPrubehList("Začíná kontrola surovin u výrobku: " + vvv.getNazev() + ".");
            for (Suroviny sur : potrebneSuroviny) {
                ZaznamuSkladu lokZaz = Instance.sklad_lok.vratZaznamSkladu(sur);
                ZaznamuSkladu vzdZaz = Instance.sklad_vzd.vratZaznamSkladu(sur);
                if (lokZaz.getMnozstvi() == 100) {
                    Utils.addPrubehList("Surovina: " + sur.getNazev() + " je na lokálním skladě.");
                    lokZaz.setMnozstvi(0);
                } else if (vzdZaz.getMnozstvi() > 0) {
                    Utils.addPrubehList("Surovina: " + sur.getNazev() + " již není na lokálním skladě.");
                    prevozZeVzdalenehoSkladu(sur);
                    vzdZaz.setMnozstvi(vzdZaz.getMnozstvi() - KONSTANTA_PRO_ODEBIRANI_MNOZSTVI);
                }
            }
            Utils.addPrubehList("Všechny suroviny pro výrobek: " + vvv.getNazev() + " jsou k dispozici.");
        }
    }

    /**
     * Metoda simuluje prevoz ze vzdaleneho skladu
     */
    public static void prevozZeVzdalenehoSkladu(Suroviny sur) throws InterruptedException {
        addPrubehList("Byl informován vzdálený sklad o potřebě suroviny: " + sur.getNazev() + ".");
        Thread.sleep(getSchwiftyBejbe(5000, 10000));
        addPrubehList("Surovina: " + sur.getNazev() + " ze vzdáleného skladu dodána.");
    }

    /**
     * Metoda ktera hleda volnou vyrobni linku, je to pomoci semaforu, ktery ma
     * 3 licence, a pokud jiz nema a jsou vsechny linky obsazene tak vlakno ceka
     */
    public static void kontrolaVyrLinky(Vyrobky vvv) throws InterruptedException {
        addPrubehList("Pro výrobek: " + vvv.getNazev() + " se hledá volná výrobní linka. V " + LocalDateTime.now() + ".");
        Random rdn = new Random();
        Thread.sleep(rdn.nextInt(5000));
        Instance.vyrobniLinky.acquire();
        addPrubehList("Pro tento výrobek " + vvv.getNazev() + "je volná výrobní linka.");
        addPrubehList("Volne licence " + Instance.vyrobniLinky.availablePermits());
        addPrubehList("info " + Instance.vyrobniLinky.toString());
    }


    /**
     * Metoda simuluje kontrolu vyrobku, je tam zahrnuto i prechod Dejva mezi vyrobnima linkama
     */
    public static boolean kontroloreVolimSiTebe(Vyrobky vvv) throws InterruptedException {
        Instance.pracovnik.acquire();

        Utils.addPrubehList("Dejv se přemisťuje k výrobní lince.");
        Thread.sleep(5000);
        Utils.addPrubehList("Dejv stojí u výrobní linky a začíná kontrolu výrobku " + vvv.getNazev() + ".");

        Thread.sleep(vvv.getCasPotrebnyNaKontrolu());

        return jeVadny();
    }
}

