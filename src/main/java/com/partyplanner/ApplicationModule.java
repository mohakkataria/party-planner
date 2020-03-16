package com.partyplanner;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.partyplanner.services.CustomerService;
import javax.inject.Named;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    @Named("uri")
    public String provideURI() {
        return "https://s3.amazonaws.com/intercom-take-home-test/customers.txt";
    }


    @Provides
    @Singleton
    public CustomerService provideCustomerService(@Named("uri") String filePath) {
        return new CustomerService(filePath);
    }
}
