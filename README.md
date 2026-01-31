# kennel-inventory
An app to handle my flock of dogs

**How to run the app?**

Checkout project

You have to build the project, then build the docker image(s), and at last run the docker image(s).

The commands in order in a bash terminal:

**1. Build the project:**

```
./gradlew clean build
```

**2. Run the project**

_2.1_ If you want to run the app without docker, then the command is the next:

```
./gradlew bootRun --args='--spring.profiles.active=h2'
```

_2.2_ If you just want a quick run, or dont want to use docker-compose, you have to start:
```
docker build -t kennel-inventory-0.0.1-snapshot .

docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=h2 kennel-inventory-0.0.1-snapshot
```
_2.3_ If you are ready to use docker-compose, then the app will run in a container and a postgresql db
will serve it from another container
The command is:
```
docker-compose up --build
```
**3. Manual testing**

Now the application is running.
You can load the postman collection json in the root folder to your postman.
In the collection there is a prepared call for all endpoints. 

**4. OpenAPI**

The Swagger UI is available runtime at the next link: http://localhost:8080/swagger-ui/index.html

The generated JSON representation of it available at the next link: http://localhost:8080/v3/api-docs

**5. Good to know**

There is a reference in the application.properties file where the schedule for the image service 
method is set. The current setting is for every second minute. If you want to for every minute then
change this line to the next:
```
image.service.cron.expression=0 * * * * *
```