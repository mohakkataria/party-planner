package com.partyplanner;

import com.partyplanner.models.Customer;
import com.partyplanner.services.CustomerService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class Application {

  private final CustomerService customerService;
  private static final Double officeLatitude = 53.339428;
  private static final Double officeLongitude = -6.257664;
  private static final double DEGREE_CIRCLE_EARTH = 6371;
  private static final double MAX_DISTANCE_THRESHOLD = 100;

  @Inject
  public Application(CustomerService customerService) {
    this.customerService = customerService;
  }

  public void run() {
    List<Customer> customers = customerService.getCustomers();
    customers = calculateInvitations(customers);
    printCustomers(customers);
  }

  private void printCustomers(List<Customer> customers) {
    for (Customer customer : customers) {
      System.out.println(
        String.format("Name : %s, User Id : %s", customer.getName(), customer.getUserId()));
    }
  }


  private List<Customer> calculateInvitations(List<Customer> customers) {
    return customers.stream().filter(
      c -> this.getGreatCircleDistance(c.getLatitude(), c.getLongitude()) < MAX_DISTANCE_THRESHOLD)
      .sorted(Comparator.comparing(Customer::getUserId))
      .collect(Collectors.toList());
  }

  public Double getGreatCircleDistance(Double x1, Double y1) {
    // convert to radians
    Double latx1Rad = Math.toRadians(x1);
    Double longx1Rad = Math.toRadians(y1);
    Double latx2Rad = Math.toRadians(officeLatitude);
    Double longx2Rad = Math.toRadians(officeLongitude);

    Double angle = Math.acos(Math.sin(latx1Rad) * Math.sin(latx2Rad)
      + Math.cos(latx1Rad) * Math.cos(latx2Rad) * Math.cos(longx1Rad - longx2Rad));

    return DEGREE_CIRCLE_EARTH * angle;
  }
}
