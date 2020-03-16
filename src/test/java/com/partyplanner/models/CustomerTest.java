package com.partyplanner.models;

import java.util.Random;

public class CustomerTest {

  public static Customer getDummyCustomer(Integer userId, Double latitude, Double longitude) {
    Integer customerUserId = userId  == null ? new Random().nextInt(100) : userId;
    Double customerLatitude = latitude == null ? 53 + new Random().nextDouble() : latitude;
    Double customerLongitude = latitude == null ? -6 + new Random().nextDouble() : longitude;
    return Customer.builder()
      .userId(customerUserId)
      .name("Test Name " + customerUserId)
      .latitude(customerLatitude)
      .longitude(customerLongitude)
      .build();
  }

}
