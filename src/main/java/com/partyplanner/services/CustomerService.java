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
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class CustomerService {

  private final String filePath;

  @Inject
  public CustomerService(String filePath) {
    this.filePath = filePath;
  }

  public List<Customer> getCustomers() {
    List<Customer> customers = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      InputStream is = new URL(filePath).openStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
      customers = reader.lines().map(c -> {
        try {
          return objectMapper.readValue(c, Customer.class);
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        }
      })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
      reader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return customers;
  }
}
