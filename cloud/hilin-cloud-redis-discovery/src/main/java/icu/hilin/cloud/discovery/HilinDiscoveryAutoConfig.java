package icu.hilin.cloud.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

@EnableConfigurationProperties({
        HilinDiscoveryConfig.class
})
public class HilinDiscoveryAutoConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public HilinDiscoveryRegister hilinDiscoveryRegister(@Value("${spring.application.name:UNKNOWN}") String applicationName, HilinDiscoveryConfig hilinDiscoveryConfig) {
        return new HilinDiscoveryRegister(redisTemplate, applicationName);
    }

    @Bean
    public HilinDiscoveryClient hilinDiscoveryClient() {
        return new HilinDiscoveryClient(redisTemplate);
    }

}
