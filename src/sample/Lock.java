package sample;

public interface Lock {
    void acquire() throws InterruptedException;

    void release();
}
