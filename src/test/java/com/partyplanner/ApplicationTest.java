package com.partyplanner;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.partyplanner.models.CustomerTest;
import com.partyplanner.services.CustomerService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private Application application;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUp() throws Exception {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @Test
  public void testWithNoCustomers() throws Exception {
    when(customerService.getCustomers()).thenReturn(emptyList());
    application.run();
    assertEquals("", outContent.toString());
  }

  @Test
  public void testWithOneCustomers() throws Exception {
    when(customerService.getCustomers())
      .thenReturn(List.of(CustomerTest.getDummyCustomer(1, null, null)));
    application.run();
    assertEquals("Name : Test Name 1, User Id : 1\n", outContent.toString());
  }

  @Test
  public void testWithSameCustomersLatLong() throws Exception {
    when(customerService.getCustomers())
      .thenReturn(List
        .of(CustomerTest.getDummyCustomer(1, 53d, -6d),
          CustomerTest.getDummyCustomer(2, 53d, -6d)));
    application.run();
    assertEquals("Name : Test Name 1, User Id : 1\nName : Test Name 2, User Id : 2\n",
      outContent.toString());
  }

  @Test
  public void testWithDifferentLatLongCustomers() throws Exception {
    when(customerService.getCustomers())
      .thenReturn(List
        .of(CustomerTest.getDummyCustomer(2, 53d, -6d),
          CustomerTest.getDummyCustomer(1, 53.1d, -6.1d)));
    application.run();
    assertEquals("Name : Test Name 1, User Id : 1\nName : Test Name 2, User Id : 2\n",
      outContent.toString());
  }

  @Test
  public void testWithMixOfSameAndDifferentLatLongCustomers() throws Exception {
    when(customerService.getCustomers())
      .thenReturn(List
        .of(CustomerTest.getDummyCustomer(2, 53d, -6d),
          CustomerTest.getDummyCustomer(1, 53.1d, -6.1d),
          CustomerTest.getDummyCustomer(3, 53.1d, -6.1d)));
    application.run();
    assertEquals("Name : Test Name 1, User Id : 1\nName : Test Name 2, User Id : 2\nName : Test Name 3, User Id : 3\n",
      outContent.toString());
  }

  @Test
  public void testWithMixOfOutOfBoundsLatLongCustomers() throws Exception {
    when(customerService.getCustomers())
      .thenReturn(List
        .of(CustomerTest.getDummyCustomer(2, 55d, -6d),
          CustomerTest.getDummyCustomer(1, 55.1d, -6.1d),
          CustomerTest.getDummyCustomer(3, 55.1d, -6.1d)));
    application.run();
    assertEquals("", outContent.toString());
  }

  @Test
  public void testWithMixOfInAndOutOfBoundsLatLongCustomers() throws Exception {
    when(customerService.getCustomers())
      .thenReturn(List
        .of(CustomerTest.getDummyCustomer(2, 53d, -6d),
          CustomerTest.getDummyCustomer(1, 55.1d, -6.1d),
          CustomerTest.getDummyCustomer(3, 53.1d, -6.1d)));
    application.run();
    assertEquals("Name : Test Name 2, User Id : 2\nName : Test Name 3, User Id : 3\n", outContent.toString());
  }
}
