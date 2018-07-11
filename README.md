## Mybatis

#### MyBatis 是一个简化和实现了 Java 数据持久化层(persistence layer)的开源框架，它抽象了大量的 JDBC 冗余代码，并提供了一个简单易用的 API 和数据库交互。MyBatis 的前身是 iBATIS，iBATIS 于 2002 年由 Clinton Begin 创建。MyBatis 3 是 iBATIS 的全新设计，支持注解和 Mapper。MyBatis 流行的主要原因在于它的简单性和易使用性。在 Java 应用程序中，数据持久化层涉及到的工作有：将从数据库查询到的数据生成所需要的 Java 对象；将 Java 对象中的数据通过 SQL 持久化到数据中。MyBatis  通过抽象底层的 JDBC 代码，自动化 SQL 结果集产生 Java 对象、Java 对象的数据持久化数据库中的过程使得对 SQL 的使用变得容易。

#### 官网：http://www.mybatis.org/mybatis-3/zh/getting-started.html

#### Github:https://github.com/mybatis/mybatis-3

### 安装与配置

* 创建maven项目
* 添加maven依赖：junit , mybatis, mysql-connector-java
* 创建mybatis主配置文件

``` xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 下划线转化 -->
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

    <!-- 为java Bean起类别名 -->
    <typeAliases>
<!-- 一个一个的配置，type中放的类的完全限定名(类全路径)，aliase中放置的是类的别名  -->
        <typeAlias type="com.kaishengit.entity.Product" alias="product"/>
        <typeAlias type="com.kaishengit.entity.Movie" alias="Movie"/>
        <typeAlias type="com.kaishengit.entity.Student" alias="Student"/>
        <typeAlias type="com.kaishengit.entity.Tag" alias="Tag"/>
        <typeAlias type="com.kaishengit.entity.School" alias="School"/>

        <!-- 自动扫描，将Java类名作为类的别名（首字母小写） -->
        <!--<package name="com.kaishengit.entity"/>-->
    </typeAliases>

    <!--环境：开发|测试|线上-->
    <environments default="dev">

        <environment id="dev">
            <!-- 事务管理器 -->
            <transactionManager type="JDBC"/>
            <!-- mybatis提供3种数据源类型分别是：POOLED, UNPOOLED, JNDI -->
            <!-- POOLED  表示支持JDBC数据源连接池 -->
            <!-- UNPOOLED  表示不支持数据源连接池 -->
            <!-- INDI 表示支持外部数据源连接池 -->
            <!-- 数据库连接地址 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///dao"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
        
         <!--配置mapper文件的classpath加载路径-->
    <mappers >
        <!-- 告知映射文件， 一个一个配置 -->
        <mapper resource="mapper/ProductMappers.xml"/>
        <mapper resource="mapper/MovieMapper.xml"/>
        <mapper resource="mapper/StudentMapper.xml"/>
       
        <!-- 自动扫描包内的mapper接口与配置文件 -->
       <!-- <package name="com.kaishengit.mapper"/>-->
    </mappers>
</configuration>
```

* 创建mapper配置文件

``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.kaishengit.mapper.ProductMapper">

    <insert id="save" parameterType="com.kaishengit.entity.Product">
        insert into product (product_name, product_inventory)
        values (#{productName},#{productInventory})
    </insert>

</mapper>
```

* 在mybatis.xml主配置文件中加载mapper配置文件（多个）

### 其他配置

#### settings

* 将数据库中下划线风格的命名映射为Java中驼峰命名风格

``` xml
<settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
 </settings>
```

#### typeAliases

* 一个一个的配置，type中放的类的完全限定名(类全路径)，aliase中放置的是类的别名

``` xml
<typeAliases>
	<typeAlias type="com.kaishengit.entity.Product" alias="product"/>
    <!-- 自动扫描，将Java类名作为类的别名（首字母小写） -->
    <!--<package name="com.kaishengit.entity"/>-->
</typeAliases>
```

