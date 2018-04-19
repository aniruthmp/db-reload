# SpringBoot 2.0 - DB credentials update without restarting the App!


## Wait what? Did I read the title properly?

Oh boy. Spring team is kicking everyone's _@55_ by re-defining software development as we know it. I read our own [Josh Long](http://joshlong.com/) weekly blog on [spring.io/blog](https://spring.io/blog/) and this particularly caught my attention.

> I haven’t tried this out yet, but it looks like you can now add @RefreshScope to a javax.sql.DataSource @Bean definition and it’ll automatically be re-configured? If so, that’s super cool!

Read the above line once again! For quite some time, we have enjoyed [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/). With the recent launch of SpringBoot 2.0, we could change the database credential(s) on the fly and hit the _/actuator/refresh_ endpoint and refresh the DB connection pool with the new credentials **without restarting the app**. I have seen at several enterprises, where due to their internal requirements, the DBA team mandate to change the credentials on a periodic basis. This enables zero app down-time (withouth Blue/Green, etc.)

Enough of theory, now let's test this with a simple app.

1. `git clone https://github.com/aniruthmp/db-reload.git`
1. Run a local [spring-cloud-config](https://cloud.spring.io/spring-cloud-config/) at the default port _8888_
1. Ensure to provide the `spring.cloud.config.server.git.uri` to locate the configuration data for the above `db-reload` app. Make sure to have the following properties (update the DB credentials based on your set-up)

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_1
    username: springuser
    password: ThePassword
  jpa:
    hibernate:
      ddl-auto: create

management:
  endpoints:
    enabled-by-default: true
    web:
      exposure:
        include: refresh
```
**Note:** If you are unfamiliar about the changes introduced in SpringBoot 2.0 Actuator endpoints, check out this [introducing-actuator-endpoints-in-spring-boot-2-0](https://spring.io/blog/2017/08/22/introducing-actuator-endpoints-in-spring-boot-2-0)
1. Now start your `db-reload` app and _PUT_ some test data
```bash
$ curl -X PUT -d name=Ani -d email="ani@ani.io" http://localhost:8080/demo/add
$ curl -X PUT -d name=Jack -d email="jack@test.io" http://localhost:8080/demo/add
```

1. Now verify the above records by doing a _GET_
```bash
$ curl http://localhost:8080/demo/all
```

1. Now change your DB credentials in mysql after logging into your `mysqladmin`
```bash
$ ALTER USER 'springuser'@'localhost' IDENTIFIED BY 'NewThePassword';
```

1. After changing the password successfully, go ahead and update your `db-reload.yml` in your `spring.cloud.config.server.git.uri` and commit/push the change.
1. Now perform the _/actuator/refresh_ endpoint
```bash
$ curl -X POST http://localhost:8080/actuator/refresh
```

**Note:** in the output above that **spring.datasource.password** got refreshed. Also in the application logs you can notice that the DB connection pool got shutdown and started back. 
```
2018-04-18 21:27:29.660  INFO 15521 --- [nio-8080-exec-3] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2018-04-18 21:27:29.669  INFO 15521 --- [nio-8080-exec-3] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
```

1. Now try to call the _DELETE_ or _GET_ and you can see the output. 

