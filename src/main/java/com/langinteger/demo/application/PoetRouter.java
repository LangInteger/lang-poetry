package com.langinteger.demo.application;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoetRouter {

  private static final Logger logger = LoggerFactory.getLogger(PoetRouter.class);

  public static Router build(Vertx vertx) {
    logger.info("build poet router");
    ThymeleafTemplateEngine engine = ThymeleafTemplateEngine.create(vertx);
    Router router = Router.router(vertx);
    router.route().handler(ctx -> {
      ctx.response().putHeader("Pragma", "no-cache");
      ctx.response().putHeader("Cache-Control", "no-cache");
      ctx.response().putHeader("Expires", "0");
      ctx.response().putHeader("content-type", "text/html;charset=UTF-8");
      PoetService.getRandomPoetry().compose(ret -> {
            engine.render(ret, "templates/index.html", res -> {
              if (res.succeeded()) {
                logger.info("render success");
                ctx.response().end(res.result());
              } else {
                logger.info("render fail");
                ctx.fail(res.cause());
              }
            });
            return Future.succeededFuture();
          })
          .onFailure(exception -> {
            logger.info("fail to handle request, message: {}",
                exception.getMessage(), exception);
            ctx.response().setStatusCode(500).end();

          });
    });
    return router;
  }
}
