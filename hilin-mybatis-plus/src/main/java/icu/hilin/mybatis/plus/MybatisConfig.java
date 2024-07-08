package icu.hilin.mybatis.plus;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@ConfigurationProperties(prefix = "icu.hilin.mybatis.plus")
public class MybatisConfig {

    private static final long DEFAULT_MAX_LIMIT = 1000;

    /**
     * 分页查询最大条数
     */
    private Long maxLimit = DEFAULT_MAX_LIMIT;

    public long getMaxLimit() {
        return maxLimit < 0 ? DEFAULT_MAX_LIMIT : maxLimit;
    }
}
