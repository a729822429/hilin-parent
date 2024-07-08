package icu.hilin.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RuntimeUtil {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000));


    /**
     * 执行，只关注结果
     */
    public static List<String> run(String commandStr) {
        CountDownLatch latch = new CountDownLatch(1);

        List<String> lines = new ArrayList<>();
        run(commandStr, new RuntimeListener() {
            @Override
            public void readLine(String line) {
                lines.add(line);
            }

            @Override
            public void end() {
                latch.countDown();
            }
        });

        return lines;
    }

    /**
     * 异步执行，关注过程
     */
    public static void run(String commandStr, RuntimeListener listener) {
        if (listener == null) {
            listener = RuntimeListener.DEFAULT_LISTENER;
        }
        log.info("开始执行 command : {}", commandStr);
        try {
            Process process = Runtime.getRuntime().exec(commandStr);
            // 获取输出流，此处是否可以改为只要录制，给出一个录制地址即可，不用管是否已经录制完成。录制本身的工作由录制代码完成
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                // 这个必须有 reader.readLine会阻塞当前线程，除非是有输入或者时进程退出才会继续
                while ((line = reader.readLine()) != null) {
                    listener.readLine(line);
                }
                listener.end();
            }
        } catch (Exception e) {
        }

    }

    public interface RuntimeListener {

        RuntimeListener DEFAULT_LISTENER = new RuntimeListener() {
        };

        default ThreadPoolExecutor threadPool() {
            return EXECUTOR;
        }

        default void readLine(String line) {
        }

        default void end() {
        }
    }

}
