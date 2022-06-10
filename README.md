# spring-api

API mock service based on Spring Boot. This app runs on Heroku PaaS.

## Database synchronization with Salesforce Cloud via Heroku Connect

box__c table is synchronized with "Box" object on Salesforce Cloud via Heroku Connect.

```
       [box__c]<-------- Heroku Connect -------->[Box]
Heroku with PostgreSQL                       Salesforce Cloud
```

## Table on PostgreSQL

```
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
spring.datasource.url=jdbc:postgresql:<... URL of your PostgreSQL database ...>?currentSchema=salesforce,public
spring.datasource.username=<username>
spring.datasource.password=<password>
```
Note that "?currentSchema=salesforce,public" is added to spring.datasource.url to include "salesforce" schema.

Or follow the following instructions if this app is deployed to Heroku: https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku
