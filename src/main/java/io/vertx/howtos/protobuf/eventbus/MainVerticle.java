package io.vertx.howtos.protobuf.eventbus;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;

public class MainVerticle extends VerticleBase {

  @Override
  public Future<?> start() {
    return Future.join(
      vertx.deployVerticle(new ReceiverVerticle()),
      vertx.deployVerticle(new SenderVerticle())
    );
  }
}
