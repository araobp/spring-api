# Box API service based on Spring Boot

Note: "Box" is my original custom object that I created on my Salesforce development edition.

This app service runs on Heroku PaaS.

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

## Database synchronization with Salesforce Cloud via Heroku Connect

box__c table is synchronized with "Box" custom object on Salesforce Cloud via Heroku Connect.

```
       [box__c]<-------- Heroku Connect -------->[Box]
Heroku with PostgreSQL                       Salesforce Cloud
```

## Tables on PostgreSQL

Note:
- "box__c" is a custom object on Salesforce Cloud, and its data is synchronized with its counter part on Heroku via Heroku connect.
- "box_stats" is a table that exists on Heroku only.

```
spring-api-0::DATABASE=> \dn
       List of schemas
    Name    |     Owner      
------------+----------------
 public     | xxxxxxxxxxxxxx
 salesforce | xxxxxxxxxxxxxx
(2 rows)

spring-api-0::DATABASE=> select * from salesforce.box__c;
     createddate     | isdeleted | name |   systemmodstamp    | id__c | move__c |        sfid        | id | _hc_lastop | _hc_err 
---------------------+-----------+------+---------------------+-------+---------+--------------------+----+------------+---------
 2022-06-09 22:04:39 | f         | Box2 | 2022-06-09 22:04:39 |     2 | f       | xxxxxxxxxxxxxxxxxx |  1 |            | 
 2022-06-09 22:03:57 | f         | Box0 | 2022-06-09 22:08:56 |     0 | t       | xxxxxxxxxxxxxxxxxx |  2 |            | 
 2022-06-09 22:04:24 | f         | Box1 | 2022-06-09 23:03:17 |     1 | t       | xxxxxxxxxxxxxxxxxx |  3 |            | 
(3 rows)

spring-api-0::DATABASE=> select * from salesforce.box_stats;
 id__c | count 
-------+-------
     2 |     0
     1 |     0
     0 |     0
(3 rows)

spring-api-0::DATABASE=> \d salesforce.box__c;
                                              Table "salesforce.box__c"
     Column     |            Type             | Collation | Nullable |                    Default                    
----------------+-----------------------------+-----------+----------+-----------------------------------------------
 createddate    | timestamp without time zone |           |          | 
 isdeleted      | boolean                     |           |          | 
 name           | character varying(80)       |           |          | 
 systemmodstamp | timestamp without time zone |           |          | 
 id__c          | double precision            |           |          | 
 move__c        | boolean                     |           |          | 
 sfid           | character varying(18)       | ucs_basic |          | 
 id             | integer                     |           | not null | nextval('salesforce.box__c_id_seq'::regclass)
 _hc_lastop     | character varying(32)       |           |          | 
 _hc_err        | text                        |           |          | 
Indexes:
    "box__c_pkey" PRIMARY KEY, btree (id)
    "hc_idx_box__c_systemmodstamp" btree (systemmodstamp)
    "hcu_idx_box__c_id__c" UNIQUE, btree (id__c)
    "hcu_idx_box__c_sfid" UNIQUE, btree (sfid)
Triggers:
    hc_box__c_logtrigger AFTER INSERT OR DELETE OR UPDATE ON salesforce.box__c FOR EACH ROW WHEN (get_xmlbinary()::text = 'base64'::text) EXECUTE FUNCTION salesforce.hc_box__c_logger()
    hc_box__c_status_trigger BEFORE INSERT OR UPDATE ON salesforce.box__c FOR EACH ROW EXECUTE FUNCTION salesforce.hc_box__c_status()

spring-api-0::DATABASE=> \d salesforce.box_stats;
           Table "salesforce.box_stats"
 Column |  Type   | Collation | Nullable | Default 
--------+---------+-----------+----------+---------
 id     | integer |           |          | 
 count  | integer |           |          | 
```

## Set up

### Running this app on Heroku

[Prerequisite] Add a custom object "Box" on Salesforce Cloud:

```
Object name "Box" with the following fields:
- Id (id__c): Double
- Move (move__c): Boolean (appears as a checkbox in a detail page)

```

[Step1]  Add the following Heroku plugins:
- Heroku postgres
- Heroku connect

[Step2] Add Config Vars as follows:
```
SPRING_DATASOURCE_DRIVER-CLASS-NAME: org.postgresql.Driver
SPRING_DATASOURCE_URL: jdbc:postgresql:<... URL of your PostgreSQL database ...>?currentSchema=salesforce,public
SPRING_DATASOURCE_USERNAME: <username>
SPRING_DATASOURCE_PASSWORD: <password>

```

[Step3] Add the following Config Vars for HTTP Basic authentication:
```
BOX_USERNAME: <HTTP basic auth username>
BOX_PASSWORD: <HTTP basic auth password>
```

### Running this app on Eclipse for local testing

Add "application.properties" file to the project root directory with the following properties:

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql:<... URL of your PostgreSQL database ...>?currentSchema=salesforce,public
spring.datasource.username=<username>
spring.datasource.password=<password>
```
Note that "?currentSchema=salesforce,public" is added to spring.datasource.url to include "salesforce" schema.

