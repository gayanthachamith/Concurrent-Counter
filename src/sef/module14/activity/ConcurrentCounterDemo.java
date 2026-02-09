package sef.module14.activity;

public class ConcurrentCounterDemo {

    public static void main(String[] args) throws InterruptedException {
        int I = 20;         // number of threads
        int J = 100_000;    // increments per thread
        int expected = I * J;

        System.out.println("Threads (I) = " + I + ", Increments per thread (J) = " + J);
        System.out.println("Expected = " + expected);

        // Part 1: Reproduce bug
        runTest("BEFORE FIX (unsafe)", false, I, J, expected);

        // Part 2: Fix race condition (synchronized)
        runTest("AFTER FIX (synchronized)", true, I, J, expected);
    }

    private static void runTest(String label, boolean safeMode, int I, int J, int expected)
            throws InterruptedException {

        Counter counter = new Counter();
        Worker[] workers = new Worker[I];

        // Start threads
        for (int i = 0; i < I; i++) {
            workers[i] = new Worker(counter, J, safeMode);
            workers[i].start();
        }

        // Part C: join() -> wait for all workers
        for (int i = 0; i < I; i++) {
            workers[i].join();
        }

        int actual = counter.getValue();

        System.out.println("\n" + label);
        System.out.println("Actual   = " + actual);
        System.out.println("Diff     = " + (expected - actual));
    }
}
