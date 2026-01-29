# kennel-inventory
An app to handle my flock of dogs

**How to run the app?**

Checkout project

You have to build the project, then build the docker image(s), and at last run the docker image(s).

The commands in order in a bash terminal:

1. Build the project:

```
./gradlew clean build
```

2. Run the project

2.1 If you want to run the app without docker, then the command is the next:

```
./gradlew bootRun --args='--spring.profiles.active=h2'
```

2.2 If you just want a quick run, or dont want to use docker-compose, you have to start:
```
docker build -t kennel-inventory-0.0.1-snapshot .

docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=h2 kennel-inventory-0.0.1-snapshot
```
2.3 If you are ready to use docker-compose, then the app will run in a container and a postgresql db will serve it from another container
The command is:
```
docker-compose up --build
```
3. Manual testing

Now the application is running.
You can load the postman collection json in the root folder to your postman.
In the collection there is a prepared call for all endpoints. 


**Known issues:**

The simple gradle running has an issue that I'm still pursuing to resolve: The image links didn't loaded,
the application throws errors on getting the links. The dockerized runs have no this issue. 
