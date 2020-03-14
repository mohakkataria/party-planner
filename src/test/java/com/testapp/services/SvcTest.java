package com.testapp.services;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SvcTest {

    @Mock
    private WebTarget client;

    private final JerseyWebTarget jwt = mock(JerseyWebTarget.class);

    private final JerseyInvocation.Builder jwtB = mock(JerseyInvocation.Builder.class);

    @Before
    public void setUp() {
        when(client.path(anyString())).thenReturn(jwt);
        when(jwt.request(anyString())).thenReturn(jwtB);
        when(jwtB.accept(anyString())).thenReturn(jwtB);
    }

    @Test
    public void testA() {
    }

}
