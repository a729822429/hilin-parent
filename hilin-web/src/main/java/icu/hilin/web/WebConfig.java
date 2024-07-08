package icu.hilin.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "icu.hilin.web")
public class WebConfig {

    private boolean corsEnabled;
    private boolean responseWrapping;

}
