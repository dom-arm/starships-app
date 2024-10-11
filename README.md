# Starships Application

This is a Spring Boot application that exposes one REST-endpoint.

## Instructions

### Clone the repository

```sh
git clone https://github.com/dom-arm/starships-app.git
```

### Run the app

The project uses Maven. When the repo is cloned, cd into the root folder and run the command:

```sh
./mvnw spring-boot:run
```

### Access the API

The one endpoint is:

```sh
http://localhost:8080/starships?sort=price&order=desc
```

Query parameters:

* `sort`: Field to sort by
* `order`: Order of sorting

### Response structure

```sh
[
  {
    "name": "Name",
    "cost_in_credits": "100000"
  },
  {
    "name": "Another Name",
    "cost_in_credits": "10000"
  },
  
  ...
]
```