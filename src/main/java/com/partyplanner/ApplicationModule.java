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
        return "";
    }

//    @Provides
//    @Singleton
//    public Sca

    @Provides
    @Singleton
    public CustomerService provideCustomerService() {
        return new CustomerService();
    }
}
