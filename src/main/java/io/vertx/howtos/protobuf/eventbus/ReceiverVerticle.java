package io.vertx.howtos.protobuf.eventbus;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;

public class ReceiverVerticle extends VerticleBase {

  @Override
  public Future<?> start() {
    return vertx.eventBus().<GreetingRequest>consumer("greetings", msg -> {
      var request = msg.body();
      System.out.printf("Received request = %s (%d)%n", request.getName(), System.identityHashCode(request));
      var greeting = String.format("Hello %s", request.getName());
      var reply = GreetingReply.newBuilder().setMessage(greeting).build();
      System.out.printf("Sending reply = %s (%d)%n", reply.getMessage(), System.identityHashCode(reply));
      msg.reply(reply);
    }).completion();
  }
}
