package com.langinteger.demo.application;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.JDBCPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabasePoolFactory {

  private static final Logger logger = LoggerFactory.getLogger(DatabasePoolFactory.class);

  private static JDBCPool pool;

  public static JDBCPool pool() {
    if (pool == null) {
      throw new RuntimeException("database pool is not initialized yet");
    }
    return pool;
  }

  public static synchronized void init(String path) {
    JsonObject config = new JsonObject()
        .put("url", String.format("jdbc:sqlite:%s", path))
        .put("driver_class", "org.sqlite.JDBC")
        .put("max_pool_size", 4);

    Vertx vertx = Vertx.vertx();
    pool = JDBCPool.pool(vertx, config);
    logger.info("database pool is initialized");
  }

}
