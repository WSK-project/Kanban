package sample;

public class ZaznamuSkladu {
    private Suroviny surovina;
    private int mnozstvi;

    public ZaznamuSkladu(Suroviny surovina, int mnozstvi) {
        this.surovina = surovina;
        this.mnozstvi = mnozstvi;
    }

    public Suroviny getSurovina() {
        return surovina;
    }

    public void setSurovina(Suroviny surovina) {
        this.surovina = surovina;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    @Override
    public String toString() {
        return "ZaznamuSkladu{" +
                "surovina=" + surovina +
                ", mnozstvi=" + mnozstvi +
                '}';
    }
}
