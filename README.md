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

## Temperature App

#### GET

-   ##### Between Dates

    -   http://localhost:51020/temperature/{date}/{date}

    -   ##### EXAMPLE

        -   http://localhost:51020/temperature/1622236039/1622236456

-   ##### Between Dates And Specific House

    -   http://localhost:51020/temperature/{date}/{date}/{houseId}

    -   ##### EXAMPLE

        -   http://localhost:51020/temperature/1622236039/1622236456/1

-   ##### Between Dates And Specific House And Specific Room

    -   http://localhost:51020/temperature/{date}/{date}/{houseId}/{roomId}

    -   ##### EXAMPLE

        -   http://localhost:51020/temperature/1622236039/1622236456/1/1
