package icu.hilin.mybatis.plus.entity.qo;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import icu.hilin.mybatis.plus.entity.table.BaseTable;
import lombok.Data;

@Data
public class BaseUpdateQO<T extends BaseTable> extends BaseQO<T> {

    private T set;

    public UpdateWrapper<T> toUpdateWrapper() {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        if (ObjectUtil.isNotEmpty(set)) {
            obj2Map(set).forEach((k, v) -> updateWrapper.set(ObjectUtil.isNotNull(k) && ObjectUtil.isNotNull(v), k, v));
        }

        obj2Map(getEq()).forEach((k, v) -> updateWrapper.eq(ObjectUtil.isNotEmpty(k) && ObjectUtil.isNotEmpty(v), k, v));
        obj2Map(getLike()).forEach((k, v) -> updateWrapper.like(ObjectUtil.isNotEmpty(k) && ObjectUtil.isNotEmpty(v), k, v));
        updateWrapper.lambda()
                .ge(ObjectUtil.isNotEmpty(getStartTime()), T::getCreateTime, getStartTime())
                .le(ObjectUtil.isNotEmpty(getEndTime()), T::getCreateTime, getEndTime());
        return updateWrapper;
    }

}
