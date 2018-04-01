### Circle CI

[![CircleCI](https://circleci.com/gh/gbvbahia01/Ecommerce/tree/master.svg?style=svg)](https://circleci.com/gh/gbvbahia01/Ecommerce/tree/master)

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

