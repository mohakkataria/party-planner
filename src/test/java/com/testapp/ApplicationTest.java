package com.testapp;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.testapp.services.MerchantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Mock
    private MerchantService svc;

    @InjectMocks
    private Application application;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testA() throws Exception {
        application.run();
        //ArgumentCaptor<Object> argCaptor = ArgumentCaptor.forClass(Object.class);
    }
}
