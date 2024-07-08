package icu.hilin.mongo;

import com.anwen.mongo.handlers.DocumentHandler;
import com.anwen.mongo.handlers.MetaObjectHandler;
import org.bson.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MongoMetaObjectHandler implements DocumentHandler {

    @Override
    public List<Document> insertInvoke(List<Document> documents) {
        for (Document document : documents) {
            document.put("createTime", new Date());
            document.put("updateTime", new Date());
        }
        return documents;
    }

    @Override
    public List<Document> updateInvoke(List<Document> documents) {
        for (Document document : documents) {
            document.put("updateTime", new Date());
        }
        return documents;
    }
}
