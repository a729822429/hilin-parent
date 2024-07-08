package icu.hilin.mybatis.plus.entity.qo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.hilin.mybatis.plus.entity.table.BaseTable;
import lombok.Data;

@Data
public class BasePageQO<T extends BaseTable> extends BaseQO<T> {

    private Integer current;
    private Integer size;

    public Page<T> toPage() throws RuntimeException {
        return new Page<>(current, size);
    }

    public Page<T> queryPage(BaseMapper<T> mapper) {
        return mapper.selectPage(toPage(), toQueryWrapper());
    }


}
