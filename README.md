# Backend Development Scaffold Project

The aim of this project is to provide a generic scaffold for Java language development, allowing developers to quickly start business development without having to worry about infrastructure concerns such as permissions, login/logout, etc.

## Technologies Used

 Spring Cloud

 Gateway (Web Flutter)

 MyBatis Plus

 Dubbo

 Swagger

 OAuth 2.0 + JWT + Spring Security

 Redis

 MySQL

 Microservice Tracing with Sleuth + Zipkin

## Additional Technologies to be Added in the Future

 Performance Monitoring and Instrumentation with Prometheus

 Distributed Task Scheduling, considering XXL-Job

 Gateway Rate Limiting and Circuit Breaking, considering Sentinel

 Additional Authentication Methods for OAuth, such as SMS/Authorization Code/Client

 Messaging Queue Components: RocketMQ/RabbitMQ/Kafka

 MySQL Sharding and Partitioning Components

 ...

## Current Capabilities of the Framework

Single sign-on

Authorization control

RPC invocation

API rate limiting

Swagger documentation (access address: http://127.0.0.1/doc.html#/home)

## Project Usage Overview

To add a new module, you can refer to the service-one and service-two examples.

If you find this project useful, please consider giving it a star.

## Startup

You need to start the following services in the specified order: MySQL, Nacos, Redis, ZooKeeper, Zipkin, Dubbo-Admin.

Welcome to contribute code if you have similar interests.
[Personal Blog](https://www.yuque.com/dashboard)
