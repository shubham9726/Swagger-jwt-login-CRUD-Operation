package com.decipherzone.studmgmtmongodb.configuration;

import com.decipherzone.studmgmtmongodb.configuration.properties.MongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Mongo Config
 */
public class MongodbConfiguration extends AbstractMongoConfiguration {


  private MongoProperties mongoProperties;

  @Autowired
  public MongodbConfiguration(MongoProperties mongoProperties) {
    this.mongoProperties = mongoProperties;
  }

  @Bean
  @Override
  public MongoClient mongoClient() {
    return new MongoClient(new MongoClientURI(mongoProperties.getMongoUri()));
  }

  @Override
  protected String getDatabaseName() {
    return mongoProperties.getMongoDatabase();
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), getDatabaseName());
  }
}
