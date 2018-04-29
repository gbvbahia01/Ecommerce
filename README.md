## You are looking for a complete project this one is not for you. I just started it a few days ago. And it will take some time to have a very solid project.

### Circle CI Build [![CircleCI](https://circleci.com/gh/gbvbahia01/Ecommerce/tree/master.svg?style=svg)](https://circleci.com/gh/gbvbahia01/Ecommerce/tree/master)
### Coveralls [![Coverage Status](https://coveralls.io/repos/github/gbvbahia01/Ecommerce/badge.svg)](https://coveralls.io/github/gbvbahia01/Ecommerce)
### Environment Variables

Name | Description
------------ | -------------
**img_ecommerce_folder** | A path folder where all ecommerce images will be kept. When developing **copy** default images example at **src/main/resources/static/img/tomcat-folder** in this folder.



### Setup Tomcat JNDI
At server.xml file

```xml
  <GlobalNamingResources>
   ...

    <Resource name="jdbc/postgres_ecommerce"
           auth="Container"
           type="javax.sql.DataSource"
           driverClassName="org.postgresql.Driver"
           url="jdbc:postgresql://xxx.xxx.xxx.xxx:5432/db"
           factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
           removeAbandonedOnBorrow="true"
           removeAbandonedOnMaintenance="true"
           timeBetweenEvictionRunsMillis="10000"
           removeAbandonedTimeout="60"
           logAbandoned="true"
           username="xxxxxx"
           password="xxxxxx"
           maxTotal="20"
           maxIdle="10"
           maxWaitMillis="-1"/>

   ...
  </GlobalNamingResources>
```
Copy postgresql-42.2.2.jar file into TOMCAT_HOME/lib folder

### Setup Liquibase

You can use liquibase to create some data in a database to use for developing, tests or production.
In pom.xml you define your database in dev profile.
Change the url, username and password.

```xml
<profile>
    <id>database</id>
    <properties>
        <liquibase.url>jdbc:postgresql://xxx.xxx.xxx.xxx:5432/database</liquibase.url>
        <liquibase.driver>org.postgresql.Driver</liquibase.driver>
        <liquibase.username>xxxxxx</liquibase.username>
        <liquibase.password>xxxxxx</liquibase.password>
        <liquibase.schema>public</liquibase.schema>
        <liquibase.logging>warning</liquibase.logging>
    </properties>
</profile>
```        
After setup those information execute this command at the same directory that is you project, where you have the pom.file:

* For production I recommend to execute DDL commands.
 ```
 mvn liquibase:update -Pdatabase,liquibase-ddl
 ```
 * For developing and testing execute DDL and DML commands.
 ```
 mvn liquibase:update -Pdatabase,liquibase-all
 ```

 #### Clean a database **`(Attention: those commands will erase all data and a rollback is NOT possible)`**
 If you need to clean a database and run liquibase from scratch use those sql commands:

  ```sql
 truncate table databasechangelog;
 truncate table databasechangeloglock;
 drop schema  orders cascade;
 drop schema customers cascade;
 drop schema products cascade;
 drop schema commons cascade;
  ```
