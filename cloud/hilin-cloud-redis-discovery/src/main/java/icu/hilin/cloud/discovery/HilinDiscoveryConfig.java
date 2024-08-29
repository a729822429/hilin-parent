package icu.hilin.cloud.discovery;

import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.net.NetUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "icu.hilin.redis.discovery")
public class HilinDiscoveryConfig {

    /**
     * ip过滤，只获取本机A、B、C类地址
     */
    private String ip = NetUtil.localIpv4s().stream().filter(ip ->
            // 排除本机环回地址
            !ip.startsWith("127")
                    // 排除D类网络
                    && Ipv4Util.ipv4ToLong(ip) < Ipv4Util.ipv4ToLong("224.0.0.0")
    ).findFirst().orElse(null);
}