### Relevant articles:

- [Spring Boot Cache with Redis](https://www.baeldung.com/spring-boot-redis-cache)

#### 初始化数据库方式

- 使用JPA
  - spring.jpa.generate-ddl=true|false 属性来控制是否开启 DDL 生成
- 使用Hibernate
  - spring.jpa.hibernate.ddl-auto=none|validate|update|create|create-drop 属性来控制使用 Hibernate 初始化数据库时的行为。在使用内存数据库（例如 h2/hsqldb/derby）时，该属性存在一个默认值：未检测到 schema manager 时，默认值为 create-drop；其他情况，默认值为 none。当上述属性值为 create 或 crate-drop 时，Hibernate 在启动时还会读取 classpath 根目录下的 import.sql 并执行（不过注意：这是 Hibernate 的特性，并非 Spring 的）。
- 使用 SQL Scripts
  - Spring Boot 会加载 classpath 根目录下的 schema.sql 和 data.sql
  - spring.sql.init.platform=platform_value 属性可以使 Spring Boot 加载 schema-platform
    value.sql和data−{platform_value}.sql，其中 platform_value 可以是 mysql/oracle/h2 等等
  - 默认情况下，只有在使用内存数据库时，Spring Boot 才会加载上述的 SQL Scripts，这个行为可以通过属性 spring.sql.init.mode=always|never|embedded 来控制：always 指使用任何数据库时都初始化数据库，never 指从不初始化数据库，embedded 指使用内存数据库时初始化数据库
  - 默认情况下，Spring Boot 执行 SQL Scripts 时是 fail-fast 的，这个行为可以通过属性spring.sql.init.continue-on-error=true|false调整
  - 默认情况下，使用 SQL Scripts 初始化数据库发生在 JPA EntityManagerFactory Bean 创建之前。尽管不推荐方式 1-3 中的多种初始化方式混合使用，但是如果想在 Hibernate 自动生成 DDL 的基础上再执行 schema.sql 和 data.sql，可以将属性spring.jpa.defer-datasource-initialization设置为 true。意味着将 SQL Scripts 的执行事件延迟（defer）到 EntityManagerFactory Bean 创建之后。schema.sql 可以在 Hibernate 生成的 DDL 基础上做额外的动作，data.sql 可以用来填充数序