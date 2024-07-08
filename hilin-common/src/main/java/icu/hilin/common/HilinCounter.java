package icu.hilin.common;

import java.util.concurrent.atomic.LongAdder;

public class HilinCounter {

    private final Object lock = new Object();

    private final LongAdder adder;

    public HilinCounter() {
        adder = new LongAdder();
    }

    public HilinCounter(long initValue) {
        this();
        adder.add(initValue);
    }

    public long getAndAdd(long addValue) {
        synchronized (lock) {
            long value = adder.longValue();
            adder.add(addValue);
            return value;
        }
    }

    public long addAndGet(long addValue) {
        synchronized (lock) {
            adder.add(addValue);
            return adder.longValue();
        }
    }

    public long get() {
        synchronized (lock) {
            return adder.longValue();
        }
    }

    public void add(long addValue) {
        synchronized (lock) {
            adder.add(addValue);
            adder.longValue();
        }
    }

}
