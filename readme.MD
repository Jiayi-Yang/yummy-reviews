# Yummy Reviews by Jay Yang
## Overview
This is project for users to review all the yummy stuffs.

### Project Technical Overview:
Project Technical Overview:
This application is developed in Spring Framework by using Spring Boot, Spring Data, Hibernate, Spring RESTful web services, Postman, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.
* Business Rules
    1. Objects: users,items,ratings

* Development Approaches
    
    1. Created User,Item,Rating domain
    1. Used Hibernate to do the database schema migration
    

## Configure local enviorment
## setup local database
Refer to postgres docker [docker image](https://hub.docker.com/_/postgres) for environment option.
```$xslt
docker run --name training-db -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### migrate database schema
Refer to [flyway setup](https://flywaydb.org/documentation/migrations) find all [migration schema][src/main/resources/db/migrate]
```$xslt
mvn clean comple flyway:migrate
```