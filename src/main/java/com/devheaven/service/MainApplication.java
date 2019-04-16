package com.devheaven.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the main entry point for the application.
 *
 * @author tomdewildt
 */
@SpringBootApplication
public class MainApplication {

    /**
     * This method is invoked by java to start the spring
     * application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}

