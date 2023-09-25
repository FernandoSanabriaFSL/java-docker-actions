package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.URLEncoder;

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
        try {
            String encodedUser = URLEncoder.encode(dbUser, "UTF-8");
            String encodedPassword = URLEncoder.encode(dbPassword, "UTF-8");

            String uri = String.format("mongodb+srv://%s:%s@%s/%s",
                    encodedUser, encodedPassword, clusterAddress, dbName);
            return MongoClients.create(uri);
        } catch (Exception e) {
            throw new RuntimeException("Error creating MongoClient", e);
        }
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), dbName);
    }
}
