package com.langinteger.demo.application;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoetService {

  private static final Logger logger = LoggerFactory.getLogger(PoetService.class);

  private static final int MAX_POETRY_NO = 43017;
  private static final int RANDOM_UPPER_BOUND = MAX_POETRY_NO + 1;
  private static final Random RANDOM = new Random();


  public static Future<JsonObject> getRandomPoetry() {
    return DatabasePoolFactory.pool()
        .query(String.format("select p.title as title, pt.name as poet_name, p.content as content"
            + " from poetries p left join poets pt "
            + " on p.poet_id = pt.id where p.id = %s", RANDOM.nextInt(RANDOM_UPPER_BOUND)))
        .execute()
        .compose(rows -> {
          if (rows.size() == 1) {
            logger.info("find one row");
            Row row = rows.iterator().next();
            JsonObject ret = JsonObject.of("title", row.getString("title"),
                "content", row.getString("content"),
                "poet_name", row.getString("poet_name"));
            return Future.succeededFuture(ret);
          } else {
            logger.info("find more than one row");
          }
          return Future.failedFuture("xx");
        }).onFailure(
            exception -> logger.info("query poetry fail. message: {}", exception.getMessage(),
                exception));
  }
}
