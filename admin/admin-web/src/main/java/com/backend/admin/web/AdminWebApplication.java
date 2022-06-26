package com.backend.admin.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminWebApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(AdminWebApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
