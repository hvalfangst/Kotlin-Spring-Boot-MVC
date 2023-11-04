# Ktor Koin API with Flyway, Exposed and JWT using Maven


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

