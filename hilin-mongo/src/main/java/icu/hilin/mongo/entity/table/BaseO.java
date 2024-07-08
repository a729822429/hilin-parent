package icu.hilin.mongo.entity.table;

import cn.hutool.core.util.IdUtil;
import com.anwen.mongo.annotation.ID;
import com.anwen.mongo.annotation.collection.CollectionField;
import com.anwen.mongo.enums.FieldFill;
import com.anwen.mongo.enums.IdTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class BaseO {

    @ID(type = IdTypeEnum.ASSIGN_ID)
    private String id;

    /**
     * 创建时间
     */
    @CollectionField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 最后更新时间
     */
    @CollectionField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
