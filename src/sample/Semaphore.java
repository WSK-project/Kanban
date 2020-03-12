package sample;

public class Semaphore implements Lock {

    private final int licenses;

    /**
     * pocet povolenych soubeznych pristupu k prostredkum
     * Jednoduseji: pocet volnych vyrobnich linek
     */
    private int counter;

    public Semaphore(int licenses, int counter) {
        this.licenses = licenses;
        this.counter = counter;
    }

    public int getLicenses() {
        return licenses;
    }

    public int getCounter() {
        return counter;
    }

    public synchronized void acquire() throws InterruptedException {
        while (counter == 0){
            wait();
        }

        counter = counter - 1;

    }

    public synchronized void release() {
        if(counter < licenses){
            counter = counter + 1;
            notify();
        }

    }
}
