## Spring Kafka

This module contains articles about Spring with Kafka

### Relevant articles

- [Intro to Apache Kafka with Spring](https://www.baeldung.com/spring-kafka)
- [Testing Kafka and Spring Boot](https://www.baeldung.com/spring-boot-kafka-testing)
- [Monitor the Consumer Lag in Apache Kafka](https://www.baeldung.com/java-kafka-consumer-lag) 还没去看，代码没有加进来
- [Send Large Messages With Kafka](https://www.baeldung.com/java-kafka-send-large-message)
- [Configuring Kafka SSL Using Spring Boot](https://www.baeldung.com/spring-boot-kafka-ssl)还没去看，代码没有加进来
- [Kafka Streams With Spring Boot](https://www.baeldung.com/spring-boot-kafka-streams)还没去看，代码没有加进来

### Intro

This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using spring-kafka.

As Kafka topics are not created automatically by default, this application requires that you create the following topics manually.

`$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic baeldung`<br>
`$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic partitioned`<br>
`$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic filtered`<br>
`$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic greeting`<br>

kafka3.0创建topic不依赖于zookeeper，也可以通过编程方式创建
`$ bin/kafka-topics.sh  --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic mytopic`<br>
`$ bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic mytopic`<br>

When the application runs successfully, following output is logged on to console (along with spring logs):

#### Message received from the 'baeldung' topic by the basic listeners with groups foo and bar
>Received Message in group 'foo': Hello, World!<br>
Received Message in group 'bar': Hello, World!

#### Message received from the 'baeldung' topic, with the partition info
>Received Message: Hello, World! from partition: 0

#### Message received from the 'partitioned' topic, only from specific partitions
>Received Message: Hello To Partioned Topic! from partition: 0<br>
Received Message: Hello To Partioned Topic! from partition: 3

#### Message received from the 'filtered' topic after filtering
>Received Message in filtered listener: Hello Baeldung!

#### Message (Serialized Java Object) received from the 'greeting' topic
>Received greeting message: Greetings, World!!
> 
> https://github.com/spring-projects/spring-kafka

注意：使用docker-compose生成的broker，也就是主机名称（对应为容器ID），要配置到host里面去，如果不配置，会导致IPv6解析失败