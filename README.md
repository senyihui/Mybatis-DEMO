### basic CURD function

参考：https://www.liaoxuefeng.com/wiki/1252599548343744/1331313418174498

### xml配置

* 注意点

  1. `application.properties`中切记：

     ```properties
     #================== mybatis =====================#
     #映射文件路径
     mybatis.mapper-locations=classpath:mybatis/mapper/*Dao.xml
     #指定mybatis生成包
     mybatis.type-aliases-package=com.example.demo.dao.*
     #指定mybatis配置文件路径
     mybatis.config-location=classpath:mybatis/mybatis-config.xml
     ```

  2. SpringBoot启动类添加`@MapperScan`

  3. 插件使用：Free Mybatis plugin