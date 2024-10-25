package com.example.demo.infrastructure;

import com.example.demo.models.Product;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;


@Component
public class ProductDAO {
    private final MongoCollection<Document> collection;

    public ProductDAO(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public Product find(String productId) {
        Document document = collection.find(
                Filters.eq("_id",new ObjectId(productId))
        ).first();

        if(document == null){
            return null;
        }

        return new Product(
                document.getObjectId("_id").toString(),
                document.getString("name"),
                document.getInteger("price")
        );
    }
}
