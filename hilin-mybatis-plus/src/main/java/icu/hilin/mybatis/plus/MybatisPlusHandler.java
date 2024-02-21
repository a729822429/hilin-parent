package icu.hilin.mybatis.plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MybatisPlusHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "create_time", Date.class, new Date());
        this.strictInsertFill(metaObject, "update_time", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "update_time", Date.class, new Date());
    }
}
