package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static String getPrubehList() {
        StringBuilder ss = new StringBuilder();
        prubehList.forEach(ss::append);
        return ss.toString();
    }

    public static void addPrubehList(String s) {
        prubehList.add(s);
        System.out.println(s);
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

    /**
     * Hlavni metoda ktera simuluje RUN pro vsechny vyrobky
     * bohuzel se muze zacyklit ale to by ten vyrobek musel byt hodne velky smolar aby byl vzdycky vadny :D
     * @param vvv
     * @throws InterruptedException
     */
    public static void runScenario(Vyrobky vvv) throws InterruptedException {
        Utils.addPrubehList("Vyrobek: " + vvv.getNazev() + " pridan do fronty na vyrobu. Momentalni usek: " + vvv.getMomentalniUsek());
        Thread.sleep(2000);

        //Vyrobek po vyrobku se zkontroluje zdali jsou suroviny pro vyrobu
        //Nemuzou se kontrolovat 2 vyrobky soucasne
        Utils.kontrolaMnoszstvi(vvv);

        //pote si kazdy vyrobek "najde" vyrobni linku
        Utils.kontrolaVyrLinky(vvv);

        //Po uspesnem nalezeni vyrobni linky a zkontrolovani surovin se jde na vyrobu
        Utils.zmenUsek(vvv);
        Utils.addPrubehList("Zacina vyroba vyrobku: " + vvv.getNazev() + ", bude to trvat: " + vvv.getDelkaVyroby() + "milisekund.");
        Thread.sleep(vvv.getDelkaVyroby());
        vvv.getLinka().setDostupny(true);

        //Zahaji se kontrola
        Utils.kontroloreVolimSiTebe(vvv);
        Instance.p1.setDostupny(true);
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
            Utils.addPrubehList("Zacina kontrola surovin u vyrobku: " + vvv.getNazev() + ".");
            for (Suroviny sur : potrebneSuroviny) {
                ZaznamuSkladu lokZaz = Instance.sklad_lok.vratZaznamSkladu(sur);
                ZaznamuSkladu vzdZaz = Instance.sklad_vzd.vratZaznamSkladu(sur);
                if (lokZaz.getMnozstvi() == 100) {
                    Thread.sleep(1500);
                    Utils.addPrubehList("Surovina: " + sur.getNazev() + " je na lokalnim sklade.");
                    lokZaz.setMnozstvi(0);
                } else if (vzdZaz.getMnozstvi() > 0) {
                    Utils.addPrubehList("Surovina: " + sur.getNazev() + " jiz neni na lokalnim skladu.");
                    prevozZeVzdalenehoSkladu(sur);
                }
            }
            Utils.addPrubehList("Vsechny suroviny pro vyrobek: " + vvv.getNazev() + " jsou k dispozici.");
        }
    }


    /**
     * Metoda ktera hleda volnou vyrobni linku, pokud nenajde ceka a po
     * 10s to zkusi znovu
     *
     * @param vvv
     * @throws InterruptedException
     */
    public static void kontrolaVyrLinky(Vyrobky vvv) throws InterruptedException {
        addPrubehList("Pro vyrobek: " + vvv.getNazev() + " se hleda volna vyrobni linka. V " + LocalDateTime.now());
        Random rdn = new Random();
        Thread.sleep(rdn.nextInt(5000));
        if (Instance.vl1.isDostupny()) {
            Instance.vl1.setDostupny(false);
            addPrubehList("Vyrobek: " + vvv.getNazev() + " se bude vyrabet na vyrobni lince: " + Instance.vl1.getNazev() + ".");
        } else if (Instance.vl2.isDostupny()) {
            Instance.vl2.setDostupny(false);
            addPrubehList("Vyrobek: " + vvv.getNazev() + " se bude vyrabet na vyrobni lince: " + Instance.vl2.getNazev() + ".");
        } else if (Instance.vl3.isDostupny()) {
            Instance.vl3.setDostupny(false);
            addPrubehList("Vyrobek: " + vvv.getNazev() + " se bude vyrabet na vyrobni lince: " + Instance.vl3.getNazev() + ".");
        } else {
            addPrubehList("Pro vyrobek: " + vvv.getNazev() + " neni volna zadna vyrobni linka. Zkusi se znovu za 10s.");
            Thread.sleep(10000);
            kontrolaVyrLinky(vvv);
        }
    }


    /**
     * Metoda simuluje prevoz ze vzdaleneho skladu
     */
    public static void prevozZeVzdalenehoSkladu(Suroviny sur) throws InterruptedException {
        addPrubehList("Byl informovan vzdaleny sklad o potrebe suroviny: " + sur.getNazev() + ".");
        Random rdm = new Random();
        int result = rdm.nextInt(10000);
        Thread.sleep(result + 10000);
        addPrubehList("Surovina: " + sur.getNazev() + " ze vzdaleneho skladu dodana.");
    }



    public static void kontroloreVolimSiTebe(Vyrobky vvv) throws InterruptedException {
        if (Instance.p1.isDostupny()) {
            Instance.p1.setDostupny(false);
            Utils.addPrubehList("Pracovnik: " + Instance.p1.getJmeno() + " zacina s kontrolou.");
            Thread.sleep(vvv.getCasPotrebnyNaKontrolu());
            if (vvv.jeVadny()) {
                Utils.addPrubehList("Vyrobek: " + vvv.getNazev() + " je vadny, musi se vyrobit znova.");
                vvv.setMomentalniUsek(Useky.TO_DO);
                runScenario(vvv);
            } else {
                Utils.addPrubehList("Vyrobek je v poradku.");
            }
        } else {
            Utils.addPrubehList("Pracovnik neni k dispozici. Vyrobek: " + vvv.getNazev() + " ceka na zkontrolovani.");
            Thread.sleep(10000);
            kontroloreVolimSiTebe(vvv);
        }
    }
}
