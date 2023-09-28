生成了uppercase-in和uppercacseecho-out-0两个topic，但是没有触发uppercase和echo这两个Bean的执行，因为没有数据写入topic
README.adoc文档上有表示使用docker exec -it kafka /opt/kafka/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic uppercase-in

我安装的话使用如下命令：
docker exec -it 0c70bd9a571a /opt/bitnami/kafka/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic uppercase-in

输入（hello）数据后，uppercase和echo这两个Bean的执行就会触发，输出如下：
Uppercase: HELLO
Echo: HELLO

从日志看，有建立groupId
[Consumer clientId=consumer-anonymous.dc9e5eca-5217-4ba4-bc04-f187436f4113-2, groupId=anonymous.dc9e5eca-5217-4ba4-bc04-f187436f4113] Subscribed to topic(s): uppercase-in

