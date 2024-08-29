package icu.hilin.cloud.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.data.redis.core.StringRedisTemplate;

public class HilinServiceRegistry implements ServiceRegistry<Registration> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void register(Registration registration) {
    }

    @Override
    public void deregister(Registration registration) {
        redisTemplate.delete(registration.getServiceId());
    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(Registration registration, String status) {

    }

    @Override
    public Object getStatus(Registration registration) {
        return null;
    }
}
