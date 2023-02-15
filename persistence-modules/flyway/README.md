### Relevant Articles:
- [Database Migrations with Flyway](http://www.baeldung.com/database-migrations-with-flyway)
- [A Guide to Flyway Callbacks](http://www.baeldung.com/flyway-callbacks)
- [Rolling Back Migrations with Flyway](https://www.baeldung.com/flyway-roll-back)

### Question:
- 执行mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf命令报错，是因为SQL语法不正确
> CREATE TABLE IF NOT EXISTS `employee` (
`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(20),
`email` varchar(50),
`date_of_birth` timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
> 是因为上面的语法是MySQL数据库的，要么将最后一行删掉，或者在myFlywayConfig.conf文件中指明;DB_CLOSE_DELAY=-1;MODE=MySQL;
- 