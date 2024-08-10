# SpringBootMicroservice

## Eureka Server

http://localhost:8080/eureka/web

## resillience4J Circuit Breaker -->

http://localhost:8081/actuator <!-- Order-service/actuator -->

## Zipkin

http://localhost:9411/zipkin

## Docker

### Keycloack

docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.2 start-dev

### Zipkin

docker run -d -p 9411:9411 openzipkin/zipkin
