package icu.hilin.mongo.entity.qo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.anwen.mongo.annotation.collection.CollectionField;
import com.anwen.mongo.conditions.query.QueryWrapper;
import com.anwen.mongo.mapper.BaseMapper;
import icu.hilin.mongo.entity.table.BaseO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class BaseQO<T extends BaseO> {

    /**
     * 开始时间，要小于create_time
     */
    private Date startTime;
    /**
     * 结束时间，要大于create_time
     */
    private Date endTime;

    /**
     * 精确查询
     */
    private T eq;

    /**
     * 模糊查询
     */
    private T like;

    /**
     * 实体转换为查询
     */
    public QueryWrapper<T> toQueryWrapper() throws RuntimeException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        obj2Map(eq).forEach((k, v) -> queryWrapper.eq(ObjectUtil.isNotEmpty(k) && ObjectUtil.isNotEmpty(v), k, v));
        obj2Map(like).forEach((k, v) -> queryWrapper.like(ObjectUtil.isNotEmpty(k) && ObjectUtil.isNotEmpty(v), k, v));

        queryWrapper.gte(ObjectUtil.isNotEmpty(startTime), "create_time", getStartTime());
        queryWrapper.lte(ObjectUtil.isNotEmpty(endTime), "create_time", getEndTime());
        return queryWrapper;
    }

    public List<T> queryList(BaseMapper mapper, Class<T> cls) {
        return mapper.list(toQueryWrapper(), cls);
    }

    public T queryOne(BaseMapper mapper, Class<T> cls) {
        try {
            return queryList(mapper, cls).get(0);
        } catch (Exception e) {
            log.warn("queryOne查询失败 {}", JSONUtil.toJsonStr(this), e);
            return null;
        }
    }

    protected Map<String, Object> obj2Map(T obj) {
        Map<String, Object> map = new HashMap<>();
        if (ObjectUtil.isNotEmpty(obj)) {
            for (Field declaredField : obj.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                String tableFileName = declaredField.getName();
                Object tableFileValue = null;
                try {
                    tableFileValue = declaredField.get(obj);
                } catch (Exception ignored) {
                }
                CollectionField tableField = declaredField.getDeclaredAnnotation(CollectionField.class);
                if (ObjectUtil.isNotEmpty(tableField)) {
                    tableFileName = ObjectUtil.isNotEmpty(tableField.value()) ? tableField.value() : declaredField.getName();
                }
                map.put(tableFileName, tableFileValue);
            }
        }
        return map;
    }

}
