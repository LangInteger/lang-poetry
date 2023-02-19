package com.langinteger.demo.application;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  private static final int PORT = 8080;

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    initDatabase();
    startWebServer(vertx);
  }

  private static void initDatabase() {
    DatabasePoolFactory.init(":resource:database/data.sqlite");
  }

  private static void startWebServer(Vertx vertx) {
    Router router = buildRouter(vertx);

    HttpServer server = vertx.createHttpServer();
    server.requestHandler(router).listen(PORT)
        .onSuccess(placeHolder -> logger.info("server started at port: {}", PORT))
        .onFailure(
            exception -> logger.error("server failed to start. message: {}",
                exception.getMessage(), exception));
  }

  private static Router buildRouter(Vertx vertx) {

    Router router = Router.router(vertx);
    router.route("/poetry/*").subRouter(PoetRouter.build(vertx));

    router.errorHandler(500, ctx -> {
      Throwable exception = ctx.failure();
      if (exception != null) {
        logger.error("failed to handle request. message: {}",
            exception.getMessage(), exception);
      }
    });
    return router;
  }

}
