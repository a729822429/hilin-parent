package icu.hilin.mybatis.plus;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import icu.hilin.core.spi.biz.IFillUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Date;

public class MybatisPlusHandler implements MetaObjectHandler {

    @Autowired
    private ApplicationContext context;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "id", Long.class, IdUtil.getSnowflakeNextId());
        this.strictInsertFill(metaObject, "create_time", Date.class, new Date());
        this.strictInsertFill(metaObject, "update_time", Date.class, new Date());
        fillCreateUser(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "update_time", Date.class, new Date());
        fillCreateUser(metaObject);
    }

    public void fillCreateUser(MetaObject metaObject) {
        try {
            this.strictInsertFill(metaObject, "creator", Long.class, context.getBean(IFillUser.class).getUserId());
        } catch (BeansException ignored) {
        }
        try {
            this.strictInsertFill(metaObject, "updator", Long.class, context.getBean(IFillUser.class).getUserId());
        } catch (BeansException ignored) {
        }
    }

    public void fillUpdateUser(MetaObject metaObject) {
        try {
            this.strictInsertFill(metaObject, "updator", Long.class, context.getBean(IFillUser.class).getUserId());
        } catch (BeansException ignored) {
        }
    }
}
