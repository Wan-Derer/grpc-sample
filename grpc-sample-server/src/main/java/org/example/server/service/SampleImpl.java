package org.example.server.service;

import com.google.protobuf.TextFormat;
import io.grpc.stub.StreamObserver;
import org.example.server.Car;
import org.example.server.Id;
import org.example.server.Person;
import org.example.server.SampleGrpc;

import java.util.logging.Logger;


public class SampleImpl extends SampleGrpc.SampleImplBase {

  private static final Logger logger = Logger.getLogger(SampleImpl.class.getName());

  @Override
  public void getCarById(Id carId, StreamObserver<Car> responseObserver) {
    Car car = Car.newBuilder()
      .setId(carId.getId())
      .setBrandName("Tesla")
      .setModel("Model 3")
      .build();

    logger.info("Get Car request, car: " + car);

    responseObserver.onNext(car);
    responseObserver.onCompleted();
  }

  @Override
  public void getPersonById(Id personId, StreamObserver<Person> responseObserver) {
    Car car = Car.newBuilder()
      .setId(111)
      .setBrandName("Vesla")
      .setModel("Model 333")
      .build();

    Person person = Person.newBuilder()
      .setId(personId.getId())
      .setName("Василий")
      .setAge(25)
      .setCar(car)
      .build();

    logger.info("Get Person request, person: \n" + TextFormat.printer().escapingNonAscii(false).printToString(person));

    responseObserver.onNext(person);
    responseObserver.onCompleted();
  }


}
