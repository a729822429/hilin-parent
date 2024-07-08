package icu.hilin.mongo;

import cn.hutool.core.util.IdUtil;
import com.anwen.mongo.interceptor.Interceptor;
import com.anwen.mongo.model.MutablePair;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class MongoInterceptor implements Interceptor {

    @Override
    public List<Document> executeSave(List<Document> documentList) {
        for (Document document : documentList) {
            document.put("createTime", new Date());
            document.put("updateTime", new Date());
        }
        return Interceptor.super.executeSave(documentList);
    }

    @Override
    public MutablePair<Bson, Bson> executeUpdate(Bson queryBasic, Bson updateBasic, MongoCollection<Document> collection) {
        return Interceptor.super.executeUpdate(queryBasic, updateBasic, collection);
    }

    @Override
    public MutablePair<Bson, Bson> executeUpdate(Bson queryBasic, Bson updateBasic) {
        BasicDBObject obj = (BasicDBObject) updateBasic;
        obj.append("updateTime", new Date());
        return Interceptor.super.executeUpdate(queryBasic, obj);
    }
}
