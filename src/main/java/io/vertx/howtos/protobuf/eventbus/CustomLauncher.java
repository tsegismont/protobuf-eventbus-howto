package io.vertx.howtos.protobuf.eventbus;

import io.vertx.launcher.application.HookContext;
import io.vertx.launcher.application.VertxApplication;
import io.vertx.launcher.application.VertxApplicationHooks;

public class CustomLauncher extends VertxApplication implements VertxApplicationHooks {

  public CustomLauncher(String[] args) {
    super(args);
  }

  public static void main(String[] args) {
    new CustomLauncher(args).launch();
  }

  @Override
  public void afterVertxStarted(HookContext context) {
    var vertx = context.vertx();
    var protobufCodec = new ProtobufCodec();
    vertx.eventBus().registerCodec(protobufCodec);
    vertx.eventBus().codecSelector(body -> {
      return protobufCodec.appliesTo(body.getClass().getName()) ? protobufCodec.name() : null;
    });
  }
}
