package sef.module14.activity;

public class Worker extends Thread {
    private final Counter counter;
    private final int times;
    private final boolean safeMode;

    public Worker(Counter counter, int times, boolean safeMode) {
        this.counter = counter;
        this.times = times;
        this.safeMode = safeMode;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            if (safeMode) {
                counter.incrementSafe();
            } else {
                counter.incrementUnsafe();
            }
        }
    }
}
