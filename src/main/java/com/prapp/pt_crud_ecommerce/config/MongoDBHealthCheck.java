package com.prapp.pt_crud_ecommerce.config;

import com.mongodb.MongoException;
import com.mongodb.MongoTimeoutException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class MongoDBHealthCheck implements ApplicationListener<ContextRefreshedEvent> {

    private final MongoTemplate mongoTemplate;
    private final MongoProperties mongoProperties;
    private final Logger logger = LoggerFactory.getLogger(MongoDBHealthCheck.class);

    public MongoDBHealthCheck(MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        this.mongoTemplate = mongoTemplate;
        this.mongoProperties = mongoProperties;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Primero verificamos si hay una URI configurada
        if (!StringUtils.hasText(mongoProperties.getUri())) {
            logger.error("❌ Error al conectar a MongoDB Atlas: No se ha configurado la URI de conexión");
            return;
        }

        try {
            // Intentamos hacer un ping con timeout reducido
            mongoTemplate.executeCommand(new Document("ping", 1));
            logger.info("✅ Conexión exitosa a MongoDB Atlas");

        } catch (MongoTimeoutException e) {
            logger.error("❌ Error al conectar a MongoDB Atlas: Timeout en la conexión");

        } catch (MongoException e) {
            logger.error("❌ Error al conectar a MongoDB Atlas: {}", e.getMessage());

        } catch (Exception e) {
            logger.error("❌ Error al conectar a MongoDB Atlas: Error inesperado");
        }
    }
}
