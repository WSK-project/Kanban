package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
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
     * Metoda ktera hleda volnou vyrobni linku, pokud nenajde ceka a po
     * 10s to zkusi znovu
     * @param vvv
     * @throws InterruptedException
     */
    public static void kontrolaVyrLinky(Vyrobky vvv) throws InterruptedException {
        addPrubehList("Pro vyrobek: " + vvv.getNazev() + " se hleda volna vyrobni linka. V " + LocalDateTime.now());

        Random rdm = new Random();
        Thread.sleep(rdm.nextInt(7000));

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
        addPrubehList("Byl informovan vzdaleny sklad o potrebe suroviny: " + sur.getNazev() + ".\n");
        Random rdm = new Random();
        int result = rdm.nextInt(10000);
        Thread.sleep(result + 10000);
        addPrubehList("Surovina: " + sur.getNazev() + " ze vzdaleneho skladu dodana.\n");
    }
}
