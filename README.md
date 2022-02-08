## [Jacoco测试接口覆盖率](http://blog.kail.xyz/post/2018-09-24/qa/jacoco-simple.html)

通过在JVM启动参数中加入`-javaagent`参数指定 JaCoCo 的代理程序，在Class Loader装载一个class前将统计代码插入class文件，达到在执行测试代码或者人工功能测试的时候，实时统计覆盖率的目的。

### JVM配置Agent

```xml
-javaagent:C:\Users\SN\IdeaProjects\Mybatis-DEMO\opt\jacocoagent.jar=includes=com.example.demo.*,output=tcpserver,address=127.0.0.1,port=8110
```

### Dump报告数据

```
java -jar jacococli.jar dump --address 127.0.0.1 --port 8110 --destfile C:/Users/SN/IdeaProjects/Mybatis-DEMO/opt/jacoco.exec
```

### 生成报告

```
java -jar jacococli.jar \
# 指定报告数据文件的路径
report C:/Users/SN/IdeaProjects/Mybatis-DEMO/opt/jacoco.exec \
# 指定项目编译后的 class 文件路径
--classfiles C:/Users/SN/IdeaProjects/Mybatis-DEMO/target/classes \
# 指定生成 HTML 报告路径
--html C:/Users/SN/IdeaProjects/Mybatis-DEMO/opt \
#指定源码路径（如果不指定无只能看到类和方法的覆盖率，没办法看到具体业务逻辑的服务概率）
--sourcefiles C:/Users/SN/IdeaProjects/Mybatis-DEMO/src/main/java \
# 指定编码方式
--encoding utf-8
# 指定报告名称
--name Mybatis-DEMO-Report

java -jar jacococli.jar \
report C:/Users/SN/IdeaProjects/Mybatis-DEMO/opt/jacoco.exec \
--classfiles C:/Users/SN/IdeaProjects/Mybatis-DEMO/target/classes \
--html C:/Users/SN/IdeaProjects/Mybatis-DEMO/opt \
--sourcefiles C:/Users/SN/IdeaProjects/Mybatis-DEMO/src/main/java \
--encoding utf-8
--name Mybatis-DEMO-Report
```

