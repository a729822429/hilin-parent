package icu.hilin.core.queue;

public interface QueueRunnable<T> {

    void handler(T t);

}
