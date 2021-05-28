# ES-HouseHold

### PORTS:

-   51000 to 51999

### PACKAGE

```bash
$ mvn clean package -DskipTests
```

### DOCKER COMPOSE

```bash
$ sudo docker-compose up -d --build
```

##### ONLY SERVICES

```bash
$ sudo docker-compose -f docker-compose-services.yml up -d --build
```
