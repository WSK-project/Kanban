package sample;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Instance {
    //Suroviny do lokalniho skladu
    static Suroviny s_1 = new Suroviny("Surovina1");
    static Suroviny s_2 = new Suroviny("Surovina2");
    static Suroviny s_3 = new Suroviny("Surovina3");
    static Suroviny s_4 = new Suroviny("Surovina4");
    static Suroviny s_5 = new Suroviny("Surovina5");
    static Suroviny s_6 = new Suroviny("Surovina6");
    static Suroviny s_7 = new Suroviny("Surovina7");

    //zaznamy pro lokalni sklad
    static ZaznamuSkladu zs_l_1 = new ZaznamuSkladu(s_1, 100);
    static ZaznamuSkladu zs_l_2 = new ZaznamuSkladu(s_2, 100);
    static ZaznamuSkladu zs_l_3 = new ZaznamuSkladu(s_3, 100);
    static ZaznamuSkladu zs_l_4 = new ZaznamuSkladu(s_4, 100);
    static ZaznamuSkladu zs_l_5 = new ZaznamuSkladu(s_5, 100);
    static ZaznamuSkladu zs_l_6 = new ZaznamuSkladu(s_6, 100);
    static ZaznamuSkladu zs_l_7 = new ZaznamuSkladu(s_7, 100);

    //zaznamu pro vzdaleny sklad
    static ZaznamuSkladu zs_v_1 = new ZaznamuSkladu(s_1, 1000);
    static ZaznamuSkladu zs_v_2 = new ZaznamuSkladu(s_2, 1000);
    static ZaznamuSkladu zs_v_3 = new ZaznamuSkladu(s_3, 1000);
    static ZaznamuSkladu zs_v_4 = new ZaznamuSkladu(s_4, 1000);
    static ZaznamuSkladu zs_v_5 = new ZaznamuSkladu(s_5, 1000);
    static ZaznamuSkladu zs_v_6 = new ZaznamuSkladu(s_6, 1000);
    static ZaznamuSkladu zs_v_7 = new ZaznamuSkladu(s_7, 1000);

    //sety surovin pro sklady
    static List<ZaznamuSkladu> zaznamySkladuLok = Arrays.asList(zs_l_1, zs_l_2, zs_l_3, zs_l_4, zs_l_5, zs_l_6, zs_l_7);
    static List<ZaznamuSkladu> zaznamySkladuVzd = Arrays.asList(zs_v_1, zs_v_2, zs_v_3, zs_v_4, zs_v_5, zs_v_6, zs_v_7);

    //Sklady
    static Sklady sklad_lok = new Sklady("Lokalni sklad", TypSkladu.LOKALNI, 0, zaznamySkladuLok);
    static Sklady sklad_vzd = new Sklady("Vzdaleny sklad", TypSkladu.VZDALENY, 5000, zaznamySkladuVzd);

    static Semaphore vyrobniLinky = new Semaphore(3);
    static Semaphore pracovnik = new Semaphore(1);
}
