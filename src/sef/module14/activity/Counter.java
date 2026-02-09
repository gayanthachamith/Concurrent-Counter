package sef.module14.activity;

public class Counter {
    private int value = 0;

    // Part 1: NOT synchronized (race condition)
    public void incrementUnsafe() {
        value++;
    }

    // Part 2: synchronized fix
    public synchronized void incrementSafe() {
        value++;
    }

    public int getValue() {
        return value;
    }
}
