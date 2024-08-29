package icu.hilin.cloud.discovery;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class HilinDiscoveryClient implements DiscoveryClient {

    /**
     * redis中存放服务信息的key
     * 第一个%s: serviceId
     * 第二个%s: 服务器ip和端口
     */
    public static final String RK_INSTANCE_PREFIX = "icu.hilin.cloud.service.%s.%s";

    private final StringRedisTemplate redisTemplate;

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        Set<String> keys = redisTemplate.keys(String.format(RK_INSTANCE_PREFIX, serviceId, "*"));
        List<ServiceInstance> serviceInstances = new ArrayList<>();

        if (keys != null) {
            for (String key : keys) {
                serviceInstances.add(JSONUtil.toBean(redisTemplate.opsForValue().get(key), ServiceInstance.class));
            }
        }
        return serviceInstances;
    }

    @Override
    public List<String> getServices() {
        Set<String> keys = redisTemplate.keys(String.format(RK_INSTANCE_PREFIX, "*", "*"));

        Set<String> serviceIds = new HashSet<>();
        for (String key : keys) {
            serviceIds.add(key.substring(24,key.lastIndexOf(".")));
        }

        return new ArrayList<>(serviceIds);
    }
}
