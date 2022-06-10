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

## PostgreSQL database
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

spring-api-0::DATABASE=> \dn
       List of schemas
    Name    |     Owner      
------------+----------------
 public     | xxxxxxxxxxxxxx
 salesforce | xxxxxxxxxxxxxx
(2 rows)

spring-api-0::DATABASE=> set search_path = salesforce;
SET
spring-api-0::DATABASE=> select * from box__c;
     createddate     | isdeleted | name |   systemmodstamp    | id__c | move__c |        sfid        | id | _hc_lastop | _hc_err 
---------------------+-----------+------+---------------------+-------+---------+--------------------+----+------------+---------
 2022-06-09 22:04:39 | f         | Box2 | 2022-06-09 22:04:39 |     2 | f       | xxxxxxxxxxxxxxxxxx |  1 |            | 
 2022-06-09 22:03:57 | f         | Box0 | 2022-06-09 22:08:56 |     0 | t       | xxxxxxxxxxxxxxxxxx |  2 |            | 
 2022-06-09 22:04:24 | f         | Box1 | 2022-06-09 23:03:17 |     1 | t       | xxxxxxxxxxxxxxxxxx |  3 |            | 
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
