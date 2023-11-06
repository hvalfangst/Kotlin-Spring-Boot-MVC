# Kotlin Spring Boot API with Exposed ORM


## Requirements

* x86-64
* Linux/Unix
* [Docker](https://www.docker.com/products/docker-desktop/)
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/)


## Startup

The script "up" creates our database container and starts up our application:
```
1. docker-compose -f docker/cars/docker-compose.yml up -d
2. mvn spring-boot:run
```

The associated `docker-compose` also contains initialization scripts for creating our database and inserting test rows.


## Shutdown

The script "down" removes our database container:
```
1.docker-compose -f docker/cars/docker-compose.yml down
```

## Load Testing using Bombardier
In this project we utilize [Bombardier](https://github.com/codesenberg/bombardier) to conduct load-testing.  

The subject of our tests is the `/car-details` endpoint, which is responsible for retrieving various vehicle-related data from different repositories and responding with an aggregate object.

### Test 1: 10 Concurrent Connections, 1000 Requests (Baseline)
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 10 -n 1000
Statistics:
  Reqs/sec: 203.67
  Latency: 50.95ms (Avg)
  HTTP codes: 2xx - 1000
  Throughput: 177.46KB/s
  ```

### Test 2: 100 Concurrent Connections, 1000 Requests
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 100 -n 1000
Statistics:
  Reqs/sec: 207.97 (↑1.44% from Test 1)
  Latency: 491.09ms (↑863.91% from Test 1)
  HTTP codes: 2xx - 1000
  Throughput: 181.64KB/s (↑2.63% from Test 1)
```

### Test 3: 1000 Concurrent Connections, 2000 Requests
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 1000 -n 2000
Statistics:
  Reqs/sec: 98.17 (↓51.82% from Test 1)
  Latency: 8.72s (↑17,040.39% from Test 1)
  HTTP codes: 2xx - 362, Errors: Timeout - 1638
  Throughput: 43.78KB/s (↓75.33% from Test 1)
```

## Test Result Analysis

### Baseline (C10, N1000)
The baseline test (Test 1), which serves as our reference point, simulates a moderate load with 10 concurrent connections and 1000 requests. In this scenario:

* The application achieved a request rate of 203.67 requests per second.
* The average latency was measured at 50.95ms.
* All 1000 requests resulted in successful HTTP 2xx responses.


### Test 2 (C100, N1000)
In Test 2, where 100 concurrent connections and 1000 requests were applied:

* The request rate and throughput stays pretty much the same
* The average latency experienced a significant rise of 863.91%, indicating that `disaster is imminent`

### Test 3 (C1000, N2000)
In Test 3, with 1000 concurrent connections and 2000 requests:

* The request rate experienced a notable decrease of 51.82% compared to the baseline (Test 1), revealing potential performance limitations under heavy concurrent load.
* The average latency surged dramatically by `17,040.39%`, showcasing an exponential increase in response times.
* Throughput declined by 75.33% compared to the baseline, indicating a considerable reduction in data transfer capacity.


## Conclusion
The findings suggest that this type of application is best suited for environments with low concurrent load due to the sheer amount of database calls to different repositories.
This could very much be due to the blocking nature of [JDBC](https://spring.io/blog/2018/12/07/reactive-programming-and-relational-databases). 

One can alleviate these performance issues by incorporating caching with [Redis](https://redis.io/) and load balancing with [NGINX](https://docs.nginx.com/nginx/admin-guide/load-balancer/http-load-balancer/),
but if performance is in fact important one should consider non-blocking alternatives such as [WebFlux](https://docs.spring.io/spring-framework/reference/web/webflux.html) or [Vert.x](https://vertx.io/); 
with non-blocking APIs such as [R2DBC](https://r2dbc.io/).