## ShardingSphere

### Dependency
```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.0.1</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.25</version>
</dependency>

<dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
    <version>4.1.1</version>
</dependency>

<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>4.0.3</version>
</dependency>
```

### application.properties
```.properties
spring.shardingsphere.datasource.names=ds
# 配置第一个数据库
spring.shardingsphere.datasource.ds.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.ds.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.ds.jdbc-url=jdbc:mysql://localhost:3306/mybatis_demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=true
spring.shardingsphere.datasource.ds.username=root
spring.shardingsphere.datasource.ds.password=passward

# 配置分库策略（此处没用）
# spring.shardingsphere.sharding.tables.users.database-strategy.standard.sharding-column=id
# 自定义分库策略
# spring.shardingsphere.sharding.tables.users.database-strategy.standard.precise-algorithm-class-name=com.example.test.config.MyDbPreciseShardingAlgorithm

# 配置users的分表策略
spring.shardingsphere.sharding.tables.users.actual-data-nodes=ds.users_$->{0..1}
spring.shardingsphere.sharding.tables.users.table-strategy.standard.sharding-column=id
# 自定义分表策略
spring.shardingsphere.sharding.tables.users.table-strategy.standard.precise-algorithm-class-name=com.example.demo.config.MyTablePreciseShardingAlgorithm

# 添加users表的id生成策略
spring.shardingsphere.sharding.tables.users.key-generator.column=id
spring.shardingsphere.sharding.tables.users.key-generator.type=SNOWFLAKE

# 打开sql输出日志
spring.shardingsphere.props.sql.show=true
```