package icu.hilin.mongo.entity.qo;

import cn.hutool.core.util.ObjectUtil;
import com.anwen.mongo.conditions.update.UpdateWrapper;
import icu.hilin.mongo.entity.table.BaseO;
import lombok.Data;

@Data
public class BaseUpdateQO<T extends BaseO> extends BaseQO<T> {

    private T set;

    public UpdateWrapper<T> toUpdateWrapper() {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        if (ObjectUtil.isNotEmpty(set)) {
            obj2Map(set).forEach((k, v) -> updateWrapper.set(ObjectUtil.isNotNull(k) && ObjectUtil.isNotNull(v), k, v));
        }

        obj2Map(getEq()).forEach((k, v) -> updateWrapper.eq(ObjectUtil.isNotEmpty(k) && ObjectUtil.isNotEmpty(v), k, v));
        obj2Map(getLike()).forEach((k, v) -> updateWrapper.like(ObjectUtil.isNotEmpty(k) && ObjectUtil.isNotEmpty(v), k, v));
        updateWrapper.lambdaUpdate()
                .gte(ObjectUtil.isNotEmpty(getStartTime()), T::getCreateTime, getStartTime())
                .lte(ObjectUtil.isNotEmpty(getEndTime()), T::getCreateTime, getEndTime());
        return updateWrapper;
    }

}
