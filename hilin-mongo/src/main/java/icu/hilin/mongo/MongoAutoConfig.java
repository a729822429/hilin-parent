package icu.hilin.mongo;

import org.springframework.context.annotation.Bean;

public class MongoAutoConfig {
//    @Bean
    public MongoInterceptor mongoInterceptor() {
        return new MongoInterceptor();
    }


    @Bean
    public MongoMetaObjectHandler mongoMetaObjectHandler() {
        return new MongoMetaObjectHandler();
    }

}
