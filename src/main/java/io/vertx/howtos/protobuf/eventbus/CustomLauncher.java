package io.vertx.howtos.protobuf.eventbus;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;

public class CustomLauncher extends Launcher {

  public static void main(String[] args) {
    new CustomLauncher().dispatch(args);
  }

  @Override
  public void afterStartingVertx(Vertx vertx) {
    ProtobufCodec protobufCodec = new ProtobufCodec();
    vertx.eventBus().registerCodec(protobufCodec);
    vertx.eventBus().codecSelector(o -> protobufCodec.test(o.getClass().getName()) ? protobufCodec.name() : null);
  }
}
