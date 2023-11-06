# Kotlin Spring Boot API with Exposed ORM


## Requirements

* x86-64
* Linux/Unix
* Docker
* JDK 17


## Startup

The script "up" creates our database container and starts up our application:
```
1. docker-compose -f docker/db/docker-compose.yml up -d
2. mvn spring-boot:run
```


## Shutdown

The script "down" removes our database container:
```
1.docker-compose -f docker/db/docker-compose.yml down
```

## Load Testing using Bombardier
Bombardier is a widely-used open-source benchmarking and load testing tool designed to evaluate the performance and behavior of web servers and applications. 
It accomplishes this by generating a substantial volume of HTTP requests directed at a specific endpoint. 

In our tests, we focus on the http://localhost:8080/car-details/{id} endpoint, which serves as a key component of our application. This endpoint's function is to assemble an aggregate object, encompassing domain objects related to the owner, car, engine, tire, insurance, and repair. The aggregation process is achieved by querying the respective repositories associated with each of these domain objects. The result is a comprehensive representation of car details, offering a holistic view of the car, its owner, and various associated components.

By using Bombardier to assess the performance of this endpoint, we can gain crucial insights into how well our application handles varying levels of traffic and concurrency. This testing enables us to determine the application's responsiveness and scalability under real-world conditions, ensuring that it operates efficiently even as demands fluctuate.

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
The baseline test (Test 1) serves as a critical reference point for evaluating the application's performance. It simulates a moderate load with 10 concurrent connections and 1000 requests. In this scenario:

* The application achieved a request rate of 203.67 requests per second, reflecting its responsiveness under typical usage.
* The average latency was measured at 50.95ms, demonstrating efficient response times.
* All 1000 requests resulted in successful HTTP 2xx responses, indicating robust reliability.

This baseline test sets the stage for evaluating how the application's performance varies when exposed to different levels of concurrency and request counts


### Test 2 (C100, N1000)
In Test 2, where 100 concurrent connections and 1000 requests were applied:

* The request rate increased slightly by 1.44% compared to the baseline (Test 1), suggesting a modest scalability improvement.
* However, the average latency experienced a significant rise of 863.91%, indicating a potential trade-off between response rate and response time.
* Throughput showed a marginal increase of 2.63% compared to the baseline.

### Test 3 (C1000, N2000)
In Test 3, with 1000 concurrent connections and 2000 requests:

* The request rate experienced a notable decrease of 51.82% compared to the baseline (Test 1), revealing potential performance limitations under heavy concurrent load.
* The average latency surged dramatically by 17,040.39%, showcasing a substantial increase in response times.
* Throughput declined by 75.33% compared to the baseline, indicating a considerable reduction in data transfer capacity.
* The test also encountered timeout errors, highlighting the challenges faced by the application under extreme load conditions.


## Conclusion
In summary, the performance tests indicate that the application performs well under moderate load but faces scalability and latency challenges as the level of concurrency increases. 
To improve the application's performance under heavy load conditions, optimizations such as load balancing, connection pooling, and code enhancements may be necessary. 
Further testing and tuning are advisable to ensure the application can meet the demands of real-world usage.