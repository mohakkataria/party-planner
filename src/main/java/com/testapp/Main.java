package com.testapp;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {

        // @TODO: write a command based system
        // @TODO: add tests to the system using junit
        Injector injector = Guice.createInjector(new ApplicationModule());
        Application app = injector.getInstance(Application.class);
        app.run();
    }
}
