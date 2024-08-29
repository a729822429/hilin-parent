package icu.hilin.cloud.discovery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TestApi {

    @Value("${server.port:8080}")
    private int port;

    @RequestMapping
    public int test(){
        return port;
    }

}
