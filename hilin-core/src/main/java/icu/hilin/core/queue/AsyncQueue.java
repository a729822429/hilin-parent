package icu.hilin.core.queue;

import cn.hutool.core.util.ObjectUtil;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 队列
 */
public class AsyncQueue<T> extends Queue<T> {


    public AsyncQueue(int coreThreadSize, int maxThreadSize) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreThreadSize, maxThreadSize, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
        new Thread(() -> {
            while (!isClosed()) {
                try {
                    T t = queueList.poll(30, TimeUnit.SECONDS);
                    for (int i = 0; ObjectUtil.isNotEmpty(getRunnables()) && i < getRunnables().size(); i++) {
                        int finalI = i;
                        executor.execute(() -> getRunnables().get(finalI).handler(t));

                    }
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

}
