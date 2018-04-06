### Circle CI Build [![CircleCI](https://circleci.com/gh/gbvbahia01/Ecommerce/tree/master.svg?style=svg)](https://circleci.com/gh/gbvbahia01/Ecommerce/tree/master)

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
 * For developer and testing execute DDL and DML commands.
 ```
 mvn liquibase:update -Pdatabase,liquibase-all
 ```
 