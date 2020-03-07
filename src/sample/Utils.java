package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
    //Neco jako LOGGER at to muzeme zobrazovat na pejne co se deje... Jenom napad :D
    private static List<String> prubehList = new ArrayList<>();

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
     * Metoda simuluje prevoz ze vzdaleneho skladu
     */
    public static void prevozZeVzdalenehoSkladu(Suroviny sur) throws InterruptedException {
        addPrubehList("Byl informovan vzdaleny sklad o potrebe suroviny: " + sur.getNazev() + ".\n");
        Random rdm = new Random();
        int result = rdm.nextInt(10000);
        Thread.sleep(result + 10000);
        addPrubehList("Surovina: " + sur.getNazev() + " ze vzdaleneho skladu dodana.\n");
    }

    public static Suroviny s_1, s_2, s_3, s_4, s_5, s_6,  s_7, s_8, s_9, s_10;
    public static ZaznamuSkladu zs_l_1, zs_l_2, zs_l_3, zs_l_4, zs_l_5, zs_l_6, zs_l_7, zs_l_8, zs_l_9, zs_l_10;
    public static ZaznamuSkladu zs_v_1, zs_v_2, zs_v_3, zs_v_4, zs_v_5, zs_v_6, zs_v_7, zs_v_8, zs_v_9, zs_v_10;
    static List<ZaznamuSkladu> zaznamySkladuLok, zaznamySkladuVzd;
    public static Sklady sklad_vzd, sklad_lok;
    public static Pracovnik p1;
    public static VyrobniLinka vl1, vl2, vl3;

    public static void letsRoll(){
        //Suroviny do lokalniho skladu
        s_1 = new Suroviny("Surovina_1");
        s_2 = new Suroviny("Surovina_2");
        s_3 = new Suroviny("Surovina_3");
        s_4 = new Suroviny("Surovina_4");
        s_5 = new Suroviny("Surovina_5");
        s_6 = new Suroviny("Surovina_6");
        s_7 = new Suroviny("Surovina_7");
        s_8 = new Suroviny("Surovina_8");
        s_9 = new Suroviny("Surovina_9");
        s_10 = new Suroviny("Surovina_10");
        addPrubehList("Suroviny zadany do systemu!");

        //zaznamy pro lokalni sklad
        zs_l_1 = new ZaznamuSkladu(s_1, 100);
        zs_l_2 = new ZaznamuSkladu(s_2, 100);
        zs_l_3 = new ZaznamuSkladu(s_3, 100);
        zs_l_4 = new ZaznamuSkladu(s_4, 100);
        zs_l_5 = new ZaznamuSkladu(s_5, 100);
        zs_l_6 = new ZaznamuSkladu(s_6, 100);
        zs_l_7 = new ZaznamuSkladu(s_7, 100);
        zs_l_8 = new ZaznamuSkladu(s_8, 100);
        zs_l_9 = new ZaznamuSkladu(s_9, 100);
        zs_l_10 = new ZaznamuSkladu(s_10, 100);
        addPrubehList("Vsechny suroviny dodany do lokalniho skladu!");

        //zaznamu pro vzdaleny sklad
        zs_v_1 = new ZaznamuSkladu(s_1, 1000);
        zs_v_2 = new ZaznamuSkladu(s_2, 1000);
        zs_v_3 = new ZaznamuSkladu(s_3, 1000);
        zs_v_4 = new ZaznamuSkladu(s_4, 1000);
        zs_v_5 = new ZaznamuSkladu(s_5, 1000);
        zs_v_6 = new ZaznamuSkladu(s_6, 1000);
        zs_v_7 = new ZaznamuSkladu(s_7, 1000);
        zs_v_8 = new ZaznamuSkladu(s_8, 1000);
        zs_v_9 = new ZaznamuSkladu(s_9, 1000);
        zs_v_10 = new ZaznamuSkladu(s_10, 1000);
        addPrubehList("Vsechny suroviny dodany do vzdaleneho skladu!");

        //sety surovin pro sklady
        zaznamySkladuLok = Arrays.asList(zs_l_1, zs_l_2, zs_l_3, zs_l_4, zs_l_5, zs_l_6, zs_l_7, zs_l_8, zs_l_9, zs_l_10);
        zaznamySkladuVzd = Arrays.asList(zs_v_1, zs_v_2, zs_v_3, zs_v_4, zs_v_5, zs_v_6, zs_v_7, zs_v_8, zs_v_9, zs_v_10);

        //Sklady
        sklad_lok = new Sklady("Lokalni sklad", TypSkladu.LOKALNI, 0, zaznamySkladuLok);
        addPrubehList("Lokalni sklad je k dispozici: " + sklad_lok.toString());
        sklad_vzd = new Sklady("Vzdaleny sklad", TypSkladu.VZDALENY, 5000, zaznamySkladuVzd);
        addPrubehList("Vzdaleny sklad je k dispozici: " + sklad_vzd.toString());

        //kontrolor
        p1 = new Pracovnik("Dejv", true);
        addPrubehList("Pracovnik prisel do prace: " + p1.toString());

        //Vyrobni linky
        vl1 = new VyrobniLinka("Prvni", true);
        addPrubehList("Vytvorena prvni linka: " + vl1.toString());
        vl2 = new VyrobniLinka("Druha", true);
        addPrubehList("Vytvorena druha linka: " + vl2.toString());
        vl3 = new VyrobniLinka("treti", true);
        addPrubehList("Vytvorena treti linka: " + vl3.toString());
    }

    /**
     *
     *
     * Metoda pro simulaci vyroby
     * 1, prevede vsechny vyrobku do stavu/useku TO_DO
     * 2, zapta na mnozstvi na skladech, tzn. ziska si potrebne mnozstvi surovin
     * prvni zkusi lokalni sklad pokud neni koukne do vzdaleneho
     * 3, pokud ziska suroviny zapocne vyrobu podle casu u vyrobku a prevede ho do stavu INPROGRESS
     * 4, nasimuluje kontrolora a checkne jestli je vyrobek ok pokud ne jde znovu do skladu
     * pokud v pohode preda ho do stavu/useku DONE
     * 5, vsechno se zapisuje do listu v utils ktery do dava do textfieuldu aby bylo videl co se deje
     *
     *
     */
    public static void vyrob(Vyrobky vv) throws InterruptedException {
        //prehodim ze stavu BACKLOG do stavu TO_DO
        addPrubehList("Prevedeni Vyrobku: " + vv.getNazev() + " do stavu TO_DO.");
        vv.zmenUsek(vv);
        addPrubehList("Vyrobek " + vv.getNazev() + " je ve stavu " + vv.getMomentalniUsek() + ".");

        //zkontroluju jestli je volna nejaka vyrobni linka a kdyztak ji zaberu, pokud neni dam sleep a vyrobek bude cekat
        kontrolaVyrLinky(vv);



        //dokonceni Vyroby
        vv.zmenUsek(vv);
        addPrubehList("Vyrobek: " + vv.getNazev() + " hotovy.");

    }
    //Nekonecny cyklus
    public static void kontrolaVyrLinky(Vyrobky vvv) throws InterruptedException {
        addPrubehList("Vyrobek: " + vvv.getNazev() + " zkousi najit volnou vyrobni linku." + LocalDateTime.now());
        Random rdm = new Random();
        Thread.sleep(rdm.nextInt(7000));
        //random protoze se nekdy stavalo ze vlezli 2 najednou do jedne linky
        //schvalne jestli projdes na to jak to osefovat pomoci synchonized jinak nez dat
        //celou tu metodu do do bloku synchonized :D
        if (vl1.isDostupny()) {
            vl1.setDostupny(false);
            addPrubehList("Vyrobek: " + vvv.getNazev() + " se bude vyrabet na vyrobni lince: " + vl1.getNazev() + ".");
        } else if (vl2.isDostupny()) {
            vl2.setDostupny(false);
            addPrubehList("Vyrobek: " + vvv.getNazev() + " se bude vyrabet na vyrobni lince: " + vl2.getNazev() + ".");
        } else if (vl3.isDostupny()) {
            vl3.setDostupny(false);
            addPrubehList("Vyrobek: " + vvv.getNazev() + " se bude vyrabet na vyrobni lince: " + vl3.getNazev() + ".");
        } else {
            addPrubehList("Pro vyrobek: " + vvv.getNazev() + " neni volna zadna vyrobni linka.");
            Thread.sleep(10000);
            vl3.setDostupny(true);
            kontrolaVyrLinky(vvv);
        }
    }
}
