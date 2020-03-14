package com.testapp;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
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
}
