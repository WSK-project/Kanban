package sample.classes;

import com.sun.deploy.net.MessageHeader;

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

    /*
    Metoda simuluje prevoz ze vzdaleneho skladu
     */
    public void prevozZeVzdalenehoSkladu(Suroviny sur) throws InterruptedException {
        Utils.addPrubehList("Byl informovan vzdaleny sklad o potrebe suroviny: " + sur.getNazev() + ".\n");
        Random rdm = new Random();
        int result = rdm.nextInt(10000);
        Thread.sleep(result + 10000);
        Utils.addPrubehList("Surovina: " + sur.getNazev() + " ze vzdaleneho skladu dodana.\n");
    }
}
