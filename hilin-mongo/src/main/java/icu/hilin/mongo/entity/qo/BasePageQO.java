package icu.hilin.mongo.entity.qo;

import com.anwen.mongo.mapper.BaseMapper;
import com.anwen.mongo.model.PageResult;
import icu.hilin.mongo.entity.table.BaseO;
import lombok.Data;

@Data
public class BasePageQO<T extends BaseO> extends BaseQO<T> {

    private Integer current;
    private Integer size;

    public PageResult<T> queryPage(BaseMapper mapper, Class<T> cls) {
        return mapper.page(toQueryWrapper(), current, size, cls);
    }


}
