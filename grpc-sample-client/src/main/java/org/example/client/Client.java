package org.example.client;

import com.google.protobuf.TextFormat;
import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Client {
  private static final Logger logger = Logger.getLogger(Client.class.getName());

  private final SampleGrpc.SampleBlockingStub stub;

  public Client(Channel channel) {
    this.stub = SampleGrpc.newBlockingStub(channel);
  }

  public Car getCarById(int carId) {
    Id id = Id.newBuilder().setId(carId).build();
    logger.info("Car ID: " + id);
    return stub.getCarById(id);
  }

  private Person getPersonById(int personId) {
    Id id = Id.newBuilder().setId(personId).build();
    return stub.getPersonById(id);
  }

  public static void main(String[] args) throws InterruptedException {
    String target = "localhost:50051";

    ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
      .build();

    logger.info("Channel ---> " + channel);

    Client client = new Client(channel);

    Car car = client.getCarById(11);
    logger.info("Car ------> \n" + car.toString());

    Person person = client.getPersonById(22);
    logger.info("Person ---> \n" + TextFormat.printer().escapingNonAscii(false).printToString(person));

    channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);

  }

}
