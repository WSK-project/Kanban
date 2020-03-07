package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    //Neco jako LOGGER at to muzeme zobrazovat co se deje... Jenom napad :D
    private static List<String> prubehList = new ArrayList<>();

    public static String getPrubehList() {
        StringBuilder ss = new StringBuilder();
        prubehList.forEach(ss::append);
        return ss.toString();
    }

    public static void addPrubehList(String s) {
        prubehList.add(s);
    }

    /**
     * Metoda simuluje prevoz ze vzdaleneho skladu
     */
    public void prevozZeVzdalenehoSkladu(Suroviny sur) throws InterruptedException {
        Utils.addPrubehList("Byl informovan vzdaleny sklad o potrebe suroviny: " + sur.getNazev() + ".\n");
        Random rdm = new Random();
        int result = rdm.nextInt(10000);
        Thread.sleep(result + 10000);
        Utils.addPrubehList("Surovina: " + sur.getNazev() + " ze vzdaleneho skladu dodana.\n");
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
    public void vyrob() {
    }

    /**
     * Metoda kterou si vytvorime vsechno (suroviny, vyrobni linky a podobne picoviny) at si tim nezasirame
     * controller k cemu jinemu pak mame ty utilsy ze
     */
    public void letsRoll() {

    }
}
