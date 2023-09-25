package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ApplicationConfig {
    @Value("${MONGODB_USERNAME}")
    private String dbUser;

    @Value("${MONGODB_PASSWORD}")
    private String dbPassword;

    @Value("${MONGODB_CLUSTER_ADDRESS}")
    private String clusterAddress;

    @Value("${MONGODB_DB_NAME}")
    private String dbName;

    @Bean
    public MongoClient mongoClient() {
        String uri = String.format("mongodb://%s:%s@%s/%s?retryWrites=true&w=majority",
                dbUser, dbPassword, clusterAddress, dbName);
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), dbName);
    }
}
