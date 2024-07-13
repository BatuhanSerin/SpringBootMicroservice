package com.example.discoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {
    public static void main(String[] args) {
        System.out.println("Discovery Service is running...");
        System.out.println("Discovery Service is running...");
        System.out.println("Discovery Service is running...");
        System.out.println("Discovery Service is running...");
        System.out.println("Discovery Service is running...");
        System.out.println("Discovery Service is running...");
        SpringApplication.run(DiscoveryServiceApplication.class, args);
    }

}
