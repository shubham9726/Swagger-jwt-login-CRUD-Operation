package com.decipherzone.studmgmtmongodb.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoProperties {

  @Value("${spring.data.mongodb.uri}")
  private String mongoUri;

  @Value("${spring.data.mongodb.database}")
  private String mongoDatabase;

  @Value("${spring.data.mongodb.port}")
  private String dbPort;

  public String getMongoUri() {
    return mongoUri;
  }

  public void setMongoUri(String mongoUri) {
    this.mongoUri = mongoUri;
  }

  public String getMongoDatabase() {
    return mongoDatabase;
  }

  public void setMongoDatabase(String mongoDatabase) {
    this.mongoDatabase = mongoDatabase;
  }

  public String getDbPort() {
    return dbPort;
  }

  public void setDbPort(String dbPort) {
    this.dbPort = dbPort;
  }
}