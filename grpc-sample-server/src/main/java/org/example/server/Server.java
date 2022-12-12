package org.example.server;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import org.example.server.service.SampleImpl;

import java.io.IOException;
import java.util.logging.Logger;

public class Server {

  private static final Logger logger = Logger.getLogger(Server.class.getName());
  private static io.grpc.Server server;

  public static void main(String[] args) throws IOException, InterruptedException {
    final Server server = new Server();
    server.start();
    server.blockUntilShutdown();

  }

  private void start() throws IOException {
    final int port = 50051;
    server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
      .addService(new SampleImpl() {
      })
      .build()
      .start();

    logger.info("----> Server started, listening on " + port);
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) server.awaitTermination();
  }
}
