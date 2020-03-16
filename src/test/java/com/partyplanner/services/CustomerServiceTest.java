package com.partyplanner.services;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;

import com.partyplanner.models.Customer;
import com.partyplanner.wiremocks.CustomerDataWireMock;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  private CustomerService underTest;

  @ClassRule
  public static CustomerDataWireMock customerDataWireMock =
    new CustomerDataWireMock(options().port(5555));

  @Before
  public void setUp() throws IOException {

  }

  @Test
  public void testWithCorrectFileWithMultipleCustomers() {
    underTest = new CustomerService("http://localhost:5555/customer1.txt");
    assertEquals(underTest.getCustomers(), List.of(
      Customer.builder().latitude(52.986375).longitude(-6.043701).name("Christina McArdle")
        .userId(12).build(),
      Customer.builder().name("Alice Cahill").userId(1).latitude(51.92893).longitude(-10.27699)
        .build()));
  }

  @Test
  public void testWithCorrectFileWithMultipleCustomers2() {
    underTest = new CustomerService("http://localhost:5555/customer2.txt");
    assertEquals(underTest.getCustomers().size(), 32);
  }

  @Test
  public void testWithCorrectFileWithSingleCustomers() {
    underTest = new CustomerService("http://localhost:5555/customer0.txt");
    assertEquals(underTest.getCustomers(), List.of(
      Customer.builder().latitude(52.986375).longitude(-6.043701).name("Christina McArdle")
        .userId(12).build()));
  }

  @Test
  public void testWithCorrectFileWithMixOfValidAndInvalidJsonCustomer() {
    underTest = new CustomerService("http://localhost:5555/customer3.txt");
    assertEquals(underTest.getCustomers(), List.of(
      Customer.builder().latitude(52.986375).longitude(-6.043701).name("Christina McArdle")
        .userId(12).build()));
  }

}
