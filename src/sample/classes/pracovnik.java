package sample.classes;

import java.time.LocalDateTime;

public class pracovnik {
    String jmeno;
    boolean dostupny;

    public pracovnik(String jmeno, boolean dostupny) {
        this.jmeno = jmeno;
        this.dostupny = dostupny;
    }

    /*
    Metoda se vzdycky zavola kdyz se vyrobek dostane do stavu 'Done' a
    simuluje kontrolu vyrobku, Kontrola trva podle jednotlivych vyrobku
     */
    public boolean kontroluj(Vyrobky vyrobek, VyrobniLinka vl) throws InterruptedException {
        prejdiKVyrobniLince(vl);
        Utils.addPrubehList("Kontrolor: " + jmeno + " zacina kontrolu vyrobku: " + vyrobek.getNazev() + " v " + LocalDateTime.now() + ".\n");

        //Simulace kontroly
        Thread.sleep(vyrobek.getCasPotrebnyNaKontrolu());

        Utils.addPrubehList("Kontrolor: " + jmeno + " konci kontrolu vyrobku: " + vyrobek.getNazev() + " v " + LocalDateTime.now() + ".\n");
        return vyrobek.jeVadny();
    }

    /*
    Metoda simuluje prechod pracovnika mezi jednotlivymi vyrobnimi linkami
    pracujeme za predpokladu ze jsou vsechny stejne daleko od sebe
     */
    private void prejdiKVyrobniLince (VyrobniLinka vl) throws InterruptedException {
        Utils.addPrubehList("Kontrolor: " + jmeno + " se premistuje k vyrobni lince " + vl.toString() + ".\n");
        //simulace prechodu k jine vyrobni lince
        Thread.sleep(5000);
        Utils.addPrubehList("Kontrolor: " + jmeno + " stoji u vyrobni linky " + vl.toString() + ".\n");
    }


    //getters and setters
    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public boolean isDostupny() {
        return dostupny;
    }

    public void setDostupny(boolean dostupny) {
        this.dostupny = dostupny;
    }
}
