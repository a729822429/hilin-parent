package icu.hilin.core.queue;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 队列
 */
public class Queue<T> implements Closeable {

    public final BlockingQueue<T> queueList = new LinkedBlockingDeque<>();

    @Getter
    private final List<QueueRunnable<T>> runnables = new ArrayList<>();

    @Getter
    private boolean closed = false;

    public Queue() {
        new Thread(() -> {
            while (!closed) {
                try {
                    T t = queueList.poll(30, TimeUnit.SECONDS);
                    for (int i = 0; ObjectUtil.isNotEmpty(runnables) && i < runnables.size(); i++) {
                        runnables.get(i).handler(t);
                    }
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

    public void pub(T t) {
        queueList.add(t);
    }

    public void addListener(QueueRunnable<T> runnable) {
        runnables.add(runnable);
    }

    public T get() {
        return queueList.poll();
    }


    @Override
    public void close() throws IOException {
        closed = true;
    }
}
