package com.partyplanner.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partyplanner.models.Customer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CustomerService {
  private final String filePath;

  @Inject
  public CustomerService(String filePath) {
    this.filePath = filePath;
  }

  public List<Customer> getCustomers() {
    List<Customer> customers = new ArrayList<>();
    try {
      InputStream is = new URL(filePath).openStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
      String line = reader.readLine();
      while (line != null) {
        Customer customer = new ObjectMapper().readValue(line, Customer.class);
        customers.add(customer);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return customers;
  }
}
