package icu.hilin.cloud.discovery;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.client.ServiceInstance;

import java.net.URI;
import java.util.Map;

@Getter
@Setter
public class HilinServiceInstance implements ServiceInstance {

    private String serviceId;
    private String host;
    private int port;
    private boolean secure;
    private URI uri;
    private Map<String,String> metadata;
}
