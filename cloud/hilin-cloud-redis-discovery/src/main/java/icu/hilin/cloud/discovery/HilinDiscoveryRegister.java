package icu.hilin.cloud.discovery;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HilinDiscoveryRegister {

    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(1);
    private static boolean isInit = false;
    @Autowired
    private HilinDiscoveryConfig hilinDiscoveryConfig;
    @Value("${server.port:8080}")
    private int port;

    public HilinDiscoveryRegister(StringRedisTemplate redisTemplate, String applicationName) {
        if (!isInit) {
            EXECUTOR.scheduleAtFixedRate(() -> {
                HilinServiceInstance serviceInstance = new HilinServiceInstance();

                serviceInstance.setServiceId(applicationName);
                serviceInstance.setSecure(false);
                serviceInstance.setMetadata(new HashMap<>());
                serviceInstance.setHost(hilinDiscoveryConfig.getIp());
                serviceInstance.setPort(port);

                redisTemplate.opsForValue().set(String.format(HilinDiscoveryClient.RK_INSTANCE_PREFIX, serviceInstance.getServiceId(), hilinDiscoveryConfig.getIp() + "_" + port), JSONUtil.toJsonStr(serviceInstance), 10, TimeUnit.SECONDS);
            }, 1, 1, TimeUnit.SECONDS);
            isInit = true;
        }
    }
}
