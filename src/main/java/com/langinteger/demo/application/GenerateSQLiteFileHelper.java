package com.langinteger.demo.application;

import io.vertx.core.Future;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateSQLiteFileHelper {

  public static void main(String[] args) {
    DatabasePoolFactory.init("src/main/resources/sql/data.sqlite");

    String createPoets = "    CREATE TABLE IF NOT EXISTS poets (\n"
        + "        id INTEGER PRIMARY KEY,\n"
        + "        name TEXT NOT NULL,\n"
        + "        created_at TEXT NOT NULL,\n"
        + "        updated_at TEXT NOT NULL\n"
        + ");";

    String createPoetries = "    CREATE TABLE IF NOT EXISTS poetries (\n"
        + "        id INTEGER PRIMARY KEY,\n"
        + "        poet_id INTEGER NOT NULL,\n"
        + "        content TEXT NOT NULL,\n"
        + "        title TEXT NOT NULL,\n"
        + "        created_at TEXT NOT NULL,\n"
        + "        updated_at TEXT NOT NULL\n"
        + ");";

    DatabasePoolFactory.pool().query(createPoets).execute()
        .compose(ret -> {
          System.out.println("create poets success");
          return DatabasePoolFactory.pool().query(createPoetries).execute();
        })
        .compose(ret -> {
          System.out.println("create poetries success");
          try {
            getResourceFileAsString("sql/tang_poetry_poetries.sql")
                .forEach(line -> {
                  DatabasePoolFactory.pool().query(line).execute()
                      .onSuccess(placeHolder -> System.out.println("insert success"))
                      .onFailure(Throwable::printStackTrace);
                });
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          return Future.succeededFuture();
        });
  }

  static List<String> getResourceFileAsString(String fileName) throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(fileName)) {
      if (is == null) {
        return null;
      }
      try (InputStreamReader isr = new InputStreamReader(is);
          BufferedReader reader = new BufferedReader(isr)) {
        return reader.lines().collect(Collectors.toList());
      }
    }
  }

}
