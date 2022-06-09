# spring-api
 
(Work in progress)

API mock service based on Spring Boot. This app runs on Heroku PaaS.

## Project structure

```
    [react-api]---REST API---[spring-api]
                                  |
                               REST API
                                  |
                             [unity-api]
```

- Frontend: https://github.com/araobp/react-api
- Backend: https://github.com/araobp/spring-api
- Backend in a virtual world: https://github.com/araobp/unity-api

### PostgreSQL database
```
spring-api-0::DATABASE=> \d box
                Table "public.box"
 Column |  Type   | Collation | Nullable | Default 
--------+---------+-----------+----------+---------
 id     | integer |           |          | 
 move   | boolean |           |          | 
 
 spring-api-0::DATABASE=> select * from box;
 id | move 
----+------
  2 | t
  0 | f
  1 | t
(3 rows)
```

## Set up

### Connection to PostgreSQL

Add "application.properties" file to the project root directory with the following properties:

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql:<... URL of your PostgreSQL database ...>
spring.datasource.username=<username>
spring.datasource.password=<password>
```

Or follow the following instructions if this app is deployed to Heroku: https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku
